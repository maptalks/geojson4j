import org.junit.Assert;
import org.junit.Test;
import org.maptalks.gis.core.geojson.CRS;

/**
 * Created by fuzhen on 2015/10/29.
 */
public class CRSTest {
    @Test
    public void test() {
        String prj4 = "+proj=longlat +datum=WGS84 +no_defs";
        CRS crs = CRS.createProj4(prj4);
        Assert.assertEquals(crs.getProperties().get("proj"), prj4);
    }
}
