package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectStackTest {

	private ObjectStack example;
	
	
	@Test
	public void testConstructor() {
		example = new ObjectStack();
		assertEquals(example.size(), 0);
		assertEquals(example.isEmpty(), true);
		for(int i = 1; i < 6; i++) example.push(i);
		assertEquals(example.size(), 5);
		assertEquals(example.isEmpty(), false);
		assertEquals(example.pop(), 5);
		assertEquals(example.peek(), 4);
		assertEquals(example.size(), 4);
		example.clear();
		assertEquals(example.size(), 0);
		assertEquals(example.isEmpty(), true);
	}
	
	
	
	

}
