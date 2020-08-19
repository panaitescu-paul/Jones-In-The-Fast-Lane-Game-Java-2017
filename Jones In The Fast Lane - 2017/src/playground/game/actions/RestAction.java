package playground.game.actions;

import playground.game.Player;

/**
 * Created by paul on 06/11/17.
 */
public class RestAction extends Action {
    // Game info
    private Player player;

    private int energyPerTick;

    public RestAction(Player player, int duration, int energyPerTick) {
        super(duration);

        this.player = player;
        this.energyPerTick = energyPerTick;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

        this.player.restEnergy.increase(energyPerTick);

        if (elapsedTicks == durationInTicks - 1) {
            // This is the final tick for this action
            // remove the busy attribute
            player.isBusy = false;
        }
    }
}
