package com.kosn.entity;

public interface Examinable {

	public default void printInfo() {}

	public default String getName() {
		return null;
		}
}
