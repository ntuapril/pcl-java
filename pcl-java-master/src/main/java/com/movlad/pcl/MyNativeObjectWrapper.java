package com.movlad.pcl;

import com.movlad.pcl.nat.NativeObject;

public class MyNativeObjectWrapper extends NativeObject {
	@Override
	protected native void alloc();

	@Override
	public native void dispose();

	/**
	 * Method that is called on the native side
	 */
	public native void methodOne(int paramOne);
	
}