package playground.game.stats;

/**
 * Created by paul on 04/11/17.
 */
public class Stat {
    String name;
    public double value;
    public double price;
    public double quantity;

    public Stat(String name, double startValue) {
        this.name = name;
        this.value = startValue;
    }

    //overloading the constructor
    public Stat(String name, double price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increase(double byAmount) {
        this.value += byAmount;
    }

    public boolean deduct(double amount) {
        if (this.value >= amount) {
            this.value -= amount;
            return true;
        }
        return false;
    }

    public void forceDeduct(double amount) {
        this.value -= amount;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
