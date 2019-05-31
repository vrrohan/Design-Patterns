package com.ron.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class SingletonReflectionFix {

	private static SingletonReflectionFix soleInstance = null;

	// private constructor
	private SingletonReflectionFix() {
		System.out.println("private singleton class constructor...");
		if (soleInstance != null) {
			throw new RuntimeException("cannot create instance, please use getInstance()");
		}
	}

	// static method that returns sole instance of class Singleton
	public static SingletonReflectionFix getInstance() {
		if (soleInstance == null) {
			soleInstance = new SingletonReflectionFix();
		}
		return soleInstance;
	}
}

public class SingletonReflectionFix06 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Fixing Singleton Reflection problems

	// 1) becus we could set private constructor as accessible & invoke it & create
	// a new object. Hence, fix would be inside that private constructor only

	// 2) Inside private constructor, we need to check if sole instance has been
	// created
	// or not, it yes, return that instance only

	// 3) if soleInstance==null then throw new RunTimeException(), this will make
	// unable to create new instance if sole instance is null

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonReflectionFix singletonOne = SingletonReflectionFix.getInstance();
		SingletonReflectionFix singletonTwo = SingletonReflectionFix.getInstance();

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		// load class using reflection
		try {
			Class singletonClass = Class.forName("com.ron.singleton.SingletonReflectionFix");
			// get declared constructor
			Constructor<SingletonReflectionFix> cons = singletonClass.getDeclaredConstructor();
			// change private constructor to public constructor
			cons.setAccessible(true);
			SingletonReflectionFix singleton3 = cons.newInstance();
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
