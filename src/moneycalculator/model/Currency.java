package moneycalculator.model;

public class Currency {
    
    private final String currencyName;    
    private final String currencySymbol;
    private final String id;

    public Currency(String currencyName, String currencySymbol, String id) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

    public String getIsoCode() {
        return this.id;
    }

    public String getName() {
        return this.currencyName;
    }

    public String getSymbol() {
        return this.currencySymbol;
    }
    
}
