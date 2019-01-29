package gol.four.ldcc.gol.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("applies/")
    Call<ResponseBody> postApplies(@Field("apply_type") int applyType,
                                   @Field("container_no") int containerNo,
                                   @Field("confirm_idx") String confirm_idx,
                                   @Field("employee_idx") String pk,
                                   @Field("apply_state") int applyState);


}
