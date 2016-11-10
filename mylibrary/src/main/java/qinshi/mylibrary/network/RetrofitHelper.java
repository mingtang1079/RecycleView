package qinshi.mylibrary.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import qinshi.mylibrary.BuildConfig;
import qinshi.mylibrary.rx.ApiInterface;
import qinshi.mylibrary.utils.ToastUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class RetrofitHelper {

    private ApiInterface mApiInterface;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    private void init() {
        initOkHttp();
        initRetrofit();

    }

    private RetrofitHelper() {
        init();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        // 统一添加的Header
//        httpClient.networkInterceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder()
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader(APP_ID_NAME, APP_ID_VALUE)
//                        .addHeader(API_KEY_NAME, API_KEY_VALUE)
//                        .addHeader(SESSION_TOKEN_KEY, UserInfoKeeper.getToken())
//                        .build();
//                return chain.proceed(request);
//            }
//        });
        // 缓存
//        File cacheFile = new File(SDCardUtils.cacheDir, "/NetCache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//        Interceptor cacheInterceptor = chain -> {
//            Request request = chain.request();
////            if (!NetUtils.isConnected(BaseApplication.getInstance())) {
////                request = request.newBuilder()
////                        .cacheControl(CacheControl.FORCE_CACHE)
////                        .build();
////            }
//            Response response = chain.proceed(request);
//            if (Util.isNetworkConnected(BaseApplication.getInstance())) {
//                int maxAge = 0;
//                // 有网络时 设置缓存超时时间0个小时
//                response.newBuilder()
//                        .header("Cache-Control", "public, max-age=" + maxAge)
//                        .build();
//            } else {
//                // 无网络时，设置超时为4周
//                int maxStale = 60 * 60 * 24 * 28;
//                response.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .build();
//            }
//            return response;
//        };
//        builder.cache(cache).addInterceptor(cacheInterceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);


    }

    public static void disposeFailureInfo(Throwable t) {
        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                t.toString().contains("UnknownHostException")) {
            ToastUtils.showShort("网络问题");
        } else if (t.toString().contains("API没有")) {

            ToastUtils.showShort("错误: " + t.getMessage());
        }

    }


//
//    public Observable<Weather> fetchWeather(String city) {
//
//        return apiService.mWeatherAPI(city, C.KEY)
//                .flatMap(weatherAPI -> {
//                    String status = weatherAPI.mHeWeatherDataService30s.get(0).status;
//                    if ("no more requests".equals(status)) {
//                        return Observable.error(new RuntimeException("/(ㄒoㄒ)/~~,API免费次数已用完"));
//                    } else if ("unknown city".equals(status)) {
//                        return Observable.error(new RuntimeException(String.format("API没有%s", city)));
//                    }
//                    return Observable.just(weatherAPI);
//                })
//                .map(weatherAPI -> weatherAPI.mHeWeatherDataService30s.get(0))
//                .compose(RxHelper.rxSchedulerHelper());
//    }
//
//    public Observable<VersionAPI> fetchVersion() {
//        return apiService.mVersionAPI(C.API_TOKEN).compose(RxHelper.rxSchedulerHelper());
//    }

}
