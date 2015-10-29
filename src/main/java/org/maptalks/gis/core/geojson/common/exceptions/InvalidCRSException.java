package org.maptalks.gis.core.geojson.common.exceptions;

public class InvalidCRSException extends RuntimeException {
    public InvalidCRSException(Throwable e) {
        super(e);
    }
	public InvalidCRSException() {
		super();
	}
	public InvalidCRSException(String msg) {
		super(msg);
	}
    public InvalidCRSException(String msg, Throwable e) {
        super(msg,e);
    }
}
