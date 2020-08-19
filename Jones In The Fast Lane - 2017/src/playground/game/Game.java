package playground.game;

import playground.game.actions.Action;
import playground.game.buildings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by paul on 30/10/17.
 */
public class Game {

    private Player player = new Player();
    private Building[] buildings = {
        null,
        new University(player),       // 1
        new Factory(player),          // 2
        new EmploymentOffice(player), // 3
        new Home(player),             // 4
        new Bank(player),             // 5
        new RentOffice(player),       // 6
        new PawnShop(player),         // 7
        new MonolithBurger(player)    // 8
    };
    private Map map = new Map(buildings, player);

    // Action queues
    private List<List<Action>> actionQueues = new ArrayList<>();
    private int gameTick = 0;

    public Game() {
        actionQueues.add(new ArrayList<>()); // 0 (MAP actions)
        actionQueues.add(new ArrayList<>()); // 1
        actionQueues.add(new ArrayList<>()); // 2
        actionQueues.add(new ArrayList<>()); // 3
        actionQueues.add(new ArrayList<>()); // 4
        actionQueues.add(new ArrayList<>()); // 5
        actionQueues.add(new ArrayList<>()); // 6
        actionQueues.add(new ArrayList<>()); // 7
        actionQueues.add(new ArrayList<>()); // 8
    }

    //console colors
    public static String reset = "\u001B[0m";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";
    public static String cyan = "\u001B[36m";
    public static String yellow = "\u001B[33m";

    public void run() {
        boolean gameEnded = false;
        setAchievements();
        while (!gameEnded) {
            process();
            weekReset();
            gameTick++;

            drawStats();
            gameEnded = checkForGameEnding();
        }
    }

    private void process() {
        // buildings
        // actor
        // inventory

        // 1. gather actions
        // 1.A (Buildings)
        for (int i = 1; i < buildings.length; i++) {
            List<Action> actions = buildings[i].update(gameTick);
            actionQueues.get(i).addAll(actions);
        }
        // 1.B (Map)
        List<Action> mapActions = map.update(gameTick);
        actionQueues.get(0).addAll(mapActions);

        // 2. execute the equivalent of a tick for each action
        for (int i = 0; i < actionQueues.size(); i++) {
            List<Action> actions = actionQueues.get(i);
            for (int j = 0; j < actions.size(); j++) {
                Action action = actions.get(j);
                action.consumeTick();
                action.elapsedTicks++;
                if (action.elapsedTicks >= action.durationInTicks) {
                    // Action consumed, remove it from the queue
                    actions.remove(j);
                    j--; // we removed one item, the next item will have the same position so override the j++ from for
                }
            }
        }
    }

    public void weekReset() {
        if (gameTick != 0 && gameTick % Home.WEEK == 0) { // if at the end of the week
            // reset player
            player.move(4); // HOME
        }
    }

    // UI Helpers
    private void drawStats() {

        String divider = "*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*";
        String divider2 = "*-------------------------------------------------------------------*";

        System.out.println("");
        System.out.print(divider + "\n");
        System.out.print(red); // color - active
        System.out.print("| ");
        System.out.format("%12s%12s%16s%13s%14s%12s%15s%18s%15s%10s%13s%20s%n", "GAME TICK |", "Money |", "Bank Account |", "Rest Energy |", "Food Energy |", "Literature |", "Mathematics |", "Computer Science |", "Electronics |", "Robotics |", "Ind. Design |", "Job |");
        System.out.print(reset); // color - reset
        System.out.print(divider + "\n");

        System.out.print("| ");
        System.out.format("%13s", gameTick + " | ");
        System.out.format("%12s", player.money + " | ");
        System.out.format("%16s", player.bankAccount + " | ");
        System.out.format("%13s", player.restEnergy + " | ");
        System.out.format("%14s", player.foodEnergy + " | ");
        System.out.format("%12s", player.literatureKnowledge + " | ");
        System.out.format("%15s", player.mathematicsKnowledge + " | ");
        System.out.format("%18s", player.computerScienceKnowledge + " | ");
        System.out.format("%15s", player.electronicsKnowledge + " | ");
        System.out.format("%10s", player.roboticsKnowledge + " | ");
        System.out.format("%13s", player.industrialDesignKnowledge + " | ");
        System.out.format("%20s", player.job.getName() + " | ");
        System.out.print("\n" + divider + "\n\n");

        System.out.print(divider2 + "\n");
        System.out.print(red); // color - active
        System.out.print("| ");
        System.out.format("%12s%12s%16s%13s%14s%n", "Freezer |", "Clothes |", "Books |", "Tv |", "Laptop |");
        System.out.print(reset); // color - reset
        System.out.print(divider2 + "\n");

        System.out.print("| ");
        System.out.format("%13s", player.freezerItem.quantity + " | ");
        System.out.format("%12s", player.clothesItem.quantity + " | ");
        System.out.format("%16s", player.booksItem.quantity + " | ");
        System.out.format("%13s", player.tvItem.quantity + " | ");
        System.out.format("%14s", player.laptopItem.quantity + " | ");

        System.out.print("\n" + divider2 + "\n\n");
    }

    public void setAchievements() {
        System.out.println("Set Achievements: ");
        System.out.println("Whealth: ");
        player.whealthAchievement.value = getInputInt();
        System.out.println("Education: ");
        player.educationAchievement.value = getInputInt();
        System.out.println("Career: ");
        player.careerAchievement.value = getInputInt();
    }

    // GAME END Conditions
    private boolean checkForGameEnding() {
        if (player.money.value < 0) {
            System.out.println(red + "Game over [NO MONEY]" + reset);
            return true;
        }

        if (player.money.value > player.whealthAchievement.value) {
            System.out.println(green + "You WON!!! Whealth status achieved " + player.whealthAchievement.value + reset);
            return true;
        }

        if (   (player.literatureKnowledge.value +
                player.mathematicsKnowledge.value +
                player.computerScienceKnowledge.value +
                player.electronicsKnowledge.value +
                player.roboticsKnowledge.value +
                player.industrialDesignKnowledge.value) > player.educationAchievement.value) {
            System.out.println(green + "You WON!!! Education status achieved!" + player.educationAchievement.value + reset);
            return true;
        }

        if (player.careerPoints.value > player.careerAchievement.value) {
            System.out.println(green + "You WON!!! Career status achieved!" + player.careerAchievement.value + reset);
            return true;
        }

        if (player.money.value > player.whealthAchievement.value * 0.7  &&              //money
            player.careerPoints.value > player.careerAchievement.value * 0.7  &&        //career
            player.restEnergy.value  > 30) {                                            //rest
            System.out.println(green + "You WON!!! Happiness status achieved!" + reset);

            player.happinessAchievement.value = 1;
            return true;
        }
        return false;
    }

    //helpers
    private int getInputInt() {
        System.out.println("-- Choose an option [10, 999.999.999] --");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            if (choice > 999999999 || choice < 10) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }
}
