package gol.four.ldcc.gol.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Retrofit singleton 생성
* */
public class GolService {
    private static GolService instance;
    //retrofit setting
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.180.142.153:8000/gol_server/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    API api = retrofit.create(API.class);

    public static GolService instance() {
        if (instance == null) instance = new GolService();
        return instance;
    }

    public API getService() {
        return api;
    }
}
