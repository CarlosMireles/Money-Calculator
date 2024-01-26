package software.ulpgc.moneyCalculator;

import software.ulpgc.moneyCalculator.model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog  moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        Main main = new Main();

        List<Currency> currencies = new ArrayList<>();// Currencyloader.load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new MockExchangeRateLoader(),
                main.moneyDisplay());
        main.add("exchange money", command);
        main.setVisible(true);
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
}
