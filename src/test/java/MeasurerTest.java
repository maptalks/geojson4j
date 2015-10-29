import org.junit.Assert;
import org.junit.Test;
import org.maptalks.gis.core.geojson.CRS;
import org.maptalks.gis.core.geojson.Point;
import org.maptalks.gis.core.geojson.measure.Measurer;

/**
 * Created by fuzhen on 2015/10/29.
 */
public class MeasurerTest {
    CRS crs = CRS.DEFAULT;
    @Test
    public void testMeasure() {
        //TODO 补充测试用例
        Assert.assertTrue(Measurer.computeArea(new Point(new double[]{0,0}), crs)==0);
    }
}
