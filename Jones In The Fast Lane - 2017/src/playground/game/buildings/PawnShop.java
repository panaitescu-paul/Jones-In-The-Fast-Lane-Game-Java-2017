package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;
import playground.game.actions.SellAction;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.cyan;
import static playground.game.Game.reset;
import static playground.game.actions.SellAction.PawnShopInventoryList;

/**
 * Created by paul on 30/10/17.
 */
public class PawnShop extends Building {

    public PawnShop(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Pawn Shop-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();
        System.out.println("1. Sell [Effect: + money]");
        System.out.println("2. Buy [Effect: - money]");
        System.out.println("0. [Exit]");
        System.out.println();
    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 2] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 2 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> pawnShopActions = new ArrayList<>();
                int index;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1: //SELL
                        showSubMenu("Sell [Effect: + money]");
                        showInventoryItems();
                        index = chooseItemMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (index == 1){
                            if (player.freezerItem.quantity >= 1) {
                                pawnShopActions.add(new SellAction(player, 1, player.freezerItem.getName(), player.freezerItem.price));
                                player.freezerItem.quantity -= 1; // remove 1 item from player
                                player.money.increase(player.freezerItem.price); //add money to player
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        } else if (index == 2){
                            if (player.clothesItem.quantity >= 1) {
                                pawnShopActions.add(new SellAction(player, 1, player.clothesItem.getName(), player.clothesItem.price));
                                player.clothesItem.quantity -= 1; // remove 1 item from player
                                player.money.increase(player.clothesItem.price); //add money to player
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        } else if (index == 3){
                            if (player.booksItem.quantity >= 1) {
                                pawnShopActions.add(new SellAction(player, 1, player.booksItem.getName(), player.booksItem.price));
                                player.booksItem.quantity -= 1; // remove 1 item from player
                                player.money.increase(player.booksItem.price); //add money to player
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        } else if (index == 4){
                            if (player.tvItem.quantity >= 1) {
                                pawnShopActions.add(new SellAction(player, 1, player.tvItem.getName(), player.tvItem.price));
                                player.tvItem.quantity -= 1; // remove 1 item from player
                                player.money.increase(player.tvItem.price); //add money to player
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        } else if (index == 5){
                            if (player.laptopItem.quantity >= 1) {
                                pawnShopActions.add(new SellAction(player, 1, player.laptopItem.getName(), player.laptopItem.price));
                                player.laptopItem.quantity -= 1; // remove 1 item from player
                                player.money.increase(player.laptopItem.price); //add money to player
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 2: // BUY
                        showSubMenu("Buy [Effect: - money]");
                        showPawnShopItems();
                        index = chooseItemMenu2();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {

                            // SEARCH
                            for (int i = 0; i < PawnShopInventoryList.size(); i++) { // search the item from inventory

                                if (player.tryPurchase(PawnShopInventoryList.get(index-1).price + PawnShopInventoryList.get(index-1).price * 0.25)) { // increase the item's price with 25%

                                    if (PawnShopInventoryList.get(index-1).getName().equals( player.freezerItem.getName())) { //add item to player
                                        player.freezerItem.quantity ++;
                                    } else if (PawnShopInventoryList.get(index-1).getName().equals( player.clothesItem.getName())) { //add item to player
                                        player.clothesItem.quantity ++;
                                    } else if (PawnShopInventoryList.get(index-1).getName().equals( player.booksItem.getName())) { //add item to player
                                        player.booksItem.quantity ++;
                                    } else if (PawnShopInventoryList.get(index-1).getName().equals( player.tvItem.getName())) { //add item to player
                                        player.tvItem.quantity ++;
                                    } else if (PawnShopInventoryList.get(index-1).getName().equals( player.laptopItem.getName())) { //add item to player
                                        player.laptopItem.quantity ++;
                                    }

                                    PawnShopInventoryList.remove(index-1); //remove item from list
                                    return showMenu();
                                } else {
                                    return showMenu();
                                }
                            }
                        }
                        break;
                }
                return pawnShopActions;
            }
        }
    }

    // LAYER 2 NAVIGATION


    private void showSubMenu(String parentOption) {
        System.out.println("-- Choose item : [" + parentOption + "] --");
    }

    // Navigation
    private int chooseItemMenu() {
        System.out.println();
        System.out.println("-- Choose an option [0, " + 5 + "] --");

        while (true) {
            int choice = scanner.nextInt();

            if (choice > 5 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }

    // Navigation
    private int chooseItemMenu2() {
        System.out.println();
        System.out.println("-- Choose an option [0, " + PawnShopInventoryList.size() + "] --");

        while (true) {
            int choice = scanner.nextInt();

            if (choice > PawnShopInventoryList.size() || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }

    // show Player items name and price
    private void showInventoryItems() {
        System.out.println("1. " + player.freezerItem.getName() + ": " + player.freezerItem.price + "kr");
        System.out.println("2. " + player.clothesItem.getName() + ": " + player.clothesItem.price + "kr");
        System.out.println("3. " + player.booksItem.getName() + ": " + player.booksItem.price + "kr");
        System.out.println("4. " + player.tvItem.getName() + ": " + player.tvItem.price + "kr");
        System.out.println("5. " + player.laptopItem.getName() + ": " + player.laptopItem.price + "kr");
    }

    // show Pawn Shop items name and price
    private void showPawnShopItems() {
        for (int index = 0; index < PawnShopInventoryList.size(); index++) {
            System.out.println((index + 1) + "." + PawnShopInventoryList.get(index).getName() + ": " + PawnShopInventoryList.get(index).price + "kr");
        }
    }

    // Helper
    private List<Action> showMenu() {
        drawOptions();
        return navigateOptions();
    }
}