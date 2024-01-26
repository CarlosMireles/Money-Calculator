package software.ulpgc.moneyCalculator.model;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
