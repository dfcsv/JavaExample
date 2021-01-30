
/**
 * Class used to test the CoinSorter Class
 * 
 * @version 18.11.2020
 */

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class TestCoinSorter {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the Coin Sorter
        String currency = "£";
        int min = 0;
        int max = 10000;
        List<Integer> coinList = Arrays.asList(10, 20, 50, 100, 200);
        CoinSorter c = new CoinSorter(currency, min, max, coinList);

        // Textual Menu
        char choice = '6';
        do {
            System.out.println("***Coin Sorter - Main Menu***");
            System.out.println("1 - Coin calculator");
            System.out.println("2 - Multiple coin calculator");
            System.out.println("3 - Print coin list");
            System.out.println("4 - Set details");
            System.out.println("5 - Display program configurations");
            System.out.println("6 - Quit the program");
            System.out.println();
            System.out.print("Enter a choice [1-6]: ");
            choice = keyboard.next().charAt(0);
            System.out.println();
            switch (choice) {
                case '1':
                    option1(c);
                    break;
                case '2':
                    option2(c);
                    break;
                case '3':
                    option3(c);
                    break;
                case '4':
                    option4(c);
                    break;
                case '5':
                    option5(c);
                    break;
                case '6':
                    break;
                default:
                    System.out.println("1-6 only");
            }
        } while (choice != '6');
    }

    // 1 - coin calculator
    public static void option1(CoinSorter c) {
        // amount
        System.out.print("Enter amount: ");
        int amount = keyboard.nextInt();
        System.out.println();
        if (amount < c.getMinCoinIn() || amount > c.getMaxCoinIn()) {
            System.out.println("Invalid amount");
            System.out.println();
            return;
        }

        // coin type
        System.out.print("Enter coin type: ");
        int coinType = keyboard.nextInt();
        System.out.println();
        if (coinType < 0) {
            System.out.println("Invalid coin");
            System.out.println();
            return;
        }

        // run coin calculator
        System.out.println(c.coinCalculator(amount, coinType));
        System.out.println();

    }

    // 2 - multiple coin calculator
    public static void option2(CoinSorter c) {
        // amount
        System.out.print("Enter amount: ");
        int amount = keyboard.nextInt();
        System.out.println();
        if (amount < c.getMinCoinIn() || amount > c.getMaxCoinIn()) {
            System.out.println("Invalid amount");
            System.out.println();
            return;
        }

        // coin type
        System.out.print("Enter coin type to exclude: ");
        int coinType = keyboard.nextInt();
        System.out.println();
        if (coinType < 0) {
            System.out.println("Invalid coin");
            System.out.println();
            return;
        }

        // run multiple coin calculator
        System.out.println(c.multiCoinCalculator(amount, coinType));
        System.out.println();

    }

    // 3 - print coin list
    public static void option3(CoinSorter c) {
        System.out.println(c.printCoinList());
        System.out.println();
    }

    // 4 - set details
    public static void option4(CoinSorter c) {
        char choice = '4';
        do {
            System.out.println("***Set Details Sub-Menu***");
            System.out.println();
            System.out.println("1 - Set Currency");
            System.out.println("2 - Set minimum coin input value");
            System.out.println("3 - Set maximum coin input value");
            System.out.println("4 - Return to main menu");
            System.out.println();
            System.out.print("Enter a choice [1-4]: ");
            choice = keyboard.next().charAt(0);
            System.out.println();
            switch (choice) {
                case '1':
                    System.out.println("Current Currency: " + c.getCurrency());
                    System.out.println();
                    System.out.print("Enter currency: ");
                    String currency = keyboard.next();
                    if (currency.length() <= 10) {
                        c.setCurrency(currency);
                        System.out.println("Currency is set to " + c.getCurrency());
                    } else {
                        System.out.println("Currency string is too long");
                    }
                    System.out.println();
                    break;
                case '2':
                    System.out.println("Current minimum: " + c.getMinCoinIn());
                    System.out.println();
                    System.out.print("Enter minimum: ");
                    int min = keyboard.nextInt();
                    if (min > 0 && min < c.getMaxCoinIn()) {
                        c.setMinCoinIn(min);
                        System.out.println("Minimum is set to " + c.getMinCoinIn());
                    } else {
                        System.out.println("Invalid minimum value");
                    }
                    System.out.println();
                    break;
                case '3':
                    System.out.println("Current maximum: " + c.getMaxCoinIn());
                    System.out.println();
                    System.out.print("Enter maximum: ");
                    int max = keyboard.nextInt();
                    if (max > c.getMinCoinIn() && max <= 10000) {
                        c.setMaxCoinIn(max);
                        System.out.println("Maximum is set to " + c.getMaxCoinIn());
                    } else {
                        System.out.println("Invalid maximum value");
                    }
                    System.out.println();
                    break;
                case '4':
                    break;
                default:
                    System.out.println("1-4 only");
            }
        } while (choice != '4');
    }

    // 5 - display program configurations
    public static void option5(CoinSorter c) {
        System.out.println(c.displayProgramConfigs());
        System.out.println();
    }
}
