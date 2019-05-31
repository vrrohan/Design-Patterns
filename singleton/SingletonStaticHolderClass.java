package com.ron.singleton;

class SingletonHolder {
	private static volatile SingletonHolder soleInstance = null;

	private SingletonHolder() {
		if (soleInstance != null) {
			throw new RuntimeException("cannot create singleton object");
		}
		System.out.println("singleton private constructor...");
	}

	public static SingletonHolder getInstance() {
		return Holder.INSTANCE;
	}

	// inner static holder class
	// also known as Bill Pugh Singleton
	// this solution is thread-safe & doesn't required synchronization
	static class Holder {
		// this is lazy initialization
		static final SingletonHolder INSTANCE = new SingletonHolder();
	}
}

public class SingletonStaticHolderClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonHolder s1 = SingletonHolder.getInstance();
		SingletonHolder s2 = SingletonHolder.getInstance();

		System.out.println("s1=" + s1.hashCode());
		System.out.println("s1=" + s1.hashCode());

	}

}
