package com.movlad.pcl.examples;

import java.io.BufferedReader;
import java.io.FileReader;

import com.movlad.pcl.Point3d;
import com.movlad.pcl.PointCloud3d;
import com.movlad.pcl.visualization.Visualizer;
import com.movlad.pcl.visualization.Visualizer3d;

import pcl.icp;
import pcl.pointcloudxyz;
import pcl.pointxyz;

public class ICPDemo {
	static {
		System.load(
				"H:\\eclipse\\vs_workspace\\pcl-java-master\\pcl-java-master\\natives\\win32\\x86_64\\pcl_icp_test.dll");
	}

	public static void main(String[] args) {

		pointcloudxyz inputSourcexyz = setpointcloud("building/rawXYZ.txt");
		pointcloudxyz inputTargetxyz = setpointcloud("building/editedXYZ.txt");

		Visualizer<Point3d> visualizer = new Visualizer3d();

		visualizer.create();
		visualizer.setWindowName("PCL Java ICP Example");
		visualizer.setBackgroundColor(0.f, 0.f, 0.f);
		visualizer.addCoordinateSystem(0.2, 0);

		pointxyz p = new pointxyz();
		p.alloc();

		icp icp = new icp();
		icp.alloc();

		float[] matrix = new float[16];
		for (int i = 0; i < 16; i++)
			matrix[i] = 0;

		float[] output = new float[inputSourcexyz.size() * 3];
		for (int i = 0; i < 16; i++)
			matrix[i] = 0;

		icp.compute(inputSourcexyz, inputTargetxyz, 200, 0.0001f, matrix, output);
		for (int i = 0; i < 16; i++)
			System.out.println(matrix[i]);

		System.out.println();

		PointCloud3d inputSource = setcloudXYZRGB("building/rawXYZ.txt", 255, 0, 0);
		PointCloud3d inputTarget = setcloudXYZRGB("building/editedXYZ.txt", 255, 255, 0);

		PointCloud3d finalc = new PointCloud3d();
		finalc.alloc();
		for (int i = 0; i < output.length; i += 3) {
			Point3d point = new Point3d();
			point.create();

			float x = output[i];
			float y = output[i + 1];
			float z = output[i + 2];
			point.setCoordinates(x, y, z);
			point.setRGB((short) 255, (short) 255, (short) 255);
			finalc.add(point);
			point.dispose();
		}

		visualizer.addPointCloud(inputSource, "inputSource", 0);
		visualizer.addPointCloud(inputTarget, "inputTarget", 0);
		visualizer.addPointCloud(finalc, "finalcloud", 0);

		visualizer.setPointSize(1, "inputSource");
		visualizer.setPointSize(1, "inputTarget");
		visualizer.setPointSize(1, "finalcloud");

		while (!visualizer.wasStopped()) {
			visualizer.spinOnce(100, false);

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		visualizer.dispose();
	}

	private static PointCloud3d setcloudXYZRGB(String path, int r, int g, int b) {
		PointCloud3d pc = new PointCloud3d();
		pc.create();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] tags1 = line.split(" ");
				Point3d point = new Point3d();
				point.create();
				point.setX(Float.parseFloat(tags1[0]));
				point.setY(Float.parseFloat(tags1[1]));
				point.setZ(Float.parseFloat(tags1[2]));
				point.setRGB((short) r, (short) g, (short) b);
				pc.add(point);
				point.dispose();
			}
			br.close();
		} catch (Exception e) {
		}
		return pc;
	}

	private static pointcloudxyz setpointcloud(String path) {
		pointcloudxyz pc = new pointcloudxyz();
		pc.alloc();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] tags1 = line.split(" ");
				pointxyz point = new pointxyz();
				point.create();
				point.setX(Float.parseFloat(tags1[0]));
				point.setY(Float.parseFloat(tags1[1]));
				point.setZ(Float.parseFloat(tags1[2]));
				pc.add(point);
				point.dispose();
			}
			br.close();
		} catch (Exception e) {
		}
		return pc;
	}
}