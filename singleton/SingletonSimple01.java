package com.ron.singleton;

class Singleton {

	private static Singleton soleInstance = new Singleton();

	// private constructor
	private Singleton() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static Singleton getInstance() {
		return soleInstance;
	}
}

public class SingletonSimple01 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Hard to create singleton instances :-becus we can break singleton using 6
	// different ways
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		Singleton singletonOne = Singleton.getInstance();
		Singleton singletonTwo = Singleton.getInstance();

		System.out.println(singletonOne + " : " + singletonTwo);
		System.out.println("Is 2 singleton instances same : " + (singletonOne == singletonTwo));

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

	}

	// Why use singleton ?

	// 1) you want to represent sole one concept across your whole application

	// 2) In jdk, Runtime Class is singleton - becus when you run your application
	// you will have only one runtime available to you & that is sole runtime that
	// is available to you for your running class

	// 3) like a LogManager is a singleton, which maintains all logging on your
	// applications. no other logging methods available to you.

	// 4) like a db connection is singleton, whichever application wants to access
	// your data from db, no need to create different connections. That DB
	// connection must have singleton instance & have share connection data between
	// different applications.

}
