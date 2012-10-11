package com.ahura.lunchbox.map;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyLocationOverlay extends Overlay {

	GeoPoint geopoint;

	public GeoPoint getGeopoint() {
		return geopoint;
	}

	public void setGeopoint(GeoPoint geopoint) {
		this.geopoint = geopoint;
	}

	@Override
	public void draw(Canvas canvas, MapView mapview, boolean shadow) {
		if (shadow == false && geopoint != null) {
			Point myPoint = GeoUtils.projectGeoPoint(mapview, geopoint);

			// create and setup the paint brush
			Paint paint = new Paint();
			paint.setARGB(255, 0, 0, 255);
			paint.setAntiAlias(true);
			paint.setFakeBoldText(true);

			// create the circle
			int rad = 10;
			RectF oval = new RectF(myPoint.x - rad, myPoint.y - rad, myPoint.x
					+ rad, myPoint.y + rad);

			// draw the canvas
			canvas.drawOval(oval, paint);
			// canvas.drawText("", myPoint.x+rad, myPoint.y, paint);

		} else {
                // TODO
		}

	}

}
