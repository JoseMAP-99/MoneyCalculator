package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Scanner;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencyList;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class MoneyCalculator {
    public static void main(String[] args) throws IOException {        
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
    
    private Money money;
    private ExchangeRate exchange;
    private Currency currencyTo;
    private final CurrencyList currencyList;
    
    public MoneyCalculator() {
        this.currencyList = new CurrencyList();
    } 
    
    private void control() throws IOException{
        input();
        process();
        output();
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

    private void input() {
        System.out.println("Introduzca una cantidad: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        
        while(true){
            System.out.println("Introduce la divisa origen: ");
            Currency currency = currencyList.get(scanner.next().toUpperCase());
            money = new Money(amount, currency);
            if(currency != null) break;
            System.out.println("Divisa no conocida");
        }
        
        while(true){
            System.out.println("Introduzca la divisa destino: ");
            currencyTo = currencyList.get(scanner.next().toUpperCase());
            if(currencyTo != null) break;
            System.out.println("Divisa no conocida");
        }
    }

    private void process() throws IOException {
        exchange = getExchangeRate(money.getCurrency(), currencyTo);    
    }
    
    private void output() {
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol() + 
                " equivalen a " + money.getAmount()*exchange.getRate() + " " + 
                currencyTo.getSymbol()); 
    }  
}
