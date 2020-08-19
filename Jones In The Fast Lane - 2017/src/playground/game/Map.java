package playground.game;

import playground.game.actions.Action;
import playground.game.actions.WalkAction;
import playground.game.buildings.Building;
import playground.ui.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by paul on 04/11/17.
 */
public class Map {
    private Scanner scanner = new Scanner(System.in);

    private Player player;
    private Building[] buildings;
    public int[][] buildingPaths = {
        {},                     // 0 - ignore
        {2, 5, 6},              // 1 - UNIVERSITY
        {1, 3, 6, 7},           // 2 - FACTORY
        {2, 4, 7, 8},           // 3 - EMPLOYMENT OFFICE
        {3, 8},                 // 4 - HOME
        {1, 6},                 // 5 - BANK
        {1, 2, 5, 7},           // 6 - RENT OFFICE
        {2, 3, 6, 8},           // 7 - PAWN SHOP
        {3, 4, 7},              // 8 - MONOLITH BURGER
    };

    public Map(Building[] buildings, Player player) {
        this.buildings = buildings;
        this.player = player;
    }

    public List<Action> update(int tick) {
        if (!player.isBusy) {
            System.out.println();
            System.out.println(drawMap(player.location));
            System.out.println();

            return navigate();
        }
        return new ArrayList<>();
    }

    // Navigation
    private List<Action> navigate() {
        System.out.println("-- Choose where to go next " + printIntArray(buildingPaths[player.location]) + " --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 8 || choice <= 0) {
                System.out.println("Invalid range! Try again.");
            } else if (!containsInt(buildingPaths[player.location], choice)) {
                System.out.println("Position too far! Try again.");
            } else {

                List<Action> mapActions = new ArrayList<>();
                // Create a walk action
                mapActions.add(new WalkAction(buildings, player, choice));
                return mapActions;
            }
        }
    }

    private String drawMap(int playerLocation) {
        String[] map = {
            " ┏━━━━━━━━━━━━┓      ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓ ",
            " ┃ UNIVERSITY ┃┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃ ",
            " ┃   1 < >    ┃      ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 4< > ┃ ",
            " ┗━━━━━━━━━━━━┛      ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛ ",
            "     ╱      ╲          ╱      ╲          ╱      ╲             ┆    ",
            "    ╱        ╲        ╱        ╲        ╱        ╲            ┆    ",
            " ┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓ ",
            " ┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃ ",
            " ┃ 5< > ┃   ┃    6 < >    ┃   ┃   7 < >    ┃   ┃     8 < >       ┃ ",
            " ┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛ "
        };

        return colorMap(placePlayer(String.join("\n", map), playerLocation));
    }


    // Helpers
    private String placePlayer(String map, int position) {
        int charIndex = 0;
        int foundChars = 0;

        // Get the index of consecutive '<' chars, until we reach the n'th one (n = position)
        for (int i = 1; i <= position; i++) {
            charIndex = map.indexOf('<', charIndex + 1);
            foundChars = i;
        }

        // Reconstruct the map from 2 parts:
        //  part 1. from index 0, until the index of the '<' (that correlates to our position) + 1
        //  part 2. from the index of the '<' + 2 (the character itself, and the next placeholder space), until the end
        if (foundChars == position) {
            map = map.substring(0, charIndex + 1) +             // part 1
                    Color.magenta("@") +                   // player char
                    map.substring(charIndex + 2, map.length()); // part 2
        }
        return map;
    }

    private String colorMap(String map) {
        String mapWithColoredBuildings = colorCharsBlue(new char[] {'┏', '━', '┓', '┃', '┛', '━', '┗'}, map);
        String mapWithColoredPaths = colorCharsGreen(new char[] {'┄', '┆', '╱', '╲'}, mapWithColoredBuildings);
        return mapWithColoredPaths;
    }

    private String colorCharsBlue(char[] chars, String input) {
        // For each char, replace all occurrences with the same char but with the specified ANSI codes
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            String currentCharAsString = String.valueOf(currentChar);
            input = input.replaceAll(currentCharAsString, Color.blue(currentCharAsString));
        }
        return input;
    }

    private String colorCharsGreen(char[] chars, String input) {
        // For each char, replace all occurrences with the same char but with the specified ANSI codes
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            String currentCharAsString = String.valueOf(currentChar);
            input = input.replaceAll(currentCharAsString, Color.green(currentCharAsString));
        }
        return input;
    }

    private String colorCharsMagenta(char[] chars, String input) {
        // For each char, replace all occurrences with the same char but with the specified ANSI codes
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            String currentCharAsString = String.valueOf(currentChar);
            input = input.replaceAll(currentCharAsString, Color.magenta(currentCharAsString));
        }
        return input;
    }

    // Navigation helpers
    private boolean containsInt(int[] input, int value) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == value) {
                return true;
            }
        }
        return false;
    }

    private String printIntArray(int[] input) {
        String str = "";
        for (int i = 0; i < input.length; i++) {
            str += (i == input.length - 1)
                    ? input[i]
                    : input[i] + ", ";

        }
        return "[" + str + "]";
    }
}
