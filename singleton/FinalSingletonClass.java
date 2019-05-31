package com.ron.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

class SingletonSole implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private constructor to stop creating singleton objects
	private SingletonSole() {
		if (Holder.SINGLETON_HOLDER != null) {
			throw new RuntimeException("couldn't be able to create singleton");
		}
		System.out.println("private constructor of singletonsole class");
	}

	public static SingletonSole getInstance() {
		return Holder.SINGLETON_HOLDER;
	}

	private static class Holder {
		private static final SingletonSole SINGLETON_HOLDER = new SingletonSole();
	}

	// to fix singleton problem while cloning
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return Holder.SINGLETON_HOLDER;
	}

	// to fix de-serializtion singleton problem
	private Object readResolve() throws ObjectStreamException {
		// change behaviour of de-serialization process here
		// System.out.println("readResolve() called...");
		return Holder.SINGLETON_HOLDER;
	}
}

public class FinalSingletonClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonSole s1 = SingletonSole.getInstance();
		SingletonSole s2 = SingletonSole.getInstance();
		System.out.println("s1=" + s1.hashCode());
		System.out.println("s1=" + s1.hashCode());

		SingletonSole s3;
		try {
			s3 = (SingletonSole) s1.clone();
			System.out.println("s1=" + s1.hashCode());
			System.out.println("s3=" + s3.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
