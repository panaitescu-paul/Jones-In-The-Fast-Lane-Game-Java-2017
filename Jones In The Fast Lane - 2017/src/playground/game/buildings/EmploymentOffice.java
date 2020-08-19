package playground.game.buildings;

import playground.game.Player;
import playground.game.actions.Action;
import playground.game.actions.GetJobAction;

import java.util.ArrayList;
import java.util.List;

import static playground.game.Game.cyan;
import static playground.game.Game.red;
import static playground.game.Game.reset;

/**
 * Created by paul on 30/10/17.
 */
public class EmploymentOffice extends Building {

    public EmploymentOffice(Player player) {
        super(player);
    }

    public List<Action> whileInside() {
        return showMenu();
    }

    private void drawOptions() {
        System.out.println(cyan + "         -Welcome to Employment Office-" + reset);
        System.out.println();
        System.out.println("-- Choose an option: --");
        System.out.println();
        System.out.println(red + "   Employer             Position                    Salary" + reset);
        System.out.println("1. Monolity Burger      Dishwashing                 75kr/tick");
        System.out.println("2. Monolity Burger      Cooking                     200kr/tick");
        System.out.println("3. Monolity Burger      Cleaning                    75kr/tick");
        System.out.println("4. Factory              Electronics                 200kr/tick");
        System.out.println("5. Factory              Industrial Design           200kr/tick");
        System.out.println("6. Factory              Robotics                    300kr/tick");
        System.out.println("7. University           Teaching Mathematics        300kr/tick");
        System.out.println("8. University           Teaching Literature         300kr/tick");
        System.out.println("9. University           Teaching Computer Science   1000kr/tick");
        System.out.println("0. [Exit]");
        System.out.println();

    }

    // Navigation
    private List<Action> navigateOptions() {
        System.out.println("-- Choose an option [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] --");
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 9 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                List<Action> employmentOfficeActions = new ArrayList<>();
                int index;

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        leaveBuilding();
                        break;

                    case 1:
                        showSubMenu("1. Monolity Burger - Dishwashing - 75kr/tick");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Dishwashing", 75));
                            player.isBusy = true;
                        }
                        break;

                    case 2:
                        showSubMenu("2. Monolity Burger - Cooking - 200kr/tick");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Cooking", 200));
                            player.isBusy = true;
                        }
                        break;

                    case 3:
                        showSubMenu("Monolity Burger - Cleaning - 75kr/tick ");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else {
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Cleaning", 75));
                            player.isBusy = true;
                        }
                        break;

                    case 4:
                        showSubMenu("Factory - Electronics - 200kr/tick");
                        System.out.println("Qualifications: " + "Electronics [" + player.electronicsKnowledge.value + "/10]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.electronicsKnowledge.value > 10) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Electronics", 200));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;

                    case 5:
                        showSubMenu("Factory - Industrial Design - 200kr/tick");
                        System.out.println("Qualifications: " + "Industrial Design [" + player.industrialDesignKnowledge.value + "/12]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.industrialDesignKnowledge.value > 12) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Industrial Design", 200));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;

                    case 6:
                        showSubMenu("Factory - Robotics - 200kr/tick");
                        System.out.println("Qualifications: " + "Robotics [" + player.roboticsKnowledge.value + "/14]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.roboticsKnowledge.value > 14) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Robotics", 300));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;

                    case 7:
                        showSubMenu("Factory - Teaching Mathematics - 200kr/tick");
                        System.out.println("Qualifications: " + "Teaching Mathematics [" + player.mathematicsKnowledge.value + "/15]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.mathematicsKnowledge.value > 15) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Teaching Mathematics", 300));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;

                    case 8:

                        showSubMenu("Factory - Teaching Literature - 200kr/tick");
                        System.out.println("Qualifications: " + "Teaching Literature [" + player.literatureKnowledge.value + "/15]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.literatureKnowledge.value > 15) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Teaching Literature", 300));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;

                    case 9:
                        showSubMenu("Factory - Teaching Computer Science - 200kr/tick");
                        System.out.println("Qualifications: " + "Teaching Computer Science [" + player.computerScienceKnowledge.value + "/20]");
                        index = chooseJobMenu();

                        if (index == 0) {
                            // go back to the select screen
                            return showMenu();
                        } else if (player.computerScienceKnowledge.value > 20) { //check for qualifications
                            employmentOfficeActions.add(new GetJobAction(player, 1, "Teaching Computer Science", 1000));
                            player.isBusy = true;
                        } else {
                            System.out.println("Not enough education");
                            return showMenu();
                        }
                        break;
                }
                return employmentOfficeActions;
            }
        }
    }


    // LAYER 2 NAVIGATION
    private void showSubMenu(String parentOption) {
        System.out.println(parentOption);
        System.out.println("-- Choose an option: [0, 1] --");
        System.out.println();
        System.out.println("0. Keep old job");
        System.out.println("1. Get this job");
    }

    // Navigation
    private int chooseJobMenu() {
        while (true) {
            int choice = scanner.nextInt();

            if (choice > 1 || choice < 0) {
                System.out.println("Invalid option! Try again.");
            } else {
                return choice;
            }
        }
    }

    // Helper
    private List<Action> showMenu() {
        drawOptions();
        return navigateOptions();
    }
}
