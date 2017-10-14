package golf.alphabet.com.alphabet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Hannes on 2017-10-14.
 */

public interface ApiService {

    @Headers("tenant: golf")
    @GET("schedule/{weekday}")
    public Call<List<Session>> getSessions(@Path("weekday") String weekday);
}
