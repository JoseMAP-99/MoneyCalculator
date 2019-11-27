package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    public static void main(String[] args) throws IOException {        
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
    
    private double amount;
    private double exchangeRate;
    private String currencyFrom;
    private String currencyTo;
    
    private void control() throws IOException{
        input();
        process();
        output();
    }
    
    private double getExchangeRaye(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q="
                + from + '_' + to + "&compact=ultra&apiKey=cfba020bc2a17499ee7e");
        
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(':')+1, line.indexOf('}'));
            return Double.parseDouble(line1);
        }
    }

    private void input() {
        System.out.println("Introduzca una cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();
        
        System.out.println("Introduce la divisa origen: ");
        currencyFrom = scanner.next();
        
        System.out.println("Introduzca la divisa destino: ");
        currencyTo = scanner.next();
    }

    private void process() throws IOException {
        exchangeRate = getExchangeRaye(currencyFrom, currencyTo);
    }
    
    private void output() {
        System.out.println(amount + " " + currencyFrom + " equivalen a " + 
                amount*exchangeRate + " " + currencyTo); 
    }  
}
