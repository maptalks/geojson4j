package org.maptalks.gis.core.geojson;

public class FeatureCollection extends GeoJSON {
    private Feature[] features;

    public FeatureCollection() {
        super();
    }

    public FeatureCollection(Feature[] features) {
        super();
        this.setFeatures(features);
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }
}
