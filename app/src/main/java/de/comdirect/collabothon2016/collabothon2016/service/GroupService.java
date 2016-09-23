package de.comdirect.collabothon2016.collabothon2016.service;


import org.apache.commons.lang3.StringUtils;

import de.comdirect.collabothon2016.collabothon2016.util.ServiceFactory;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public class GroupService {
    private static final String ENDPOINT_SEBASTIAN = "http://172.31.127.74:8090/";
    private static final String ENDPOINT_ANDREAS = "http://172.31.124.112:8090/";

    public static final String ENDPOINT = ENDPOINT_ANDREAS;

    private static GroupService instance;
    private RestService service;

    public static GroupService getInstance() {
        return instance;
    }

    public static GroupService init(String endPoint) {
        if (StringUtils.trimToNull(endPoint) == null || !endPoint.endsWith("/")) {
            throw new IllegalStateException("Missing end point or no trailing forward slash.");
        }
        return instance = new GroupService(endPoint);
    }

    private GroupService(String endPoint) {
        service = ServiceFactory.createRetrofitService(GroupService.RestService.class, endPoint);
    }

    public RestService getService() {
        return service;
    }

    public interface RestService {

        @GET("groups/")
        Observable<Response<ResponseBody>> getGroups();

        @GET("rating/groups/rank/{group-id}")
        Observable<Response<ResponseBody>> getGroup(@Path("group-id") int groupId);
    }


}
