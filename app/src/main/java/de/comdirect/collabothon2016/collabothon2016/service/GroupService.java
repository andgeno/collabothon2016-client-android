package de.comdirect.collabothon2016.collabothon2016.service;


import de.comdirect.collabothon2016.collabothon2016.model.Group;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface GroupService {

    String ENDPOINT = "http://172.31.127.74:8090/"; // TODO IP vom REST Server hier eintragen

    @GET("groups/rank/{group-id}")
    Observable<Response<ResponseBody>> getGroup(@Path("group-id") Integer groupId);

}
