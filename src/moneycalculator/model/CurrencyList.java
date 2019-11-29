package moneycalculator.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyList {
    private Map<String, Currency> currencies = new HashMap<>();
    private final String gsonCurrencies;
    private final Gson gson;

    public CurrencyList(String gsonCurrencies) {    
        this.gsonCurrencies = gsonCurrencies;
        gson = new Gson();
        init();
    } 
    
    public Map<String, Currency> getCurrencies(){
        Map<String,Currency> sorted = new TreeMap<>(currencies);
        return sorted;
    }
    
    public Currency get(String isoCode){
        return (currencies.get(isoCode.toUpperCase()));
    }

    private void init() {
        Type map = new TypeToken<Map<String, Currency>>() {}.getType();
        currencies = gson.fromJson(gsonCurrencies, map);
    }
}
