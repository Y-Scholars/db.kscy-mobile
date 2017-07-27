package org.kscy.db.kscydatabase.module;

import org.kscy.db.kscydatabase.model.AuthResult;
import org.kscy.db.kscydatabase.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pyh42 on 2017-07-27.
 */

public interface Search {
    @Headers("Content-Type: application/json")
    @GET("_search")
    Call<SearchResult> search(@Query("size") int size, @Query("q") String q);
}
