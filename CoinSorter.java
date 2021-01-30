/**
 * Class used to sort and exchange coins in different denominations
 * 
 * @version 18.11.2020
 */

import java.util.List;
import java.util.Collections;

public class CoinSorter {

    // attributes
    private String currency;
    private int minCoinIn;
    private int maxCoinIn;
    private List<Integer> coinList;

    /**
     * Constructor initialises CoinSorter with currency, minimum and maximum value
     * to exchange and initial list of coin denominations
     * 
     * @param currencyIn:   currency as string
     * @param minCoinInput: minimum value to exchange
     * @param maxCoinInput: maximum value to exchange
     * @param coins:        list of coin denominations
     * 
     */
    public CoinSorter(String currencyIn, int minCoinInput, int maxCoinInput, List<Integer> coins) {
        currency = currencyIn;
        minCoinIn = minCoinInput;
        maxCoinIn = maxCoinInput;
        coinList = coins;
        Collections.sort(coinList, Collections.reverseOrder());
    }

    // Getter and Setter Methods
    public void setCurrency(String currencyIn) {
        currency = currencyIn;
    }

    public void setMinCoinIn(int minCoinInput) {
        minCoinIn = minCoinInput;
    }

    public void setMaxCoinIn(int maxCoinInput) {
        maxCoinIn = maxCoinInput;
    }

    public String getCurrency() {
        return currency;
    }

    public int getMinCoinIn() {
        return minCoinIn;
    }

    public int getMaxCoinIn() {
        return maxCoinIn;
    }

    /**
     * returns denominations currently in use
     */
    public String printCoinList() {
        String denominations = coinList.toString();
        denominations = denominations.substring(1, denominations.length() - 1);
        String txt = "The current coin denominations are in circulation: " + denominations;
        return txt;
    }

    /**
     * Calculates the maximum number of coins of a coin type that can be exchanged
     * 
     * @param amount:   total value to exchange in pence
     * @param coinType: denomination for exchange
     * @return Returns a string with number of coins of certain coin type and
     *         remainder
     */
    public String coinCalculator(int amount, int coinType) {
        // Input Validation
        if (amount > maxCoinIn || amount < minCoinIn) {
            return "Invalid amount";
        }
        if (!coinList.contains(coinType)) {
            return "Denomination not included in coin list";
        }

        // Calculate
        int numberOfCoins = amount / coinType;
        int remainder = amount % coinType;
        return "A total of " + numberOfCoins + " x " + coinType
            + "p can be exchanged with a remainder of " + remainder + "p.";
    }

    /**
     * Calculates the maximum number of coins for each denomination without the
     * excluded coin type, prioritising higher denominations
     * 
     * @param amount:         total value to exchange in pence
     * @param coinTypeExclue: coin type to exclude
     * @return Returns a string with the number of coins and denominations
     */
    public String multiCoinCalculator(int amount, int coinTypeExclude) {
        // Input Validation
        if (amount > maxCoinIn || amount < minCoinIn) {
            return "Invalid amount";
        }
        if (!coinList.contains(coinTypeExclude)) {
            return "Denomination not included in coin list";
        }

        // Calculate
        String txt = "The coins exchanged are: ";
        int[] numberOfCoins = new int[coinList.size()];
        int[] remainder = new int[coinList.size()];
        int coin;
        int currentAmount = amount; // dynamic
        for (int i = 0; i < coinList.size(); i++) {
            // get current denomination
            coin = coinList.get(i);

            // check if coin shall be excluded
            if (coin == coinTypeExclude) {
                txt += "0 x " + coin + "p, ";
                continue;
            }
            // calculate number of coins and remainder
            numberOfCoins[i] = currentAmount / coin;
            remainder[i] = currentAmount % coin;

            // dynamically set the current amount to be current remainder
            currentAmount = remainder[i];
            txt += numberOfCoins[i] + " x " + coin + "p, ";
        }
        txt += "with a remainder of " + remainder[coinList.size() - 1] + "p.";
        return txt;

    }

    /**
     * returns curreny, min and max values
     */
    public String displayProgramConfigs() {
        String txt = "The current currency is " + currency + ", the minimum value is " + minCoinIn
                + " and the maximum is " + maxCoinIn + ".";
        return txt;
    }

}