package gol.four.ldcc.gol.Network;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
Retrofit 참조 : https://newland435.tistory.com/25
* */

public interface API {
    @GET("login/")
    Call<ArrayList<JsonObject>> login(@Query("id") String id, @Query("password")String password);

    @GET("employee/")
    Call<ArrayList<JsonObject>> getEmployee();

    @GET("")
    Call<List<JsonObject>> getHistory();

    @POST("")
    Response<JsonObject> postEmployee(String loginId, String password, String name, int auth);
}
