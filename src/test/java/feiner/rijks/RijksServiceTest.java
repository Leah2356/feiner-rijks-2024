package feiner.rijks;

import feiner.rijks.json.*;
import org.junit.jupiter.api.Test;
import com.andrewoid.ApiKey;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RijksServiceTest {
    @Test
    void pageSearch() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();
        int page = 1;
        // when
        ArtObjectCollection artObjectCollection = service.pageSearch(keyString, page).blockingGet();
        // then
        assertNotNull(artObjectCollection.artObjects);
        ArtObject artObject = artObjectCollection.artObjects[0];
        assertNotNull(artObject);
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.principalOrFirstMaker);
        assertNotNull(artObject.webImage);
        WebImage webImage = artObject.webImage;
        assertNotNull(webImage.url);
        assertNotNull(webImage.guid);
        assertNotEquals(0, webImage.width);
        assertNotEquals(0, webImage.height);
    }

    @Test
    void querySearch() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();
        String query = "a";
        int page = 0;
        // when
        ArtObjectCollection artObjectCollection = service.querySearch(keyString, page, query).blockingGet();
        // then
        assertNotNull(artObjectCollection.artObjects);
        ArtObject artObject = artObjectCollection.artObjects[0];
        assertNotNull(artObject);
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.principalOrFirstMaker);
        assertNotNull(artObject.webImage);
        WebImage webImage = artObject.webImage;
        assertNotNull(webImage.url);
        assertNotNull(webImage.guid);
        assertNotEquals(0, webImage.width);
        assertNotEquals(0, webImage.height);
    }

    @Test
    void artistSearch() {
        // given
        RijksService service = new RijksServiceFactory().getService();
        String artist = "Vermeer";
        int page = 0;
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        // when
        ArtObjectCollection artObjectCollection = service.artistSearch(keyString, page, artist).blockingGet();
        // then
        assertNotNull(artObjectCollection.artObjects);
        ArtObject artObjects = artObjectCollection.artObjects[0];
        assertNotNull(artObjects);
        assertNotNull(artObjects.title);
        assertNotNull(artObjects.longTitle);
        assertNotNull(artObjects.principalOrFirstMaker);
        assertNotNull(artObjects.webImage);
        WebImage webImage = artObjects.webImage;
        assertNotNull(webImage.url);
        assertNotNull(webImage.guid);
        assertNotEquals(0, webImage.width);
        assertNotEquals(0, webImage.height);
    }
}
