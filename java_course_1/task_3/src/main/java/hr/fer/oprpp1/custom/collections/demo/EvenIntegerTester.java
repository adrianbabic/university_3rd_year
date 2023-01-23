package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.Tester;

public class EvenIntegerTester<E> implements Tester<E> {
	public boolean test(E obj) {
		 if(!(obj instanceof Integer)) return false;
		 Integer i = (Integer)obj;
		 return i % 2 == 0;
		 }
}
