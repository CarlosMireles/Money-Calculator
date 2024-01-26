package software.ulpgc.moneyCalculator.swing;

import software.ulpgc.moneyCalculator.model.Money;
import software.ulpgc.moneyCalculator.model.MoneyDisplay;

import javax.swing.JLabel;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        this.setFont(new Font("Arial", Font.BOLD, 50));
        this.setText(money.toString());
    }
}
