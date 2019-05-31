package com.ron.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class SingletonReflection {

	private static SingletonReflection soleInstance = null;

	// private constructor
	private SingletonReflection() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonReflection getInstance() {
		if (soleInstance == null) {
			soleInstance = new SingletonReflection();
		}
		return soleInstance;
	}
}

public class SingletonReflection02 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Breaking singleton using reflection

	// 1) Use reflection to create new instance of singleton class using -
	// Class.forName(className) to load class dynamically

	// 2) access declared default constructor using reflection, since it is private,
	// we cannot access that constructor. Hence setAccessible(true) to access
	// private singleton constructor & create objects

	// 3) create new instance of singleton class using newInstance() & check its
	// hashcode()

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonReflection singletonOne = SingletonReflection.getInstance();
		SingletonReflection singletonTwo = SingletonReflection.getInstance();

		System.out.println(singletonOne + " : " + singletonTwo);
		System.out.println("Is 2 singleton instances same : " + (singletonOne == singletonTwo));

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		// load class using reflection
		try {
			Class singletonClass = Class.forName("com.ron.singleton.SingletonReflection");
			// get declared constructor
			Constructor<SingletonReflection> cons = singletonClass.getDeclaredConstructor();
			// change private constructor to public constructor
			cons.setAccessible(true);
			SingletonReflection singleton3 = cons.newInstance();
			// both having different hashcode means with reflection we can break singleton
			System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
			System.out.println("singleton3 hashcode() : " + singleton3.hashCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
