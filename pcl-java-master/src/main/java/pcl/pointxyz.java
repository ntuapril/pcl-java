package pcl;

import com.movlad.pcl.Point;
import com.movlad.pcl.nat.NativeObject;

public class pointxyz extends NativeObject implements Point, Cloneable {

	public native void alloc();

	public native void dispose();

	public final native float getX();

	public native void setX(float x);

	public native float getY();

	public native void setY(float y);

	public native float getZ();

	public native void setZ(float z);

	public void setCoordinates(float x, float y, float z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	public pointxyz clone() {
		pointxyz clone = new pointxyz();

		clone.create();

		clone.setCoordinates(getX(), getY(), getZ());

		return clone;
	}

	@Override
	public String toString() {
		return "x: " + getX() + " y: " + getY() + " z: " + getZ();
	}
}
