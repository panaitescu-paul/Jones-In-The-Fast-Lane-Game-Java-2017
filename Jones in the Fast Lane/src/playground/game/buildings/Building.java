package playground.game.buildings;

import playground.game.Actor;

import java.util.Scanner;

/**
 * Created by paul on 26/10/17.
 */
public class Building {
    Scanner scanner = new Scanner(System.in);

    public boolean hasArrived = false;
    public boolean hasLeft = false;

    Actor hero;

    public Building(Actor hero) {
        this.hero = hero;
    }

    public int update(int timeTick, int timeDelta) {
        int timePast = 0;
        if (hasArrived) {
            while (!hasLeft) {
                hero.drawStats();
                timePast += whileInside();
            }
        }
        return timePast;
    }

    public int whileInside() {
        // Implement logic for each building
        return 0;
    }

    public void enterBuilding() {
        hasArrived = true;
        hasLeft = false;
    }

    public void leaveBuilding() {
        hasArrived = false;
        hasLeft = true;
    }
}

