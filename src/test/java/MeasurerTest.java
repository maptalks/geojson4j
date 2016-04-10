import org.junit.Assert;
import org.junit.Test;
import org.maptalks.geojson.CRS;
import org.maptalks.geojson.Point;
import org.maptalks.geojson.measure.Measurer;

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
