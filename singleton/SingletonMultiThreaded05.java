package com.ron.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SingletonMultiThreaded {

	// Singleton lazy loading
	private static SingletonMultiThreaded soleInstance = null;

	// private constructor
	private SingletonMultiThreaded() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonMultiThreaded getInstance() {
		if (soleInstance == null) {
			soleInstance = new SingletonMultiThreaded();
		}
		return soleInstance;
	}

}

public class SingletonMultiThreaded05 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Breaking singleton using multi-threading

	// 1) till now we are creating singleton by eager initialization

	// 2) since, it is static singleton, instance created on class loading time. so
	// we need to create our singleton by lazy initialization.

	// 3) set soleInstance to null & initialize singleton by checking if instance
	// has been initialized or not. hence, singleton not created untill you call
	// static getInstance() method

	// 4) In multi-threaded environment, one thread will enter static getInstance()
	// & check if null - it will create new singleton instance. But sometimes other
	// thread may also enter that static getInstance() method & both may create
	// different singleton instances with different hashcodes.

	public static void getSingletonInstances() {
		SingletonMultiThreaded singleton = SingletonMultiThreaded.getInstance();
		System.out.println("singleton thread : " + singleton.hashCode());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create 5 threads in a thread pool using executors
		ExecutorService executors = Executors.newFixedThreadPool(5);

		// we will submit thread creating static method getSingletonInstances of class
		// SingletonMultiThreaded05 class

		// we will submit 2 threads at a time & will run that multiple times to check if
		// at any point it will create 2 different singleton instance
		// if both threads will execute that separately it means, private constructor
		// also executed twice(becus 2 different threads try to access & created
		// different singleton instance)
		executors.submit(SingletonMultiThreaded05::getSingletonInstances);
		executors.submit(SingletonMultiThreaded05::getSingletonInstances);

		/*
		 * Output:-
		 */
		/*
		 * private singleton class constructor... private singleton class constructor...
		 * singleton thread : 1475984021 singleton thread : 928642082
		 */

		// finally shutdown the executor
		executors.shutdown();
	}

	// Singleton & classloaders

	// 1) multiple class-loaders can load class mutiple times & create different
	// singleton objects in a single jvm

	// 2) in web-app container, where there 2 different class-loaders not linked
	// with each other, if you start your web-container, 2 different class-loaders
	// will load your singleton class on their own & they create singleton instances
	// on their own environment in a single jvm. Hence, breaking singleton.

	// Singleton & garbage collection

	// 1) earlier java versions(not concern now). static references with singleton
	// could get garbage collected at some point & when you call newInstance()
	// again, it will create & return new instance to you. This problem was in
	// java1.1 or java1.2 & not it is fixed.

}
