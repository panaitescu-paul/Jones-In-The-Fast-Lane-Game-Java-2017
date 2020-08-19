package playground.game.actions;

import playground.game.Player;
import playground.game.buildings.RentOffice;

/**
 * Created by paul on 04/11/17.
 */
public class RentPenaltyAction extends Action {

    public final static double PENALTY_RENT = RentOffice.RENT + 100.00;
    // Game info
    private Player player;
    private double tmpMoney = 0;

    public RentPenaltyAction(Player player) {
        super(Integer.MAX_VALUE);

        this.player = player;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

        if (tmpMoney >= PENALTY_RENT) {
            durationInTicks = 0; // stop action
        } else {
            // .................... daily rent
            double penaltyAmount = (PENALTY_RENT / 25);
            this.player.money.forceDeduct(penaltyAmount);
            tmpMoney+= penaltyAmount;
        }
    }
}
