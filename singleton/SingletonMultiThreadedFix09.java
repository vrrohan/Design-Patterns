package com.ron.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SingletonMultiThreadedFix {

	// Singleton lazy loading
	private static volatile SingletonMultiThreadedFix soleInstance = null;

	// private constructor
	private SingletonMultiThreadedFix() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	// public static synchronized SingletonMultiThreadedFix getInstance()
	public static SingletonMultiThreadedFix getInstance() {
		// check one
		if (soleInstance == null) {
			// get lock here
			synchronized (SingletonMultiThreadedFix.class) {
				// check two
				if (soleInstance == null) {
					soleInstance = new SingletonMultiThreadedFix();
				}
			}
		}
		return soleInstance;
	}

}

public class SingletonMultiThreadedFix09 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Fixing singleton for multi-threaded scenario

	// 1) quick fix is:- make getInstance() synchronized, to make sure multiple
	// threads accessing getInstance() can only access one thread at a time

	// 2) in case of multi-threading at eager loading, no problem with
	// multi-threaing here becus singleton object created at class loading time &
	// there is never a problem of multi-threading at class loading time. this issue
	// arises only on lazy-loading of singleton object.

	// 3) eager loading of singleton instance is not good option, becus if somehow
	// this fails as class loading time, we can never be able to create single
	// singleton instance during whole class run-time. Also , if very expensive
	// operation to create singleton instance, it is best to create singleton only
	// when needed, hence lazy loading is better option.

	// 4) we are making single point of access getInstance(), to the singleton a
	// synchronized call. that means even after creation of singleton instance, all
	// the threads or all the calls to singleton instance is going to be
	// synchronized. This degrades performance is some environment. A better
	// approach is to use synchronized block rather than synchronized method.

	// 5) synchronized(ClassName.class) {//create instance here} - we get lock at
	// this point. Problem is - if thread A enters & check soleInstance==null, if it
	// is null, it will try to lock on Singleton class. By this time, if thread A
	// gets lock, its possible that some other thread might have already initialized
	// your singleton soleInstance. It is always very good approach to check if
	// soleInstance is null or not even after getting lock. This is called - double
	// check locking.

	// 6) add volatile keyword to singleton object - to make sure change to any
	// volatile variable will be published only when change is complete.
	public static void getSingletonInstances() {
		SingletonMultiThreadedFix singleton = SingletonMultiThreadedFix.getInstance();
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
		executors.submit(SingletonMultiThreadedFix09::getSingletonInstances);
		executors.submit(SingletonMultiThreadedFix09::getSingletonInstances);

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
