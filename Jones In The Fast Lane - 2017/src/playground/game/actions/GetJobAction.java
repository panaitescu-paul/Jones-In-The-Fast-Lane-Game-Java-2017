package playground.game.actions;

import playground.game.Player;

/**
 * Created by paul on 08/11/17.
 */
public class GetJobAction extends Action {

    // Game info
    private Player player;

    public GetJobAction(Player player, int duration, String jobName, double jobSalary) {
        super(duration);

        this.player = player;

        player.job.setName(jobName);
        player.job.value = jobSalary;
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
