package moneycalculator;

import moneycalculator.model.CurrencyList;
import moneycalculator.model.ReadCurrencies;
import moneycalculator.view.MainViewer;

public class MoneyCalculator {
    public static void main(String[] args) {
        
        ReadCurrencies readCurrencies = new ReadCurrencies("currencies.txt");
        CurrencyList currencies = new CurrencyList(readCurrencies.readFile());
        
        MainViewer mainViewer = new MainViewer();
        mainViewer.assignCurrencies(currencies);
        mainViewer.setVisible(true);
    }
}
