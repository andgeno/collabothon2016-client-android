package de.comdirect.collabothon2016.collabothon2016.service;


import org.apache.commons.lang3.StringUtils;

import de.comdirect.collabothon2016.collabothon2016.util.ServiceFactory;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public class VotingService {
    private static final String ENDPOINT_SEBASTIAN = "http://172.31.127.74:8080/";
    private static final String ENDPOINT_ANDREAS = "http://172.31.124.112:8080/";
    private static final String ENDPOINT_ANDREAS_HOTSPOT = "http://192.168.43.27:8080/";

    public static final String ENDPOINT = ENDPOINT_ANDREAS_HOTSPOT;

    private static VotingService instance;
    private RestService service;

    public static VotingService getInstance() {
        return instance;
    }

    public static VotingService init(String endPoint) {
        if (StringUtils.trimToNull(endPoint) == null || !endPoint.endsWith("/")) {
            throw new IllegalStateException("Missing end point or no trailing forward slash.");
        }
        return instance = new VotingService(endPoint);
    }

    private VotingService(String endPoint) {
        service = ServiceFactory.createRetrofitService(VotingService.RestService.class, endPoint);
    }

    public RestService getService() {
        return service;
    }

    public interface RestService {
        @GET("voting/suggestions/group/{group-id}")
        Observable<Response<ResponseBody>> getVotes(@Path("group-id") int groupId);

        @GET("voting/leaderboard/group/{group-id}")
        Observable<Response<ResponseBody>> getLeaders(@Path("group-id") int groupId);
    }


}
