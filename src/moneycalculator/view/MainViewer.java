package moneycalculator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import moneycalculator.control.Converter;
import moneycalculator.model.CurrencyList;
import moneycalculator.model.Money;

public class MainViewer extends JFrame {
    
    private JButton exchangeButton;
    private JTextField inputText;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField outputText;
    private JComboBox<String> ratesInputCombo;
    private JComboBox<String> ratesOutCombo;
    private CurrencyList currencies;

    public MainViewer() {
        initComponents();   
    }
      
    public void assignCurrencies(CurrencyList list){
        this.currencies = list;  
        addItems(ratesInputCombo);
        ratesInputCombo.setSelectedItem("EUR");
        addItems(ratesOutCombo);
        ratesOutCombo.removeItem("EUR");
        ratesOutCombo.setSelectedItem("USD");
    }    
        
    private void addItems(JComboBox inOut){
        inOut.removeAllItems();
        for (String isoCode : this.currencies.getCurrencies().keySet()) {
            inOut.addItem(isoCode); 
        }
    }
    
    private double stringToDouble(String x){
        if(x.matches("^\\d+(,|.)?\\d*")){
            return (Double.parseDouble(x.replace(',', '.')));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please, input correct data", "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
        
    private void initComponents() {

        exchangeButton = new JButton();
        outputText = new JTextField();
        ratesInputCombo = new JComboBox<>();        
        ratesOutCombo = new JComboBox<>();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        inputText = new JTextField();

        jLabel1.setText("Input rates");
        jLabel2.setText("Output rates");

        inputText.setColumns(8);
        inputText.setHorizontalAlignment(JTextField.CENTER);
                
        outputText.setEditable(false);
        outputText.setColumns(8);
        outputText.setHorizontalAlignment(JTextField.CENTER);
                        
        exchangeButton.setText("Exchange");
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(inputText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Please, input data", "ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    String currencyFrom = ratesInputCombo.getSelectedItem().toString();
                    String currencyTo = ratesOutCombo.getSelectedItem().toString();
                    
                    double amount = stringToDouble(inputText.getText());
                    
                    Money money = new Money(amount, currencies.get(currencyFrom));

                    Converter converter = new Converter(money, currencies.get(currencyTo));
                    converter.process();

                    outputText.setText(converter.output());
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(inputText, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ratesInputCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exchangeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ratesOutCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outputText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(15, 15, 15)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratesInputCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exchangeButton)
                    .addComponent(ratesOutCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);          
        setLocationRelativeTo(null); 
        this.setTitle("MoneyCalculator");
        pack();
    }  
}
