package playground.game.actions;


/**
 * Created by paul on 04/11/17.
 */
public class Action {
    public int durationInTicks;  // total duration for action
    public int elapsedTicks = 0; // progress [0 - durationInTicks]

    public Action(int durationInTicks) {
        this.durationInTicks = durationInTicks;
    }

    public void consumeTick() {
        // logic equivalent with 1 tick
        // NOTE: this method will be called n number of times (where n = durationInTicks)
        //    after each call, elapsedTicks will be incremented until it reaches durationInTicks
    }
}
