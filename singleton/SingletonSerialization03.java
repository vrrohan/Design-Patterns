package com.ron.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SingletonSerialization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SingletonSerialization soleInstance = new SingletonSerialization();

	// private constructor
	private SingletonSerialization() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonSerialization getInstance() {
		return soleInstance;
	}
}

public class SingletonSerialization03 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Breaking singleton using serialization & de-serialization

	// 1) for class to be serializable class - implement Serializable interface

	// 2) create object of ObjectOutputStream to serialize object to stream(file)

	// 3) now read singleton object back from file, it will create a new object of
	// different hashcode

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonSerialization singletonOne = SingletonSerialization.getInstance();
		SingletonSerialization singletonTwo = SingletonSerialization.getInstance();

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
			// Serialize object of singleton class
			oos.writeObject(singletonOne);
			// now de-serialize object of singleton class
			SingletonSerialization singleton3 = (SingletonSerialization) ois.readObject();

			// Here both have different hashcodes, hence break the singleton
			System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
			System.out.println("singleton3 hashcode() : " + singleton3.hashCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
