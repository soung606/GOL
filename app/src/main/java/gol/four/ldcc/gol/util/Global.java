package gol.four.ldcc.gol.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Global {

    private static Global instance = new Global();

    public static Global getInstance() {
        return instance;
    }

    private Global() {
    }

    String baseUrl = "http://54.180.142.153:8000/gol_server";
    public String getBaseUrl() {
        return baseUrl;
    }
}
