package de.comdirect.collabothon2016.collabothon2016.service;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GroupService {

    String ENDPOINT = "http://127.0.0.1"; // TODO IP vom REST Server hier eintragen

    @GET("group")
    Response<ResponseBody> getGroup(@Query("groupId") Integer groupId);

}
