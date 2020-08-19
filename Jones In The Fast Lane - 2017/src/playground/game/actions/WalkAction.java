package playground.game.actions;

import playground.game.Player;
import playground.game.buildings.Building;

/**
 * Created by paul on 04/11/17.
 */
public class WalkAction extends Action {
    // Game info
    private Building[] buildings;
    private Player player;
    private int destination;

    public WalkAction(Building[] buildings, Player player, int destination) {
        super(1);

        this.buildings = buildings;
        this.player = player;
        this.destination = destination;
    }

    @Override
    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)

        this.player.move(destination);
        this.buildings[destination].enterBuilding();
    }
}
