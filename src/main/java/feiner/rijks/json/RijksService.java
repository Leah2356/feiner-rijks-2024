package feiner.rijks.json;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RijksService {
    @GET("/api/en/collection")
    Single<ArtObjectCollection> pageSearch(
            @Query("key") String apiKey,
            @Query("p") int page
    );

    // https://www.rijksmuseum.nl/api/nl/collection?key=[api-key]&involvedMaker=Rembrandt+van+Rijn


    @GET("/api/en/collection")
    Single<ArtObjectCollection> querySearch(
            @Query("key") String apiKey,
            @Query("page")int page,
            @Query("query") String query);

    @GET("/api/en/collection")
    Single<ArtObjectCollection> artistSearch(
            @Query("key") String apiKey,
            @Query("page")int page,
            @Query("artist") String artist);
}
