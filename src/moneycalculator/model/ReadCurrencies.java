package moneycalculator.model;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadCurrencies {
    private final String path;
    private String gsonString;
    private final File file;
    private final Gson gson;
    
    public ReadCurrencies(String path){
        this.path = path;
        file = new File(this.path);
        readFile();
        gson = new Gson();        
    }
    
    private void readFile() {
        byte[] data;
        try (FileInputStream fis = new FileInputStream(file)) {
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            gsonString = new String(data, "UTF-8");
        }catch(IOException e){
            System.out.println("ERROR::readFile: " + e.getMessage());
        }
    }
    
    public void fillCurrencies(){
        Currency currencies = gson.fromJson(gsonString, Currency.class);
        System.out.println("Name:" + currencies.getName());
        System.out.println("Code:" + currencies.getIsoCode());
        System.out.println("Symbol:" + currencies.getSymbol());        
    }
}
