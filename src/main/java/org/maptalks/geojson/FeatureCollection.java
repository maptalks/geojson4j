package org.maptalks.geojson;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FeatureCollection)) {
            return false;
        }
        FeatureCollection o = ((FeatureCollection) obj);
        if (this.features == null) {
            if (o.getFeatures() != null) {
                return false;
            }
        } else {
            if (o.getFeatures() == null) {
                return false;
            }
            if (this.features.length != o.getFeatures().length) {
                return false;
            }
            int size = this.features.length;
            for (int i = 0; i < size; i++) {
                if (!this.features[i].equals(o.features[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
