package software.ulpgc.moneyCalculator.currencyBeacon;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneyCalculator.model.Currency;
import software.ulpgc.moneyCalculator.model.ExchangeRate;
import software.ulpgc.moneyCalculator.model.ExchangeRateLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

import static java.util.Collections.emptyList;

public class currencyBeaconExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from, to, LocalDate.now(), getRate(from.code(), to.code()));
        } catch (IOException e) {
            return ExchangeRate.ExchangeRateNull(from, to, LocalDate.now());
        }
    }

    private double getRate(String currencyBaseCode, String currencyWantedCode) throws IOException {
        Map<String, JsonElement> rates = getRatesForCurrency(loadJson(currencyBaseCode));
        return rates.get(currencyWantedCode).getAsDouble();
    }

    private Map<String, JsonElement> getRatesForCurrency(String json){
        Map<String, JsonElement> rates = new Gson().
                fromJson(json, JsonObject.class).
                get("response").
                getAsJsonObject().
                get("rates").
                getAsJsonObject().
                asMap();
        return rates;
    }

    private String loadJson(String from) throws IOException {
        URL url = new URL(setupUrl(from));
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private String setupUrl(String from){
        return currencyBeaconAPI.urlConvert + currencyBeaconAPI.key + "&base=" + from;
    }
}
