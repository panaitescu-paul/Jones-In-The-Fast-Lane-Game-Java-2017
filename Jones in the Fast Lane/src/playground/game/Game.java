package playground.game;

import playground.game.buildings.*;

/**
 * Created by paul on 26/10/17.
 */
public class
Game {

    private int timeTick = 0;
    private int timeDelta = 0;
    private Actor hero = new Actor("John Smith", 200.3);
    private Building[] buildings = {
            null,
        new University(hero),       // 1
        new Factory(hero),          // 2
        new EmploymentOffice(hero), // 3
        new Home(hero),             // 4
        new Bank(hero),             // 5
        new RentOffice(hero),       // 6
        new PawnShop(hero),         // 7
        new MonolithBurger(hero)    // 8
    };
    private Map map = new Map(buildings, hero);

    public void run() {
        while(true) {
            int addTime = 0;
            for (int i = 0; i < buildings.length; i++) {
                if (buildings[i] != null) {
                    addTime += buildings[i].update(timeTick, timeDelta);
                }
            }
            map.update(timeTick, timeDelta);

            timeDelta = (addTime > 0)
                    ? addTime
                    : 1;
            timeTick += timeDelta;
        }
    }

}
