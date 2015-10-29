package org.maptalks.gis.core.geojson.common.convertor;

import org.maptalks.gis.core.geojson.*;
import org.maptalks.gis.core.geojson.common.CoordinateType;
import org.maptalks.gis.core.geojson.common.CoordinateTypeHelper;
import org.maptalks.gis.core.geojson.common.convertor.impl.Gcj02_Wgs84_BD09Convertor;
import org.maptalks.gis.core.geojson.ext.*;

/**
 * Created by fuzhen on 2015/8/12.
 */
public class CoordinateTypeConvertor {

	private static ICoordTypeConvertor convertor = null;

	static {
        convertor = new Gcj02_Wgs84_BD09Convertor();
	}

    /**
     * crs键图形转换, 目前只支持CoordinateType类型的相互转换
     * @param geometry
     * @param from
     * @param to
     * @return
     */
	public static Geometry convert(Geometry geometry, CRS from, CRS to) {
		if (convertor == null || from == null || to == null || from.equals(to)) {
			return geometry;
		}
		//直接覆盖geometry的属性, 减少创建Geometry对象
		if (geometry instanceof Point) {
			double[] c = convertor.convert(((Point) geometry).getCoordinates(), from, to);
			return new Point(c);
		} else if (geometry instanceof LineString) {
			double[][] c = convert(((LineString) geometry).getCoordinates(), from, to);
			return new LineString(c);
		} else if (geometry instanceof Polygon) {
			double[][][] c = convert(((Polygon) geometry).getCoordinates(), from, to);
			return new Polygon(c);
		} else if (geometry instanceof MultiPoint) {
			double[][] c = convert(((MultiPoint) geometry).getCoordinates(), from, to);
			return new MultiPoint(c);
		} else if (geometry instanceof MultiLineString) {
			double[][][] c = convert(((MultiLineString) geometry).getCoordinates(), from, to);
			return new MultiLineString(c);
		} else if (geometry instanceof MultiPolygon) {
			double[][][][] c = convert(((MultiPolygon) geometry).getCoordinates(), from, to);
			return new MultiPolygon(c);
		} else if (geometry instanceof GeometryCollection) {
			Geometry[] geometries = ((GeometryCollection) geometry).getGeometries();
			if (geometries != null) {
				int size = geometries.length;
				Geometry[] targets = new Geometry[size];
				for (int i = 0; i < size; i++) {
					targets[i] = convert(geometries[i], from, to);
				}
				return new GeometryCollection(geometries);
			}
			return new GeometryCollection();
		} else if (geometry instanceof Circle) {
			Circle circle = ((Circle) geometry);
			double[] c = convertor.convert(circle.getCoordinates(), from, to);
			return new Circle(c, circle.getRadius(), circle.getUnit());
		} else if (geometry instanceof Ellipse) {
			Ellipse ellipse = ((Ellipse) geometry);
			double[] c = convertor.convert(ellipse.getCoordinates(), from, to);
			return new Ellipse(c, ellipse.getWidth(), ellipse.getHeight(), ellipse.getUnit());
		} else if (geometry instanceof Rectangle) {
			Rectangle rect = ((Rectangle) geometry);
			double[] c = convertor.convert(rect.getCoordinates(), from, to);
			return new Rectangle(c, rect.getWidth(), rect.getHeight(), rect.getUnit());
		} else if (geometry instanceof Sector) {
			Sector sector = ((Sector) geometry);
			double[] c = convertor.convert(sector.getCoordinates(), from, to);
			return new Sector(c, sector.getRadius(), sector.getStartAngle(), sector.getEndAngle(), sector.getUnit());
		}
		return null;
	}

	public static double[][][][] convert(double[][][][] coordinates, CRS from, CRS to) {
		if (coordinates == null) {
			return null;
		}
		if (coordinates.length == 0) {
			return new double[0][0][0][0];
		}
		if (from == null || to == null || from.equals(to)) {
			return coordinates;
		}
		double[][][][] result = new double[coordinates.length][][][];
		for (int i = 0; i < coordinates.length; i++) {
			result[i] = convert(coordinates[i], from, to);
		}
		return result;
	}

	public static double[][][] convert(double[][][] coordinates, CRS from, CRS to) {
		if (coordinates == null) {
			return null;
		}
		if (coordinates.length == 0) {
			return new double[0][0][0];
		}
		if (from == null || to == null || from.equals(to)) {
			return coordinates;
		}
		double[][][] result = new double[coordinates.length][][];
		for (int i = 0; i < coordinates.length; i++) {
			result[i] = convert(coordinates[i], from, to);
		}
		return result;
	}

	public static double[][] convert(double[][] coordinates, CRS from,
					 CRS to) {
		if (coordinates == null || coordinates.length == 0) {
			return null;
		}
		if (from == null || to == null) {
			return coordinates;
		}
		if (from.equals(to)) {
			return coordinates;
		}
		double[][] result = new double[coordinates.length][];
		for (int i = 0, len = coordinates.length; i < len; i++) {
			double[] coordinate = coordinates[i];
			double[] converted = convertor.convert(coordinate, from, to);
			if (converted != null) {
				result[i] = converted;
			} else {
				result[i] = coordinates[i];
			}

		}
		return result;
	}
}
