package com.xory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CryptoPriceChecker {

    private static final String BASE_URL = "https://api.bingx.com/api/v1/market/ticker";

    public static void main(String[] args) {
        try {
            String symbol = "BTCUSDT"; // Торговая пара

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BASE_URL + "?symbol=" + symbol)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    // Парсинг JSON-ответа
                    JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                    String lastPrice = jsonObject.get("lastPrice").getAsString();

                    System.out.println("Текущая цена " + symbol + ": " + lastPrice);
                } else {
                    System.err.println("Ошибка получения данных: " + response.code());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
