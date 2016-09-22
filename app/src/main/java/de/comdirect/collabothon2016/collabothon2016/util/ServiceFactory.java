package de.comdirect.collabothon2016.collabothon2016.util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import de.comdirect.collabothon2016.collabothon2016.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        final Retrofit restAdapter = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .client(client)
                .build();
        T service = restAdapter.create(clazz);
        return service;
    }

    private static class LoggingInterceptor implements Interceptor {
        private AtomicLong threadSafeRequestId = new AtomicLong(0);

        @Override
        public Response intercept(Chain chain) throws IOException {
            long requestId = threadSafeRequestId.incrementAndGet();

            Request request = chain.request();
            long t1 = System.nanoTime();
            Buffer sinkRequest = new Buffer();
            request.body().writeTo(sinkRequest);
            String contentRequest = sinkRequest.readUtf8();
            Log.d(BuildConfig.LOG_TAG, String.format("REST/SEND [%d] >>> %s @ [%s] Content (%d): [%s]",
                    requestId,
                    request.method(),
                    request.url(),
                    request.body().contentLength(),
                    contentRequest));

            Response response = chain.proceed(request);
            String contentResponse = response.body().string();
            long t2 = System.nanoTime();
            Log.d(BuildConfig.LOG_TAG, String.format("REST/RECV [%d] <<< %s @ [%s] after %.1f ms.%n%nHeaders (%d): [%n%s]%n%nContent (%d): [%n%s]",
                    requestId,
                    request.method(),
                    response.request().url(),
                    (t2 - t1) / 1e6d,
                    response.headers().size(),
                    response.headers(),
                    response.body().contentLength(),
                    contentResponse
            ));

            return response;
        }
    }

}
