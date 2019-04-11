#include <pcl/point_cloud.h>
#include "com_movlad_pcl_ICP.h"

void Java_com_movlad_pcl_ICP_setInputCloud
(JNIEnv *env, jobject obj, jobject cloud_in,jobject cloud_out)
{
		
	pcl::IterativeClosestPoint<pcl::PointXYZ, pcl::PointXYZ> icp;
	
	icp.setInputSource(cloud_in);
    icp.setInputTarget(cloud_out);
	
	pcl::PointCloud<pcl::PointXYZ> Final;
	icp.align(Final);
	std::cout << "has converged:" << icp.hasConverged() << " score: " << icp.getFitnessScore() << std::endl;
	std::cout << icp.getFinalTransformation() << std::endl;
	std::cout << icp.getFinalRotation() << std::endl;
	
}

void Java_com_movlad_pcl_IterativeClosestPoint_alloc
(JNIEnv *env, jobject obj)
{
	pcl::IterativeClosestPoint<pcl::PointXYZ, pcl::PointXYZ> *icp_ptr = new pcl::IterativeClosestPoint();

	set_handle(env, obj, icp_ptr);
}

void Java_com_movlad_pcl_Point2d_dispose
(JNIEnv *env, jobject obj)
{
	pcl::PointXY *pt_ptr = get_handle<pcl::PointXY>(env, obj);

	delete pt_ptr;

	set_handle<pcl::PointXY>(env, obj, nullptr);
}