/**
 * 
 */
package com.ahura.lunchbox.map;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Projection;

import android.graphics.Point;

/**
 * Static utility class.
 * 
 * @author pani
 *
 */
public final class GeoUtils {

	 //Earth’s radius, sphere
	public static final int EARTH_RADIUS = 6378137;
			 
			 
	private GeoUtils() {
		// Cannot be instantiated
	}

	public static Point projectGeoPoint(MapView mapView, GeoPoint geoPoint) {
		Point point = new Point();
		Projection projection = mapView.getProjection();
		projection.toPixels(geoPoint, point);
		return point;
	}
	
	public static GeoPoint moveFrom(GeoPoint geoPoint, int latMeters, int longMeters) {
		//Position, decimal degrees
		double lat = geoPoint.getLatitudeE6() / 1E6f;
		double lon = geoPoint.getLongitudeE6() / 1E6f;

		 //Coordinate offsets in radians
		 double dLat = ((double) latMeters) / EARTH_RADIUS;
		 double dLon = ((double) longMeters) / (EARTH_RADIUS * Math.cos(Math.PI * lat / 180));

		 //OffsetPosition, decimal degrees
		 dLat = lat + dLat * 180 / Math.PI;
		dLon = lon + dLon * 180 / Math.PI;
		
		return new GeoPoint((int) (dLat * 1E6), (int) ( dLon * 1E6));
	}
}
