#include "pcl_icp.h"
#include "handle.h"
#include "sptr_wrapper.h"

jfloat JNICALL Java_pcl_icp
(JNIEnv *env, jobject obj, jobject src, jobject dst, jint iter, jfloat threshold, jobject matrix)
{
	pcl::IterativeClosestPoint<pcl::PointXYZ, pcl::PointXYZ>::Ptr icp_ptr =
		sptr_wrapper<pcl::IterativeClosestPoint<pcl::PointXYZ, pcl::PointXYZ>>::get_sptr(env, obj);
	pcl::PointCloud<pcl::PointXYZ>::Ptr src_ptr
		= sptr_wrapper< pcl::PointCloud<pcl::PointXYZ> >::get_sptr(env, src);
	pcl::PointCloud<pcl::PointXYZ>::Ptr dst_ptr
		= sptr_wrapper< pcl::PointCloud<pcl::PointXYZ> >::get_sptr(env, dst);
	
	icp_ptr->setInputSource(*src_ptr);
	icp_ptr->setInputTarget(*dst_ptr);
	icp_ptr->setRANSACIterations(iter);
	icp_ptr->setRANSACOutlierRejectionThreshold(threshold);
	
	pcl::PointCloud<pcl::Poi    ntXYZ> Final;
	icp.align(Final);

	sptr_wrapper<Matrix4> *matrix_ptr_w =
		new sptr_wrapper<Matrix4>();
	matrix_ptr_w->instantiate(env, matrix);
	*matrix_ptr_w->get_sptr() = icp_ptr->getFinalTransformation();

	return icp_ptr->getFitnessScore();
}

void JNICALL Java_pcl_icp_transform
(JNIEnv *env, jobject src, jobject matrix, jobject target)
{
	pcl::PointCloud<pcl::PointXYZ>::Ptr src_ptr
		= sptr_wrapper< pcl::PointCloud<pcl::PointXYZ> >::get_sptr(env, src);
	Matrix4 matrix_ptr
		= sptr_wrapper< Matrix4 >::get_sptr(env, matrix);

	sptr_wrapper<pcl::PointCloud<pcl::PointXYZ>> *target_ptr_w =
		new sptr_wrapper<pcl::PointCloud<pcl::PointXYZ>>();
	target_ptr_w->instantiate(env, target);
	
	pcl::transformPointCloud(*src_ptr, *target_ptr_w->get_sptr(), *matrix_ptr);	
}