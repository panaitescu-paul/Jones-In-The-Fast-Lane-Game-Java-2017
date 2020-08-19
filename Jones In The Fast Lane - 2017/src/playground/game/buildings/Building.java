package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by paul on 30/10/17.
 */
public class Building {
    Scanner scanner = new Scanner(System.in);

    public boolean hasArrived = false;
    public boolean hasLeft = false;

    Player player;
    int currentTick = 0;

    public Building(Player player) {
        this.player = player;
    }

    public List<Action> update(int tick) {
        currentTick = tick;
        List<Action> updateActions = backgroundUpdate(tick);
        if (hasArrived && !player.isBusy) {
            // add the actions from whileInside
            updateActions.addAll(whileInside());
        }

        // Building check
        return updateActions;
    }

    public List<Action> whileInside() {
        // Implement logic for each building
        return new ArrayList<>();
    }

    public List<Action> backgroundUpdate(int tick) {
        // Implement logic for each update
        return new ArrayList<>();
    }

    public void enterBuilding() {
        hasArrived = true;
        hasLeft = false;
    }

    public void leaveBuilding() {
        hasArrived = false;
        hasLeft = true;
    }

    // Helpers
    public int remainingTicksInWeek() {
        if (currentTick % Home.WEEK == 0) {
            return 0;
        }
        return Home.WEEK - (currentTick % Home.WEEK);
    }
}
