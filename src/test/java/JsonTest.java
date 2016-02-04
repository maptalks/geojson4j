import org.junit.Assert;
import org.junit.Test;
import org.maptalks.gis.core.geojson.*;
import org.maptalks.gis.core.geojson.json.GeoJSONFactory;

/**
 * Created by fuzhen on 2015/10/7.
 */
public class JsonTest {
    @Test
    public void testParsingFeatureCollection() {
        String json = "{ \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n" +
                "      { \"type\": \"Feature\",\n" +
                "        \"geometry\": {\"type\": \"Point\", \"coordinates\": [102.0, 0.5]},\n" +
                "        \"properties\": {\"prop0\": \"value0\"}\n" +
                "        },\n" +
                "      { \"type\": \"Feature\",\n" +
                "        \"geometry\": {\n" +
                "          \"type\": \"LineString\",\n" +
                "          \"coordinates\": [\n" +
                "            [102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]\n" +
                "            ]\n" +
                "          },\n" +
                "        \"properties\": {\n" +
                "          \"prop0\": \"value0\",\n" +
                "          \"prop1\": 0.0\n" +
                "          }\n" +
                "        },\n" +
                "      { \"type\": \"Feature\",\n" +
                "         \"geometry\": {\n" +
                "           \"type\": \"Polygon\",\n" +
                "           \"coordinates\": [\n" +
                "             [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0],\n" +
                "               [100.0, 1.0], [100.0, 0.0] ]\n" +
                "             ]\n" +
                "         },\n" +
                "         \"properties\": {\n" +
                "           \"prop0\": \"value0\",\n" +
                "           \"prop1\": {\"this\": \"that\"}\n" +
                "           }\n" +
                "         }\n" +
                "       ]\n" +
                "     }";
        FeatureCollection fc = ((FeatureCollection) GeoJSONFactory.create(json));
        Assert.assertTrue(fc.getFeatures().length ==3);
        Assert.assertTrue(fc.getFeatures()[0].getGeometry() instanceof Point);
    }

    @Test
    public void testParsingFeature() {
        String json = "{ \"type\": \"Feature\",\n" +
                "              \"geometry\": {\n" +
                "                  \"type\": \"LineString\",\n" +
                "                  \"coordinates\": [\n" +
                "                      [102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]\n" +
                "                  ]\n" +
                "              },\n" +
                "              \"properties\": {\n" +
                "                  \"prop0\": \"value0\",\n" +
                "                  \"prop1\": 0.0\n" +
                "              }\n" +
                "            }";
        Feature fc = ((Feature) GeoJSONFactory.create(json));
        Assert.assertTrue(fc.getGeometry() instanceof LineString);
    }

    @Test
    public void testOrdinal() {
        Point p = new Point(new double[]{1.0, 2.0});
        String s = p.toString();
        Assert.assertEquals("{\"type\":\"Point\",\"coordinates\":[1.0,2.0]}", s);
    }

    @Test
    public void testParsingGeometry() {
        String json ="[\n" +
                "        { " +
                "           \"type\": \"Point\", " +
                "           \"coordinates\": [100.0, 0.0] },\n" +
                "        {\n" +
                "            \"type\": \"LineString\",\n" +
                "            \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"Polygon\",\n" +
                "            \"coordinates\": [\n" +
                "                [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ]\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"Polygon\",\n" +
                "            \"coordinates\": [\n" +
                "                [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ],\n" +
                "                [ [100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2] ]\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"MultiPoint\",\n" +
                "            \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"MultiLineString\",\n" +
                "            \"coordinates\": [\n" +
                "                [ [100.0, 0.0], [101.0, 1.0] ],\n" +
                "                [ [102.0, 2.0], [103.0, 3.0] ]\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"MultiPolygon\",\n" +
                "            \"coordinates\": [\n" +
                "                [\n" +
                "                    [[102.0, 2.0], [103.0, 2.0], [103.0, 3.0], [102.0, 3.0], [102.0, 2.0]]\n" +
                "                ],\n" +
                "                [\n" +
                "                    [[100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0]],\n" +
                "                    [[100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2]]\n" +
                "                ]\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"GeometryCollection\",\n" +
                "            \"geometries\": [\n" +
                "                { \"type\": \"Point\",\n" +
                "                  \"coordinates\": [100.0, 0.0]\n" +
                "                },\n" +
                "                { \"type\": \"LineString\",\n" +
                "                  \"coordinates\": [ [101.0, 0.0], [102.0, 1.0] ]\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\":\"Circle\",\n" +
                "            \"coordinates\":[100.0,0.0],\n" +
                "            \"radius\":100\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\":\"Ellipse\",\n" +
                "            \"coordinates\":[100.0,0.0],\n" +
                "            \"width\":100,\n" +
                "            \"height\":50\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\":\"Rectangle\",\n" +
                "            \"coordinates\":[100.0,0.0],\n" +
                "            \"width\":100,\n" +
                "            \"height\":50\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\":\"Sector\",\n" +
                "            \"coordinates\":[100.0,0.0],\n" +
                "            \"radius\":1000,\n" +
                "            \"startAngle\":50,\n" +
                "            \"endAngle\":120\n" +
                "        }\n" +
                "    ]";
        Geometry[] geometries =GeoJSONFactory.createGeometryArray(json);
        for (int i = 0; i < geometries.length; i++) {

        }
    }
}
