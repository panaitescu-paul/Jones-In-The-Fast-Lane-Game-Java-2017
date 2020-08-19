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
public class Home extends Building {
    public final static int WEEK = 7;

    public Home(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        drawOptions();
        return navigateOptions();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome HOME-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();
        System.out.println("1. Rest [EFFECT: +10 energy]");
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
                List<Action> homeActions = new ArrayList<>();
                int duration;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        showDurationMenu("Rest [EFFECT: +10 energy]");
                        duration = navigateQuantityOrDurationMenu();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            homeActions.add(new RestAction(player, duration, 10));
                            player.isBusy = true;
                        }
                        break;
                }
                return homeActions;
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
