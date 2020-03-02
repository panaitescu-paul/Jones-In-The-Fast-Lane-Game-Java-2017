package playground.game;

/**
 * Created by paul on 26/10/17.
 */
public class Actor {
    String name;
    Double amount = 1000.0;
    int position = 4;

    private int timeTick;
    private int timeDelta;

    public Actor(String name, Double startAmount) {
        this.name = name;
        amount = startAmount;
    }

    public void drawStats() {
        System.out.println();
        System.out.println(name + " [MONEY:  " + amount + "] [WEEK: " + timeTick / 7 +", DAY: " + timeTick % 7 +"]");
        System.out.println();
    }

    public void update(int timeTick, int timeDelta) {
        this.timeTick = timeTick;
        this.timeDelta = timeDelta;
    }

    public boolean tryPurchase(Double amount) {
        if (this.amount > amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}
