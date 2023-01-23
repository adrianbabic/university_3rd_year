package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListIndexedCollectionTest {

	private LinkedListIndexedCollection example;
	
	
	@Test
	public void testConstructorWithArgument() {
		example = new LinkedListIndexedCollection();
		example.add(1);
		example.add(2);
		example.add(3);
		LinkedListIndexedCollection exampleTest = new LinkedListIndexedCollection(example);
		Object[] exampleArray = example.toArray();
		Object[] exampleTestArray = exampleTest.toArray();
		assertEquals(exampleArray[0], exampleTestArray[0]);
		assertEquals(exampleArray[1], exampleTestArray[1]);
		assertEquals(exampleArray[2], exampleTestArray[2]);
	}
	
	@Test
	public void testAddingNull() {
		example = new LinkedListIndexedCollection();
		assertThrows(NullPointerException.class, () -> example.add(null));
	}
	
	@Test
	public void testFirstElement() {
		example = new LinkedListIndexedCollection();
		example.add("Carrot");
		assertEquals(1, example.size());
		assertEquals("Carrot", example.toArray()[0]);
	}
	
	@Test
	public void testAddingToEnd() {
		example = new LinkedListIndexedCollection();
		example.add("Carrot");
		example.add("Potato");
		example.add("Tomato");
		assertEquals(3, example.size());
		assertEquals("Tomato", example.toArray()[2]);
	}
	
	@Test
	public void testGetMethodWrongIndex() {
		example = new LinkedListIndexedCollection();
		example.add("Carrot");
		example.add("Potato");
		example.add("Tomato");
		assertThrows(IndexOutOfBoundsException.class, () -> example.get(3));
	}
	
	@Test
	public void testGetMethodCorrectIndex() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(2, example.get(2));
		assertEquals(8, example.get(8));
	}
	
	@Test
	public void testClear() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(10, example.size());
		example.clear();
		assertEquals(0, example.size());
	}
	
	@Test
	public void testIndexOfNonExistant() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(-1, example.indexOf(10));
	}
	
	
	@Test
	public void testIndexOfExistant() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(7, example.indexOf(7));
	}
	
	@Test
	public void testIndexOfNull() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(-1, example.indexOf(null));
	}
	
	@Test
	public void testRemoveWrongArgument() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertThrows(IndexOutOfBoundsException.class, () -> example.remove(10));
	}
	
	@Test
	public void testRemoving() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		example.remove(1);
		assertEquals(9, example.toArray()[8]);
		assertEquals(2, example.toArray()[1]);
		assertEquals(9, example.size());
	}
	
	@Test
	public void testInsertWrongIndex() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		assertThrows(IndexOutOfBoundsException.class, () -> example.insert(1000, 10));
	}
	
	@Test
	public void testInsertCorrectIndex() {
		example = new LinkedListIndexedCollection();
		for(int i = 0; i < 10; i++) example.add(i);
		example.insert(1000, 3);
		assertEquals(1000, example.toArray()[3]);
		assertEquals(3, example.toArray()[4]);
		assertEquals(9,example.toArray()[10]);
	}
	

	
	

}
