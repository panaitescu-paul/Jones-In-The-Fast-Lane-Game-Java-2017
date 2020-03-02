package playground.game.buildings;

import playground.game.Actor;
import playground.game.Game;

import java.util.Scanner;

/**
 * Created by paul on 26/10/17.
 */
public class MonolithBurger extends Building {
    public MonolithBurger(Actor hero) {
        super(hero);
    }

    @Override
    public int whileInside() {
        System.out.println("Welcome to 'MonolithBurger'");
        System.out.println("-- Choose your order: ---");

        System.out.println("1. Hamburger 120.00kr");
        System.out.println("2. Pepsi 60.00kr");
        System.out.println("3. Ice Tea 20.00kr");
        System.out.println("0. [Exit]");

        navigate();
        return 1;
    }

    private void navigate() {
        boolean isValid = false;
        while (!isValid) {
            int choice = scanner.nextInt();

            if (choice > 3 || choice < 0) {
                System.out.println("Invalid range! Try again.");
            } else {
                isValid = true;
                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;
                    case 1: purchase(120.0); break;
                    case 2: purchase(60.0); break;
                    case 3: purchase(30.0); break;
                }
            }
        }
    }

    private void purchase(Double amount) {
        if (hero.tryPurchase(amount)) {
            System.out.println("Thank you for your purchase! Do you wish something else?");
        } else {
            System.out.println("We are sorry, but we can't complete this transaction. Reason: Insufficient founds.");
        }
    }
}

