package com.ron.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

class SingletonSerializationFix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SingletonSerializationFix soleInstance = null;

	// private constructor
	private SingletonSerializationFix() {
		System.out.println("private singleton class constructor...");
	}

	// static method that returns sole instance of class Singleton
	public static SingletonSerializationFix getInstance() {
		if (soleInstance == null) {
			soleInstance = new SingletonSerializationFix();
		}
		return soleInstance;
	}

	private Object readResolve() throws ObjectStreamException {
		// change behaviour of de-serialization process here
		System.out.println("readResolve() called...");
		return soleInstance;
	}
}

public class SingletonSerializationFix07 {

	// Ensure that a class has only one instance & provide a global point of access
	// to it.

	// cannot use new keyword to create instance of singleton class

	// 1) make sure that there is only one instance - restrict construction i.e.
	// make your constructor private & let class manage its instance

	// 2) Provide a global point of access - generally a static method to get the
	// sole instance

	// Fixing singleton serialization problem

	// 1) Address issue of readResolve(). During de-serialization, java called
	// readResolve() just after de-serialization . You can override that method &
	// can change the behaviour of that method.

	// 2) return same soleInstance from readResolve()

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// testing creating instance of Singleton class
		// will also run class private constructor only once
		SingletonSerializationFix singletonOne = SingletonSerializationFix.getInstance();
		SingletonSerializationFix singletonTwo = SingletonSerializationFix.getInstance();

		// Since same instances, both must have same hashcode. Hence it is singleton.
		System.out.println("singletonOne hashcode() : " + singletonOne.hashCode());
		System.out.println("singletonTwo hashcode() : " + singletonTwo.hashCode());

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
			// Serialize object of singleton class
			oos.writeObject(singletonOne);
			// now de-serialize object of singleton class
			SingletonSerializationFix singleton3 = (SingletonSerializationFix) ois.readObject();

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
