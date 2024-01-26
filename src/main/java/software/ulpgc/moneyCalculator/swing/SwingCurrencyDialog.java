package software.ulpgc.moneyCalculator.swing;

import software.ulpgc.moneyCalculator.model.Currency;
import software.ulpgc.moneyCalculator.model.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private JComboBox<Currency> currencySelector;

    public SwingCurrencyDialog(){}
    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(createLabel("Divisa: ", 14));
        add(createCurrencySelector(currencies));
        return this;
    }

    private JLabel createLabel(String text, int size){
        JLabel labelText = new JLabel(text);
        labelText.setFont(new Font("Arial", Font.ITALIC, size));
        return labelText;
    }

    private Component createCurrencySelector(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        for (Currency currency : currencies) selector.addItem(currency);
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
