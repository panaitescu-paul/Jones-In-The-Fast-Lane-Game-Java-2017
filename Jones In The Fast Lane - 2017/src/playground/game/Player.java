package playground.game;

import playground.game.stats.Stat;
import playground.ui.Color;

/**
 * Created by paul on 30/10/17.
 */
public class Player {

    String name = "Paul";
    int location = 4; //HOME
    public boolean isBusy = false;

    // Stats
    public Stat money = new Stat("Money", 1000.00);
    public Stat rentAccount = new Stat("Rent Account", 0);
    public Stat foodEnergy = new Stat("Food Energy", 0);
    public Stat restEnergy = new Stat("Rest Energy", 0);

    // bank
    public Stat bankAccount = new Stat("Bank Account", 0);

    // jobs
    public Stat job = new Stat("Unemployed", 0);

    // Knowledge for University Jobs
    public Stat literatureKnowledge = new Stat("LiteratureKnowledge", 0);
    public Stat mathematicsKnowledge = new Stat("MathematicsKnowledge", 0);
    public Stat computerScienceKnowledge = new Stat("ComputerScienceKnowledge", 0);

    // Knowledge for Factory Jobs
    public Stat electronicsKnowledge = new Stat("ElectronicsKnowledge", 0);
    public Stat roboticsKnowledge = new Stat("RoboticsKnowledge", 0);
    public Stat industrialDesignKnowledge = new Stat("IndustrialDesignKnowledge", 0);

    public Stat careerPoints = new Stat("careerPoints", 0);

    // Achievements - to WIN the game
    public Stat whealthAchievement = new Stat("whealthAchievement", 999999);
    public Stat educationAchievement = new Stat("educationAchievement", 999999);
    public Stat careerAchievement = new Stat("careerAchievement", 999999);
    public Stat happinessAchievement = new Stat("happinessAchievement", 999999);

    // Inventory items
    public Stat freezerItem = new Stat("Freezer Item", 400, 1);
    public Stat clothesItem = new Stat("Clothes Item", 200, 3);
    public Stat booksItem = new Stat("Books Item", 150, 2);
    public Stat tvItem = new Stat("Tv Item", 1500, 1);
    public Stat laptopItem = new Stat("Laptop Item", 3000, 1);


    public void move(int destination) {
        location = destination;
    }

    // check if there are sufficient founds
    public boolean tryPurchase(Double amount) {
        if (money.deduct(amount)) {
            System.out.println(Color.green("Transaction complete! -" + amount + "kr"));
            return true;
        }
        System.out.println(Color.red("We are sorry, but we can't complete this transaction. Reason: Insufficient founds."));
        return false;
    }

    // Withdraw money from bank
    public boolean tryWithdraw(Double amount) {
        if (bankAccount.deduct(amount)) {
            System.out.println(Color.green("Transaction complete! -" + amount + "kr"));
            return true;
        }
        System.out.println(Color.red("We are sorry, but we can't complete this transaction. Reason: Insufficient founds."));
        return false;
    }
}
