package playground.game.actions;

import playground.game.Player;

/**
 * Created by paul on 08/11/17.
 */
public class WithdrawMoneyAction extends Action {

    // Game info
    private Player player;

    public WithdrawMoneyAction(Player player, int duration, double amount) {
        super(duration);

        this.player = player;
        this.player.money.value += amount;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

//        this.player.money.increase(100.00);

        if (elapsedTicks == durationInTicks - 1) {
            // This is the final tick for this action
            // remove the busy attribute
            player.isBusy = false;
        }
    }
}
