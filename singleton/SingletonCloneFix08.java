package com.ron.singleton;

class SingletonCloneFix implements Cloneable {

	private static SingletonCloneFix soleInstance = null;

	// private constructor
	private SingletonCloneFix() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonCloneFix getInstance() {
		if (soleInstance == null) {
			soleInstance = new SingletonCloneFix();
		}
		return soleInstance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		// most preferred way
		throw new CloneNotSupportedException("cannot clone singleton object");
		// return soleInstance;
	}

}

public class SingletonCloneFix08 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Fixing singleton for cloning

	// 1) override clone() & throw CloneNotSupportedException() or return same
	// soleInstance singleton object
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonCloneFix singletonOne = SingletonCloneFix.getInstance();
		SingletonCloneFix singletonTwo = SingletonCloneFix.getInstance();

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		try {
			// clone will create a new object
			SingletonCloneFix clonedObject = (SingletonCloneFix) singletonOne.clone();
			System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
			System.out.println("clonedObject hashcode() : " + clonedObject.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
