package org.maptalks.gis.core.geojson;

/**
 * Created by fuzhen on 2015/8/11.
 */
public class GeometryCollection extends Geometry {
    private Geometry[] geometries;

    public GeometryCollection(Geometry[] geometries) {
        super();
        this.setGeometries(geometries);
    }

    public GeometryCollection(){
        super();
    }

    public Geometry[] getGeometries() {
        return geometries;
    }

    public void setGeometries(Geometry[] geometries) {
        this.geometries = geometries;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GeometryCollection)) {
            return false;
        }
        GeometryCollection o = ((GeometryCollection) obj);
        int size = this.geometries.length;
        for (int i = 0; i < size; i++) {
            if (!this.geometries[i].equals(o.geometries[i])) {
                return false;
            }
        }
        return true;
    }
}
