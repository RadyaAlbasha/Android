package android.firebase.labs.worldpopulationretrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("tutorial/jsonparsetutorial.txt")
    Call<JsonResponse> getJSON();
}
