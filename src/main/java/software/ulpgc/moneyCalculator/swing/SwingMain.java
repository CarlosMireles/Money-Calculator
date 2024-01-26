package software.ulpgc.moneyCalculator.swing;



import software.ulpgc.moneyCalculator.currencyBeacon.currencyBeaconCurrencyLoader;
import software.ulpgc.moneyCalculator.currencyBeacon.currencyBeaconExchangeRateLoader;
import software.ulpgc.moneyCalculator.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog  moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();

        List<Currency> currencies = new currencyBeaconCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new currencyBeaconExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
    }

    public SwingMain() throws HeadlessException{
        this.setTitle("Money Calculator");
        this.setSize(1080,556);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel mainPanel = new mainPanel();

        mainPanel.addToPanel(createMoneyDialog(), 0, 0);
        mainPanel.addToPanel(createCurrencyDialog(), 0, 1);
        mainPanel.addToPanel(toolbar(), 0, 2);
        mainPanel.addToPanel(createMoneyDisplay(), 0, 10);

        this.add(mainPanel);
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }


    private Component toolbar(){
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("exchange money").execute());
        return button;
    }

    private void add(String name, Command command){
        commands.put(name, command);
    }

    private MoneyDialog moneyDialog() {
        return moneyDialog;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private static class mainPanel extends JPanel{
        private final GridBagConstraints gridBagConstraints;

        public mainPanel() {
            this.setLayout(new GridBagLayout());
            this.setUI(new ImagePanelUI("src/main/resources/c.jpg"));
            this.gridBagConstraints = createGridBagConstraint();
        }

        private void setGBC(int column, int row) {
            gridBagConstraints.gridx = column;
            gridBagConstraints.gridy = row;
        }

        private GridBagConstraints createGridBagConstraint(){
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 20, 20, 20);
            return gbc;
        }

        public void addToPanel(Component component, int column, int row){
            setGBC(column, row);
            this.add(component, gridBagConstraints);
        }
    }
}
