package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.*;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.cyan;
import static playground.game.Game.reset;

/**
 * Created by paul on 30/10/17.
 */
public class Bank extends Building {

    public Bank(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Bank-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();
        System.out.println("1. Deposit  [EFFECT: - money]");
        System.out.println("2. Withdraw [EFFECT: + money]");
        System.out.println("0. [Exit]");
        System.out.println();

    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 1, 2] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 2 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> bankActions = new ArrayList<>();
                int quantity;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        showQuantityMenu("Deposit  [EFFECT: - money]");
                        quantity = navigateDepositQuantityMenu();

                        if (quantity == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(1.00 * quantity)) { // int to double
                                bankActions.add(new DepositMoneyAction(player, 1, 1.00 * quantity));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 2:
                        showQuantityMenu("Withdraw [EFFECT: + money]");
                        quantity = navigateWithdrawQuantityMenu();

                        if (quantity == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                                //  decrease money from bank account
                            if (player.tryWithdraw(1.00 * quantity)) { // int to double
                                // increase money from player
                                bankActions.add(new WithdrawMoneyAction(player, 1, 1.00 * quantity));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;
                }
                return bankActions;
            }
        }
    }

    // LAYER 2 NAVIGATION
    private void showQuantityMenu(String parentOption) {
        System.out.println("-- Choose quantity for: [" + parentOption + "] --");
    }

    // Navigation
    private int navigateDepositQuantityMenu() {
        int max = (int)player.money.value;
        System.out.println();
        System.out.println("-- Choose an option [0, " + max + "] --");

        while (true) {
            int choice = scanner.nextInt();

            if (choice > max || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }

    // Navigation
    private int navigateWithdrawQuantityMenu() {
        int max = (int)player.bankAccount.value;
        System.out.println("-- Choose an option [0, " + max + "] --");

        while (true) {
            int choice = scanner.nextInt();

            if (choice > max || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }

    // Helper
    private List<Action> showMenu() {
        drawOptions();
        return navigateOptions();
    }
}
