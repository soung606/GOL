package gol.four.ldcc.gol.network;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import gol.four.ldcc.gol.model.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
Retrofit 참조 : https://newland435.tistory.com/25
* */

public interface API {
    @GET("login/")
    Call<ArrayList<JsonObject>> login(@Query("id") String id, @Query("password")String password, @Query("token") String token);

    @FormUrlEncoded
    @POST("login/")
    Call<ArrayList<JsonObject>> postLogin(@Field("id") String id, @Field("password") String password, @Field("token") String token);

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
    Call<ArrayList<JsonObject>> getApplies(@Query("apply_state") int apply_state);

    @GET("applies/")
    Call<ArrayList<JsonObject>> getApplies();

    @GET("applies/")
    Call<ArrayList<JsonObject>> getNameApplies(@Query("employee_idx__name") String name);

    @FormUrlEncoded
    @PUT("employees/{pk}/")
    Call<JsonObject> changeState(@Path("pk") String pk,
                                 @Field("login_id")String loginId,
                                 @Field("password")String pw,
                                 @Field("name") String name,
                                 @Field("authority") String auth,
                                 @Field("token") String token);

    @POST("")
    Call<ResponseBody> postEmployee(@Query("loginid")String loginId,
                                    @Query("password")String password,
                                    @Query("name") String name,
                                    @Query("auth")int auth);
    @FormUrlEncoded
    @POST("applies/")
    Call<ResponseBody> postApplies(@Field("apply_type") int applyType,
                                   @Field("container_no") int containerNo,
                                   @Field("confirm_idx") String confirm_idx,
                                   @Field("employee_idx") String pk,
                                   @Field("apply_state") int applyState);

    @FormUrlEncoded
    @POST("apply/")
    Call<JsonObject> changeDoorGrant(@Field("confirm_idx") String cidx,
                                     @Field("employee_idx") String edix,
                                     @Field("row") int row,
                                     @Field("grant") int grant);

    


}
