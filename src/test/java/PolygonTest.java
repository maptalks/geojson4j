import org.junit.Assert;
import org.junit.Test;
import org.maptalks.geojson.Geometry;
import org.maptalks.geojson.MultiPolygon;
import org.maptalks.geojson.Polygon;

import java.util.Arrays;

/**
 * Created by fuzhen on 2016/1/22.
 */
public class PolygonTest {
    @Test
    public void testClosePolygonRing() {
        Polygon polygon = ((Polygon) genPolygon());
        double[][][] coordinates = polygon.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            double[][] ring = coordinates[i];
            Assert.assertTrue(Arrays.equals(ring[0], ring[ring.length-1]));
        }
    }

    @Test
    public void testCloseMultiPolygonRing() {
        MultiPolygon mpolygon = ((MultiPolygon) genMultiPolygon());
        double[][][][] coordinates = mpolygon.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            double[][][] polygon = coordinates[i];
            for (int ii = 0; ii < polygon.length; ii++) {
                double[][] ring = polygon[ii];
                Assert.assertTrue(Arrays.equals(ring[0], ring[ring.length-1]));
            }

        }
    }

    /**
     * 生成MultiPolygon数据
     * @return
     */
    private static Geometry genMultiPolygon() {
        double[][][][] multiRings = new double[][][][] {
            //polygon1
            new double[][][] {
                new double[][]{new double[]{109,32},new double[]{109,31},
                    new double[]{109,30},new double[]{108,30},new double[]{109.1,32.2}}
            },
            //polygon2
            new double[][][] {
                new double[][]{new double[]{119,32},new double[]{119,31},
                    new double[]{119,30},new double[]{118,30},new double[]{119.1,32.2}}
            }
        };
        return new MultiPolygon(multiRings);
    }

    /**
     * 生成多边形
     * @return
     */
    private static Geometry genPolygon() {
        double[][][] rings = new double[][][]{
            new double[][]{new double[]{109,32},new double[]{109,31},
                new double[]{109,30},new double[]{108,30},new double[]{109.1,32.1}},
            new double[][]{new double[]{108.5,30.5},new double[]{108.5,30.3},
                new double[]{108.2,30.3},new double[]{108.51,30.52}}
        };
        return new Polygon(rings);
    }
}
