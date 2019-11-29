package moneycalculator.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private ExchangeRate getExchangeRate(Currency from, Currency to) {
        URL url;
        String value = null;
        try {            
            url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q="
                        + from.getIsoCode() + '_' + to.getIsoCode() +
                        "&compact=ultra&apiKey=cfba020bc2a17499ee7e");
                        
            URLConnection connection = url.openConnection();
            
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            value = line.substring(line.indexOf(':')+1, line.indexOf('}'));               
            
        } catch (MalformedURLException ex){
            System.out.println("ERROR::getExchangeRate::MalformedURL: " + ex.getMessage());
        }catch (IOException ex) {
            System.out.println("ERROR::getExchangeRate::IOException: " + ex.getMessage());
        } 
        return (new ExchangeRate(from, to, LocalDate.now(), Double.parseDouble(value)));
    }

    public void process() {
        exchange = getExchangeRate(money.getCurrency(), currencyTo);    
    }
    
    public String output() {
        return (String.valueOf(Math.round(calculate()*1e4d)/1e4d));
    }  
    
    private double calculate(){
        return (money.getAmount()*exchange.getRate());
    }
}
