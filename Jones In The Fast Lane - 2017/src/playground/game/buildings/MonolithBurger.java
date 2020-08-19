package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;
import playground.game.actions.EatAction;
import playground.game.actions.WorkAction;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.red;
import static playground.game.Game.cyan;
import static playground.game.Game.reset;


/**
 * Created by paul on 30/10/17.
 */
public class MonolithBurger extends Building {

    public MonolithBurger(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Monolith Burger-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();

        //check if there is AVAILABLE WORK in this building
        if (player.job.getName().equals("Dishwashing") || player.job.getName().equals("Cooking") || player.job.getName().equals("Cleaning")) {
            System.out.println("1. Work - " + player.job.getName() +
                    " [EFFECT: " + player.job.value + "kr per tick]");
        } else {
            System.out.println("1. Work [unavailable - job in different building]");
        }

        System.out.println("2. Hotdog  [COST: 110.00kr][EFFECT: +2 energy]");
        System.out.println("3. Chicken [COST: 160.00kr][EFFECT: +4 energy]");
        System.out.println("4. Tea     [COST:  60.00kr][EFFECT: +1 energy]");
        System.out.println("0. [Exit]");
        System.out.println();
    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 1, 2, 3, 4] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 4 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> monolithBurgerActions = new ArrayList<>();
                int duration;
                int quantity;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        if (player.job.getName().equals("Dishwashing") || player.job.getName().equals("Cooking") || player.job.getName().equals("Cleaning")) {
                            showDurationMenu("Work - " + player.job.getName() + "]" +
                                                         "[EFFECT: " + player.job.value + "kr per tick]");
                            duration = navigateQuantityOrDurationMenu();

                            if (duration == 0) {
                                // go back to the select screen
                                return showMenu();
                            } else {
                                monolithBurgerActions.add(new WorkAction(player, duration));
                                player.isBusy = true;
                            }
                        }

                        break;

                    case 2:
                        showQuantityMenu("Hotdog [COST: 110.00kr][EFFECT: +2 energy]");
                        quantity = navigateQuantityOrDurationMenu();

                        if (quantity == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(110.00 * quantity)) {
                                monolithBurgerActions.add(new EatAction(player, quantity, 2));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 3:
                        showQuantityMenu("Chicken [COST: 160.00kr][EFFECT: +4 energy]");
                        quantity = navigateQuantityOrDurationMenu();

                        if (quantity == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(160.00 * quantity)) {
                                monolithBurgerActions.add(new EatAction(player, quantity, 4));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 4:
                        showQuantityMenu("Tea [COST:  60.00kr][EFFECT: +1 energy]");
                        quantity = navigateQuantityOrDurationMenu();

                        if (quantity == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(60.00 * quantity)) {
                                monolithBurgerActions.add(new EatAction(player, quantity, 1));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;
                }
                return monolithBurgerActions;
            }
        }
    }


    // LAYER 2 NAVIGATION
    private void showDurationMenu(String parentOption) {
        System.out.println("-- Choose duration for: [" + parentOption + "] --");
    }

    private void showQuantityMenu(String parentOption) {
        System.out.println();
        System.out.println("-- Choose quantity for: " + parentOption + " --");
    }

    // Navigation
    private int navigateQuantityOrDurationMenu() {
        int max = remainingTicksInWeek();
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
