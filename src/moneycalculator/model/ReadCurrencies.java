package moneycalculator.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadCurrencies {
    private final String path;    
    private final File file;
    private String gsonString;
    
    public ReadCurrencies(String path){
        this.path = path;
        file = new File(this.path);       
    }
    
    public String readFile() {
        byte[] data;
        try (FileInputStream fis = new FileInputStream(file)) {
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            gsonString = new String(data, "UTF-8");
        }catch(IOException e){
            System.out.println("ERROR::readFile: " + e.getMessage());
        }
        return gsonString;
    }
}
