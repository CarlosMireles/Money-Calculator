package software.ulpgc.moneyCalculator.model;

public class ExchangeMoneyCommand implements Command{
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public int execute() {
        try {
            Money money = moneyDialog.get();
            Currency currencyWanted = currencyDialog.get();

            ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currencyWanted);
            Money moneyResultant = new Money((long) (money.amount()* exchangeRate.rate()), currencyWanted);

            moneyDisplay.show(moneyResultant);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
}
