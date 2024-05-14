import json.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RijksServiceTest {
@Test
    void pageSearch() {
    // given
    RijksService service = new RijksServiceFactory().getService();
    int page = 0;
    // when
    ArtObjects artObjects = service.pageSearch(page).blockingGet();
    // then
    ArtObject artObject = artObjects.artObject[0];
    WebImage webImage = artObject.webImage;
    assertNotNull(artObject.title);
    assertNotNull(artObject.longTitle);
    assertNotNull(artObject.principalOrFirstMaker);
    assertNotNull(webImage.guid);
    assertNotEquals(0, webImage.offsetPercentageX);
    assertNotEquals(0, webImage.offsetPercentageY);
    assertNotEquals(0, webImage.width);
    assertNotEquals(0, webImage.height);
    assertNotNull(webImage.url);
}
@Test
void querySearch() {
    // given
    RijksService service = new RijksServiceFactory().getService();
    String query = "a";
    int page = 0;
    // when
    ArtObjects artObjects = service.querySearch(page, query).blockingGet();
    // then
    ArtObject artObject = artObjects.artObject[0];
    WebImage webImage = artObject.webImage;
    assertNotNull(artObject.title);
    assertNotNull(artObject.longTitle);
    assertNotNull(artObject.principalOrFirstMaker);
    assertNotNull(webImage.guid);
    assertNotEquals(0, webImage.offsetPercentageX);
    assertNotEquals(0, webImage.offsetPercentageY);
    assertNotEquals(0, webImage.width);
    assertNotEquals(0, webImage.height);
    assertNotNull(webImage.url);
}
    @Test
    void artistSearch() {
        // given
        RijksService service = new RijksServiceFactory().getService();
        String artist = "Vermeer";
        int page = 0;
        // when
        ArtObjects artObjects = service.querySearch(page, artist).blockingGet();
        // then
        ArtObject artObject = artObjects.artObject[0];
        WebImage webImage = artObject.webImage;
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.principalOrFirstMaker);
        assertNotNull(webImage.guid);
        assertNotEquals(0, webImage.offsetPercentageX);
        assertNotEquals(0, webImage.offsetPercentageY);
        assertNotEquals(0, webImage.width);
        assertNotEquals(0, webImage.height);
        assertNotNull(webImage.url);
    }
}
