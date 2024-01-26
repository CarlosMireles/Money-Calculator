package software.ulpgc.moneyCalculator.currencyBeacon;

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
            return getListOFCurrencies(loadJson());
        } catch (IOException e) {
            return emptyList();
        }
    }

    private List<Currency> getListOFCurrencies(String json) {
        List<Currency> list = new ArrayList<>();
        for (JsonElement currency : getJsonArrayFromJson(json)) {
            Map<String, JsonElement> currencyJson = currencyJsonToMap(currency);
            list.add(new Currency(currencyJson.get("short_code").getAsString(), currencyJson.get("name").getAsString()));
        }
        return list;
    }

    private JsonArray getJsonArrayFromJson(String json){
        return new Gson().fromJson(json, JsonObject.class).get("response").getAsJsonArray();
    }

    private Map<String, JsonElement> currencyJsonToMap(JsonElement element){
        return element.getAsJsonObject().asMap();
    }

    private String loadJson() throws IOException {
        URL url = new URL(currencyBeaconAPI.urlCurrencies + currencyBeaconAPI.key);
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
