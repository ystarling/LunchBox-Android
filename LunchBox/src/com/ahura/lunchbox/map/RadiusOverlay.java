package com.ahura.lunchbox.map;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class RadiusOverlay extends Overlay {

	private GeoPoint geopoint;
	private int radiusMeters = 0;

	public RadiusOverlay(int radiusMeters) {
		this.radiusMeters = radiusMeters;
	}

	public void setGeopoint(GeoPoint geopoint) {
		this.geopoint = geopoint;
	}
	
	public void setRadiusMeters(int radiusMeters) {
		this.radiusMeters = radiusMeters;
	}

	@Override
	public void draw(Canvas canvas, MapView mapview, boolean shadow) {
		if (shadow == false && geopoint != null) {
			// create and setup the paint brush
			Paint paint = new Paint();
			paint.setARGB(255, 0, 0, 255);
			paint.setAntiAlias(true);
			paint.setFakeBoldText(true);
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(2);
			
			
			//path.setAntiAlias(true);
			//paint.setFakeBoldText(true);

			// create the circle
			Path path = new Path();
			
			// TODO: COnvert meters into a new GeoLocation.
			//path.addCircle(myPoint.x, myPoint.y, radiusMeters, Path.Direction.CCW);
			Point topLeft = GeoUtils.projectGeoPoint(mapview,
					GeoUtils.moveFrom(geopoint, radiusMeters, radiusMeters));
			Point bottomRight = GeoUtils.projectGeoPoint(mapview,
					GeoUtils.moveFrom(geopoint, -radiusMeters, -radiusMeters));
			
			RectF oval = new RectF(bottomRight.x, bottomRight.y, topLeft.x, topLeft.y);
			path.addOval(oval, Path.Direction.CCW);
			
			// draw the canvas
			canvas.drawPath(path, paint);

		} else {
                // TODO
		}

	}

}
