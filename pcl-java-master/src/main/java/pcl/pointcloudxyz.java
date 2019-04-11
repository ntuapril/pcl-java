package pcl;

import com.movlad.pcl.PointCloud;

/**
 * The class for storing an array of 3D RGB points.
 * 
 * @see <a href=
 *      "http://docs.pointclouds.org/trunk/classpcl_1_1_point_cloud.html#abc784b5dec409efe78bf21ad3776f334">
 *      pcl::PointCloud documentation </a>
 */
public class pointcloudxyz extends PointCloud<pointxyz> {

	public native void alloc();

	public native void dispose();

	@Override
	public pointxyz get(int i) {
		pointxyz point = new pointxyz();

		nGet(i, point);

		return point;
	}

	@Override
	protected native void nGet(int i, pointxyz point);

	@Override
	public native void add(pointxyz point);

	@Override
	public native void remove(pointxyz point);

	@Override
	public native void clear();

	@Override
	public native int size();

	@Override
	public native boolean isOrganized();

	@Override
	public pointcloudxyz clone() {
		pointcloudxyz clone = new pointcloudxyz();

		clone.create();

		for (pointxyz point : this) {
			pointxyz pointClone = point.clone();

			clone.add(pointClone);
		}
		return clone;
	}
}
