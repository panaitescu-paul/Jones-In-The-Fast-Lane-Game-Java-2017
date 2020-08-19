package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;
import playground.game.actions.RentPenaltyAction;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.cyan;
import static playground.game.Game.reset;

/**
 * Created by paul on 30/10/17.
 */
public class RentOffice extends Building {
    public final static double RENT = 500.00;

    public RentOffice(Player player) {
        super(player);
    }

    public List<Action> backgroundUpdate(int tick) {
        // Implement logic for each update
        List<Action> rentOfficeActions = new ArrayList<>();

        if (tick != 0 && tick % 25 == 0) { // extract the rent after 25 ticks
            player.rentAccount.deduct(RENT);
            if (!player.rentAccount.deduct(RENT)) { // if not enough money
                rentOfficeActions.add(new RentPenaltyAction(player));
            }
        }
        return rentOfficeActions;
    }

    public List<Action> whileInside() {
        drawOptions();
        return navigateOptions();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Rent Office-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();
        System.out.println("1. Pay rent [COST: " + RENT + "kr][EFFECT: rent payed for 25 ticks]");
        System.out.println("0. [Exit]");
        System.out.println();
    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println();
        System.out.println("-- Choose an option [0, 1] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 1 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;
                    case 1:
                        if (player.tryPurchase(RENT)) {
                            player.rentAccount.increase(RENT);
                        }
                        break;
                }
                return new ArrayList<>();
            }
        }
    }
}