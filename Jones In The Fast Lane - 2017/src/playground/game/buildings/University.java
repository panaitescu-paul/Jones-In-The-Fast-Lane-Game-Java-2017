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
public class University extends Building {

    public University(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    // LAYER 1 NAVIGATION
    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to University-" + reset);
        System.out.println();
        System.out.println("-- Choose your course: --");
        System.out.println();

        //check if there is AVAILABLE WORK in this building
        if (player.job.getName().equals("Teaching Mathematics") || player.job.getName().equals("Teaching Literature") || player.job.getName().equals("Teaching Computer Science")) {
            System.out.println("1. Work - " + player.job.getName() + "]" +
                    "[EFFECT: " + player.job.value + "kr per tick]");
        } else {
            System.out.println("1. Work [unavailable - job in different building]");
        }

        System.out.println("2. Literature Knowledge          [COST: 100.00kr][EFFECT: +2 Literature Knowledge]  per tick");
        System.out.println("3. Mathematics Knowledge         [COST: 200.00kr][EFFECT: +2 Mathematics Knowledge]  per tick");
        System.out.println("4. Computer Science Knowledge    [COST: 400.00kr][EFFECT: +2 Computer Science Knowledge]  per tick");
        System.out.println("5. Electronics Knowledge         [COST: 300.00kr][EFFECT: +2 Electronics Knowledge]  per tick");
        System.out.println("6. Robotics Knowledge            [COST: 300.00kr][EFFECT: +2 Robotics Knowledge]  per tick");
        System.out.println("7. Industrial Design Knowledge   [COST: 200.00kr][EFFECT: +2 Industrial DesignKnowledge]  per tick");
        System.out.println("0. [Exit]");
        System.out.println();
    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 1, 2, 3, 4, 5, 6, 7] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 7 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> universityActions = new ArrayList<>();
                int duration;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        if (player.job.getName().equals("Teaching Mathematics") || player.job.getName().equals("Teaching Literature") || player.job.getName().equals("Teaching Computer Science")) {
                            drawDurationOptions("Work - " + player.job.getName() + "]" +
                                    "[EFFECT: " + player.job.value + "kr per tick]");
                            duration = navigateDurationOptions();
                            if (duration == 0) {
                                // go back to the select screen
                                return showMenu();
                            } else {
                                universityActions.add(new WorkAction(player, duration));
                                player.isBusy = true;
                            }
                        }
                        break;

                    case 2:
                        drawDurationOptions("Literature Knowledge [COST: 100.00kr][EFFECT: +2 Literature Knowledge]  per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(100.00 * duration)) {
                                universityActions.add(new LearnLiteratureAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 3:
                        drawDurationOptions("Mathematics Knowledge [COST: 200.00kr][EFFECT: +2 Mathematics Knowledge] per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(200.00 * duration)) {
                                universityActions.add(new LearnMathematicsAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 4:
                        drawDurationOptions("Computer Science Knowledge [COST: 400.00kr][EFFECT: +2 Computer Science Knowledge] per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(400.00 * duration)) {
                                universityActions.add(new LearnComputerScienceAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 5:
                        drawDurationOptions("Electronics Knowledge [COST: 300.00kr][EFFECT: +2 Electronics Knowledge] per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(300.00 * duration)) {
                                universityActions.add(new LearnElectronicsAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 6:
                        drawDurationOptions("Robotics Knowledge [COST: 300.00kr][EFFECT: +2 Robotics Knowledge] per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(300.00 * duration)) {
                                universityActions.add(new LearnRoboticsAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;

                    case 7:
                        drawDurationOptions("Industrial Design Knowledge [COST: 200.00kr][EFFECT: +2 Industrial DesignKnowledge] per tick");
                        duration = navigateDurationOptions();
                        if (duration == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            if (player.tryPurchase(200.00 * duration)) {
                                universityActions.add(new LearnIndustrialDesignAction(player, duration));
                                player.isBusy = true;
                            } else {
                                return showMenu();
                            }
                        }
                        break;
                }
                return universityActions;
            }
        }
    }

    // LAYER 2 NAVIGATION
    private void drawDurationOptions(String parentOption) {
        System.out.println("-- Choose duration for: [" + parentOption + "] --");
    }

    // Navigation
    private int navigateDurationOptions() {
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