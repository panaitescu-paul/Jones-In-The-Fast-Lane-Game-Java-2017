package playground.game.actions;

import playground.game.Player;
import playground.game.stats.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 09/11/17.
 */
public class SellAction extends Action {

    public static List<Stat> PawnShopInventoryList = new ArrayList<Stat>();
    // Game info
    private Player player;

    public SellAction(Player player, int duration, String itemName, double itemPrice) {
        super(duration);
        this.player = player;
        PawnShopInventoryList.add(new Stat(itemName, itemPrice, 1)); // add item in list
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
