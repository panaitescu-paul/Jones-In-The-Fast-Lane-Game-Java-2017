package playground.game;

import playground.game.buildings.Building;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by paul on 26/10/17.
 */
public class Map {
    private Scanner scanner = new Scanner(System.in);

    // Game related
    private Building[] buildings;
    private Actor hero;
    public int[][] allowedPositions = {
        {}, // 0, ignore
        {2, 5, 6}, // 1 (UNIVERSITY)
        {1, 3, 6, 7}, // 2 (FACTORY)
        {2, 4, 7, 8}, // 3 (EMPLOYMENT OFFICE)
        {3, 8}, // 4 (HOME)
        {1, 6}, // 5 (BANK)
        {1, 2, 5, 7}, // 6 (RENT OFFICE)
        {2, 3, 6, 8}, // 7 (PAWN SHOP)
        {3, 4, 7}, // 8 (MONOLITH BURGER)
    };

    public Map(Building[] buildings, Actor hero) {
        this.buildings = buildings;
        this.hero = hero;
    }

    public void update(int timeTick, int timeDelta) {
        hero.drawStats();
        switch (hero.position) {
            case 1: System.out.println(drawUniversityMap()); break;
            case 2: System.out.println(drawFactoryMap()); break;
            case 3: System.out.println(drawEmploymentMap()); break;
            case 4: System.out.println(drawHomeMap()); break;
            case 5: System.out.println(drawBankMap()); break;
            case 6: System.out.println(drawRentMap()); break;
            case 7: System.out.println(drawPawnMap()); break;
            case 8: System.out.println(drawBurgerMap()); break;
        }

        System.out.println("-- Choose where to go next " + printIntArray(allowedPositions[hero.position]) + "--");
        navigate();
    }

    // Navigation
    private void navigate() {
        boolean isValid = false;
        while (!isValid) {
            int choice = scanner.nextInt();

            if (choice > 8 || choice <= 0) {
                System.out.println("Invalid range! Try again.");
            } else if (!containsInt(allowedPositions[hero.position], choice)) {
                System.out.println("Position too far! Try again.");
            } else {
                isValid = true;
                hero.position = choice;
                buildings[hero.position].enterBuilding();
            }
        }
    }

    // Map types
    private String drawHomeMap() {
        return "-- Welcome Home --\n" +
                "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 4<@> ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 5< > ┃   ┃    6 < >    ┃   ┃   7 < >    ┃   ┃     8 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    // TODO: Update building numbers
    private String drawUniversityMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 <@>    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 < >    ┃   ┃   6 < >    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawFactoryMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 <@>  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 < >    ┃   ┃   6 < >    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawEmploymentMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 <@>       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 < >    ┃   ┃   6 < >    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawBankMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4<@> ┃   ┃    5 < >    ┃   ┃   6 < >    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawRentMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 <@>    ┃   ┃   6 < >    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawPawnMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 < >    ┃   ┃   6 <@>    ┃   ┃     7 < >       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    private String drawBurgerMap() {
        return "┏━━━━━━━━━━━━┓       ┏━━━━━━━━━┓  ┏━━━━━━━━━━━━━━━━━━━━┓  ┏━━━━━━┓\n" +
                "┃ UNIVERSITY ┃┄┄┄┄┄┄┄┃ FACTORY ┃┄┄┃ EMPLOYMENT OFFICE  ┃┄┄┃ HOME ┃\n" +
                "┃   1 < >    ┃       ┃  2 < >  ┃  ┃        3 < >       ┃  ┃ 0< > ┃\n" +
                "┗━━━━━━━━━━━━┛       ┗━━━━━━━━━┛  ┗━━━━━━━━━━━━━━━━━━━━┛  ┗━━━━━━┛\n" +
                "   ╱      ╲            ╱      ╲          ╱      ╲            ┆\n" +
                "  ╱        ╲          ╱        ╲        ╱        ╲           ┆\n" +
                "┏━━━━━━┓   ┏━━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━┓   ┏━━━━━━━━━━━━━━━━━┓\n" +
                "┃ BANK ┃┄┄┄┃ RENT OFFICE ┃┄┄┄┃ PAWN SHOP  ┃┄┄┄┃ MONOLITH BURGER ┃\n" +
                "┃ 4< > ┃   ┃    5 < >    ┃   ┃   6 < >    ┃   ┃     7 <@>       ┃\n" +
                "┗━━━━━━┛   ┗━━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━┛   ┗━━━━━━━━━━━━━━━━━┛";
    }

    // Helpers
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
