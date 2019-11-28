package moneycalculator.model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {
    private final Map<String, Currency> currencies = new HashMap<>();

    public CurrencyList() {        
        add(new Currency("USD", "Dóla americano", "$"));
        add(new Currency("EUR", "Euro", "€"));
        add(new Currency("GBP", "Libra esterlina", "£"));
    } 
    
    public Map<String, Currency> getCurrencies(){
        return currencies;
    }
    
    public Currency get(String isoCode){
        return (currencies.get(isoCode.toUpperCase()));
    }
    
    private void add(Currency currency) {
        currencies.put(currency.getIsoCode(), currency);
    }
}
