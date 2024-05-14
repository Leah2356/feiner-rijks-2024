package json;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RijksService {
    @GET("/api/en/collection?key={api-key}&p={page}")
    Single<ArtObjects> pageSearch(@Query("page")int page);

// https://www.rijksmuseum.nl/api/nl/collection?key=[api-key]&involvedMaker=Rembrandt+van+Rijn


    @GET("/api/en/collection?key={api-key}&p={page}&q={query}")
    Single<ArtObjects> querySearch(@Query("page")int page, @Query("query") String query);

    @GET("/api/en/collection?key={api-key}&p={page}&involvedMaker={artist}")
    Single<ArtObjects> artistSearch(@Query("page")int page, @Query("artist") String artist);
}
