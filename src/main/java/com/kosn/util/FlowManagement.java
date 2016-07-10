package com.kosn.util;

public class FlowManagement {
	
	static void wait(double seconds) {
		double millis = seconds * 1000;
		try {
			Thread.sleep((long) millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void wait(int seconds) {
		wait((double)seconds);
	}
}
