package moneycalculator;

import java.io.IOException;
import moneycalculator.model.CurrencyList;
import moneycalculator.model.ReadCurrencies;
import moneycalculator.view.MainViewer;

public class MoneyCalculator {
    public static void main(String[] args) throws IOException {        
        MainViewer mainViewer = new MainViewer();
        ReadCurrencies readCurrencies = new ReadCurrencies("currencies.txt");
        readCurrencies.fillCurrencies();
        mainViewer.assignCurrencies(new CurrencyList());
        mainViewer.setVisible(true);
    }
}
