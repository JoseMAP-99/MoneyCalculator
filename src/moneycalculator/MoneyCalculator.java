package moneycalculator;

import java.io.IOException;
import moneycalculator.model.CurrencyList;
import moneycalculator.view.MainViewer;

public class MoneyCalculator {
    public static void main(String[] args) throws IOException {        
        MainViewer mainViewer = new MainViewer();
        mainViewer.assignCurrencies(new CurrencyList());
        mainViewer.setVisible(true);
    }
}
