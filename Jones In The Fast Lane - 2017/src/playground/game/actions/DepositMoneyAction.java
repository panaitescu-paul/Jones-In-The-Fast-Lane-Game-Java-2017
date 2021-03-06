package playground.game.actions;

import playground.game.Player;

/**
 * Created by paul on 08/11/17.
 */
public class DepositMoneyAction extends Action {

    // Game info
    private Player player;


    public DepositMoneyAction(Player player, int duration, double amount) {
        super(duration);

        this.player = player;
        this.player.bankAccount.value += amount;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

        if (elapsedTicks == durationInTicks - 1) {
            // This is the final tick for this action
            // remove the busy attribute
            player.isBusy = false;
        }
    }
}
