package machine;
import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int waterSupply = 400;
    static int milkSupply = 540;
    static int coffeeBeansSupply = 120;
    static int cupsSupply = 9;
    static int money = 550;

    public static void showSupply(){
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n", waterSupply);
        System.out.printf("%d of milk%n", milkSupply);
        System.out.printf("%d of coffee beans%n", coffeeBeansSupply);
        System.out.printf("%d of disposable cups%n", cupsSupply);
        System.out.printf("%d of money%n", money);
        actionMenu();
    }

    public static boolean waterCheck(String coffee){
        switch (coffee){
            case "espresso":
                return waterSupply < 250;
            case "latte":
                return waterSupply < 350;
            case "cappuccino":
                return waterSupply < 200;
        }
        return true;
    }

    public static boolean milkCheck(String coffee){
        switch (coffee){
            case "latte":
                return milkSupply >= 75;
            case "cappuccino":
                return milkSupply >= 100;
        }
        return false;
    }

    public static boolean beansCheck(String coffee){
        switch (coffee){
            case "espresso":
                return coffeeBeansSupply >= 16;
            case "latte":
                return coffeeBeansSupply >= 20;
            case "cappuccino":
                return coffeeBeansSupply >= 12;
        }
        return false;
    }

    public static boolean supplyCheck(String coffee) {
        switch (coffee) {
            case "espresso":
                if (waterCheck(coffee)) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                } else if (!beansCheck(coffee)) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                } else if (cupsSupply == 0) {
                    System.out.println("Sorry, not enough cups!");
                    return false;
                }
                return true;
            case "latte":
            case "cappuccino":
                if (waterCheck(coffee)) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                } else if (!milkCheck(coffee)) {
                    System.out.println("Sorry, not enough milk!");
                } else if (!beansCheck(coffee)) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                } else if (cupsSupply == 0) {
                    System.out.println("Sorry, not enough cups!");
                    return false;
                }
                return true;
        }
        return true;
    }

    public static void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();
        switch (choice) {
            case "1":
                if (supplyCheck("espresso")){
                    System.out.println("I have enough resources, making you a coffee!");
                    waterSupply -= 250;
                    coffeeBeansSupply -= 16;
                    money += 4;
                    cupsSupply--;
                }
                actionMenu();
                break;
            case "2":
                if (supplyCheck("latte")){
                    System.out.println("I have enough resources, making you a coffee!");
                    waterSupply -= 350;
                    milkSupply -= 75;
                    coffeeBeansSupply -= 20;
                    money += 7;
                    cupsSupply--;
                }
                actionMenu();
                break;
            case "3":
                if (supplyCheck( "cappuccino")) {
                    System.out.println("I have enough resources, making you a coffee!");
                    waterSupply-= 200;
                    milkSupply -= 100;
                    coffeeBeansSupply -= 12;
                    money += 6;
                    cupsSupply--;
                }
                actionMenu();
                break;
            case "back":
                actionMenu();
        }
    }

    public static void fill(){
        System.out.println("Write how many ml of water do you want to add:");
        waterSupply += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milkSupply += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeansSupply += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cupsSupply += scanner.nextInt();
        actionMenu();
    }

    public static void take(){
        System.out.printf("I gave you $%d%n", money);
        money = 0;
        actionMenu();
    }

    public static void actionMenu(){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();
        switch (action) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                showSupply();
                break;
            case "exit":
        }
    }

    public static void main(String[] args) {
        actionMenu();
    }
}
