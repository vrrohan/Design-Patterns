package com.ron.singleton;

enum SingletonEnum {
	SINGLETON_INSTANCE;
}

public class SingletonEnums {

	// 1) Enums are best to create singleton objects in java from jdk5

	// 2) use enums to represent singleton - you have thread safety, safety against
	// serialization & de-serialization, safety against cloning & even reflection.

	// 3) create a enum singleton

	// 4) However, the obvious drawback is that we cannot have lazy loading in Enum.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonEnum s1 = SingletonEnum.SINGLETON_INSTANCE;
		SingletonEnum s2 = SingletonEnum.SINGLETON_INSTANCE;

		System.out.println("s1=" + s1.hashCode());
		System.out.println("s1=" + s2.hashCode());
	}

}
