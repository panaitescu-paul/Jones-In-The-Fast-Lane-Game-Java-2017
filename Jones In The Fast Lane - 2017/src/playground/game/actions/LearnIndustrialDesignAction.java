package playground.game.actions;

import playground.game.Player;

/**
 * Created by paul on 09/11/17.
 */
public class LearnIndustrialDesignAction extends Action {
    // Game info
    private Player player;

    public LearnIndustrialDesignAction(Player player, int duration) {
        super(duration);

        this.player = player;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

        this.player.industrialDesignKnowledge.increase(2);

        if (elapsedTicks == durationInTicks - 1) {
            // This is the final tick for this action
            // remove the busy attribute
            player.isBusy = false;
        }
    }
}
