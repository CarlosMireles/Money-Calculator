package software.ulpgc.moneyCalculator.fcs;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.moneyCalculator.model.Currency;
import software.ulpgc.moneyCalculator.model.CurrencyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class currencyBeaconCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        try {
            return toList(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> toList(String json) {
        List<Currency> list = new ArrayList<>();
        JsonArray symbols = new Gson().fromJson(json, JsonObject.class).get("response").getAsJsonArray();
        for (JsonElement symbol : symbols) {
            Map<String, JsonElement> currencyJson = symbol.getAsJsonObject().asMap();
            list.add(new Currency(currencyJson.get("short_code").getAsString(), currencyJson.get("name").getAsString()));
        }
        return list;
    }

    private String loadJson() throws IOException {
        URL url = new URL(currencyBeaconAPI.urlCurrencies + currencyBeaconAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
