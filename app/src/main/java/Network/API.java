package Network;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/*
Retrofit 참조 : https://newland435.tistory.com/25
* */

public interface API {
    @GET("/")
    Call<ArrayList<JsonObject>> getList();
}
