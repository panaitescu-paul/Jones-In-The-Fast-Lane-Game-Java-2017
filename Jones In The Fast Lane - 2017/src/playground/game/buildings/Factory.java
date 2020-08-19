package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;
import playground.game.actions.WorkAction;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.cyan;
import static playground.game.Game.reset;

/**
 * Created by paul on 30/10/17.
 */
public class Factory extends Building {

    public Factory(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Factory-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();

        //check if there is AVAILABLE WORK in this building
        if (player.job.getName().equals("Electronics") || player.job.getName().equals("Industrial Design") || player.job.getName().equals("Robotics")) {
            System.out.println("1. Work - " + player.job.getName() + "]" +
                    "[EFFECT: " + player.job.value + "kr per tick]");
        } else {
            System.out.println("1. Work [unavailable - job in different building]");
        }

        System.out.println("0. [Exit]");
        System.out.println();
    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 1] --");
        while (true) {
            int choice = scanner.nextInt();
            if (choice > 1 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> factoryActions = new ArrayList<>();
                int duration;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        if (player.job.getName().equals("Electronics") || player.job.getName().equals("Industrial Design") || player.job.getName().equals("Robotics")) {
                            showDurationMenu("Work - " + player.job.getName() + "]" +
                                    "[EFFECT: " + player.job.value + "kr per tick]");
                            duration = navigateQuantityOrDurationMenu();
                            if (duration == 0) {
                                // go back to the select screen
                                return showMenu();
                            } else {
                                factoryActions.add(new WorkAction(player, duration));
                                player.isBusy = true;
                            }
                        }
                        break;
                }
                return factoryActions;
            }
        }
    }

    // LAYER 2 NAVIGATION
    private void showDurationMenu(String parentOption) {
        System.out.println("-- Choose duration for: [" + parentOption + "] --");
    }

    // Navigation
    private int navigateQuantityOrDurationMenu() {
        int max = remainingTicksInWeek();
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

    // Helper
    private List<Action> showMenu() {
        drawOptions();
        return navigateOptions();
    }
}
