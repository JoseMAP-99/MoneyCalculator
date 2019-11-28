package moneycalculator.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class Converter {
    
    private final Money money;
    private ExchangeRate exchange;
    private final Currency currencyTo;
    
    public Converter(Money money, Currency currencyTo) {
        this.money = money;
        this.currencyTo = currencyTo;
    }
    
    private ExchangeRate getExchangeRate(Currency from, Currency to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q="
                + from.getIsoCode() + '_' + to.getIsoCode() + 
                "&compact=ultra&apiKey=cfba020bc2a17499ee7e");
        
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(':')+1, line.indexOf('}'));
            return (new ExchangeRate(from, to, LocalDate.now(), Double.parseDouble(line1)));
        }
    }

    public void process() throws IOException {
        exchange = getExchangeRate(money.getCurrency(), currencyTo);    
    }
    
    public double output() {
        return (money.getAmount()*exchange.getRate());
    }  
}
