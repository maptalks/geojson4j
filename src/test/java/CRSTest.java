import org.junit.Assert;
import org.junit.Test;
import org.maptalks.gis.core.geojson.CRS;
import org.maptalks.gis.core.geojson.common.CoordinateType;
import org.maptalks.gis.core.geojson.common.CoordinateTypeHelper;

/**
 * Created by fuzhen on 2015/10/29.
 */
public class CRSTest {
    @Test
    public void test() {
        Assert.assertEquals(CRS.BD09LL, CoordinateTypeHelper.convertToCRS(CoordinateType.bd09ll));
        ;
        Assert.assertEquals(CoordinateTypeHelper.convertFromCRS(CRS.BD09LL), CoordinateType.bd09ll);
    }
}
