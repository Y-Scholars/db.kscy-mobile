package org.kscy.db.kscydatabase.module;

import org.kscy.db.kscydatabase.model.AuthResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pyh42 on 2016-12-09.
 */

public interface Auth {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("auth")
    Call<AuthResult> auth(@Field("email") String email, @Field("password") String password);
}
