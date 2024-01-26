package software.ulpgc.moneyCalculator.model;

import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
    public static ExchangeRate ExchangeRateNull(Currency from, Currency to, LocalDate date){
        return new ExchangeRate(from, to, date, 0);
    }
}
