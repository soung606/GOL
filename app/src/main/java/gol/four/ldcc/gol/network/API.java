package gol.four.ldcc.gol.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*
Retrofit 참조 : https://newland435.tistory.com/25
* */

public interface API {
    @GET("login/")
    Call<ArrayList<JsonObject>> login(@Query("id") String id, @Query("password")String password);

    @GET("employees/")
    Call<ArrayList<JsonObject>> getEmployee();

    @GET("lockers/")
    Call<ArrayList<JsonObject>> getLockers();

    @GET("materials/")
    Call<ArrayList<JsonObject>> getMaterials();

    @GET("material_histories/")
    Call<ArrayList<JsonObject>> getMaterialHistory();

    @GET("alert_histories/")
    Call<ArrayList<JsonObject>> getAlertHistories();

    @GET("notices/")
    Call<ArrayList<JsonObject>> getNotices();

    @GET("applies/")
    Call<ArrayList<JsonObject>> getApplies();

    @POST("")
    Call<ResponseBody> postEmployee(@Query("loginid")String loginId,
                                    @Query("password")String password,
                                    @Query("name") String name,
                                    @Query("auth")int auth);
}
