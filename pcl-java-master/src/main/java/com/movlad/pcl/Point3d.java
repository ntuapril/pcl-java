package com.movlad.pcl;

import com.movlad.pcl.nat.NativeObject;

/**
 * A point structure representing Euclidean xyz coordinates.
 * 
 * @see <a href=
 *      "http://docs.pointclouds.org/1.8.0/structpcl_1_1_point_x_y_z.html">
 *      pcl::PointXYZRGB documentation </a>
 */
public class Point3d extends NativeObject implements Point, Cloneable {

	@Override
	protected native void alloc();

	@Override
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

	public final native short getR();

	public final native void setR(short r);

	public final native short getG();

	public final native void setG(short g);

	public final native short getB();

	public final native void setB(short b);

	public final native float getRGB();

	public void setRGB(short r, short g, short b) {
		setR(r);
		setG(g);
		setB(b);
	}

	@Override
	public Point3d clone() {
		Point3d clone = new Point3d();

		clone.create();

		clone.setCoordinates(getX(), getY(), getZ());
		clone.setRGB(getR(), getG(), getB());

		return clone;
	}

	@Override
	public String toString() {
		return "x: " + getX() + " y: " + getY() + " z: " + getZ();
	}
}
