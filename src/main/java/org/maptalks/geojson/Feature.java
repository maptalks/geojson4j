package org.maptalks.geojson;

import java.util.Map;

public class Feature extends GeoJSON {
    private Object id;
    private Geometry geometry;
    private Map<String, Object> properties;

    public Feature(
            Geometry geometry,
            Map<String,Object> properties) {
        this(null, geometry,properties);
    }

    public Feature(Object id,
            Geometry geometry,
            Map<String,Object> properties) {
        super();
        this.setId(id);
        this.setGeometry(geometry);
        this.setProperties(properties);
    }

    public Feature(
            Geometry geometry) {
        this(geometry,null);
    }

    public Feature() {
        super();
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Feature)) {
            return false;
        }

        Feature o = ((Feature) obj);
        if (this.geometry == null) {
            if (o.geometry != null) {
                return false;
            }
        } else if (!this.geometry.equals(o.getGeometry())) {
            return false;
        }
        if (this.properties == null) {
            if (o.properties != null) {
                return false;
            }
        } else if (!this.properties.equals(o.getProperties())) {
            return false;
        }
        if (this.id == null) {
            if (o.id != null) {
                return false;
            }
        }else if (!this.id.equals(o.id)) {
            return false;
        }
        return true;
    }


}
