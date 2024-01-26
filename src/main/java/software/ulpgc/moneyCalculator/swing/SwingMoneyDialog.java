package software.ulpgc.moneyCalculator.swing;

import software.ulpgc.moneyCalculator.model.Currency;
import software.ulpgc.moneyCalculator.model.CurrencyDialog;
import software.ulpgc.moneyCalculator.model.Money;
import software.ulpgc.moneyCalculator.model.MoneyDialog;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextComponent textField;
    private CurrencyDialog  currencyDialog;

    public SwingMoneyDialog(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        JPanel panel = new JPanel();
        panel.add(createLabel("Introduzca la cantidad de dinero: ", 20));
        panel.add(createAmountField());

        add(panel);
        add(createCurrencyDialog(currencies));
        return this;
    }

    private JLabel createLabel(String text, int size){
        JLabel labelText = new JLabel(text);
        labelText.setFont(new Font("Arial", Font.ITALIC, size));
        return labelText;
    }

    private JTextComponent createAmountField() {
        JTextField text = new JTextField();
        text.setColumns(8);

        this.textField = text;
        return text;
    }

    private Component createCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);

        this.currencyDialog = dialog;
        return dialog;
    }

    @Override
    public Money get() {
        return new Money(getAmountIntroduced(), currencyDialog.get());
    }

    private long getAmountIntroduced() {
        return toLong((textField.getText()));
    }

    private long toLong(String text) {
        return Long.parseLong(text);
    }
}
