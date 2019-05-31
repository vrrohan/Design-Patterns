package com.ron.singleton;

class SingletonClone implements Cloneable {

	private static SingletonClone soleInstance = new SingletonClone();

	// private constructor
	private SingletonClone() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonClone getInstance() {
		return soleInstance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}

public class SingletonClone04 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Breaking singleton using cloning

	// 1) implement singleton class with cloneable interface & override clone()

	// 2) now clone() your singleton object to a new reference, cast it to singleton
	// class & store it into reference
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonClone singletonOne = SingletonClone.getInstance();
		SingletonClone singletonTwo = SingletonClone.getInstance();

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		try {
			// clone will create a new object
			SingletonClone clonedObject = (SingletonClone) singletonOne.clone();
			System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
			System.out.println("clonedObject hashcode() : " + clonedObject.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
