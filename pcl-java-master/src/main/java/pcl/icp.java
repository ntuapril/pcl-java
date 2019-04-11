package pcl;

import com.movlad.pcl.nat.NativeObject;

public class icp extends NativeObject {
	
	public native void alloc();
	
	public native void dispose(); 
	
	public native float compute(pointcloudxyz src, pointcloudxyz dst, int iter, float threshold, float[] matrix,float[] output);
	
	public native float test();

	public native void transform(pointcloudxyz src, float[][] matrix, pointcloudxyz target);

}
