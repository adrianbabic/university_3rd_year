/**
 * 
 */
package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author adrian
 *
 */
public class ArrayIndexedCollectionTest {
	
	private ArrayIndexedCollection example;

	
	@Test
	public void testTooSmallArray() {
		assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection(-1));
	}
	
	@Test
	public void testDefaultConstructor() {
		example = new ArrayIndexedCollection();
		assertEquals(0, example.size());
	}
	
	@Test
	public void testNullCollection() {
		example = null;	
		assertThrows(NullPointerException.class, () -> new ArrayIndexedCollection(example, 15));
	}
	
	@Test
	public void testChoosingSize() {
		example = new ArrayIndexedCollection(5);
		example.add(1);
		example.add(2);
		example.add(3);
		example.add(4);
		example.add(5);
		ArrayIndexedCollection toTest = new ArrayIndexedCollection(example,4);
		assertEquals(5, toTest.size());
	}
	
	@Test
	public void testCopyingCollection() {
		example = new ArrayIndexedCollection(5);
		example.add(1);
		example.add(2);
		example.add(3);
		example.add(4);
		example.add(5);
		ArrayIndexedCollection toTest = new ArrayIndexedCollection(example);
		assertEquals(5, toTest.size());
	}
	
	@Test
	public void testAddingNull() {
		example = new ArrayIndexedCollection(5);
		assertThrows(NullPointerException.class, () -> example.add(null));
	}
	
	@Test
	public void testDoublingWhenArrayIsFull() {
		example = new ArrayIndexedCollection(3);
		example.add(1);
		example.add(2);
		example.add(3);
		example.add(4);
		assertEquals(4, example.size());
	}
	
	@Test
	public void testGetMethodWrongIndex() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		assertThrows(IndexOutOfBoundsException.class, () -> example.get(3));
	}
	
	@Test
	public void testGetMethodCorrectIndex() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		assertEquals(3, example.get(2));
	}
	
	@Test
	public void testClear() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		assertEquals(3, example.size());
		example.clear();
		assertEquals(0, example.size());
	}
	
	@Test
	public void testInsertWrongIndex() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		assertThrows(IndexOutOfBoundsException.class, () -> example.insert("test", 4));
	}
	
	@Test
	public void testInsertCorrectIndex() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		example.insert(1000, 3);
		assertEquals(1000, example.get(3));
	}
	
	@Test
	public void testInsertShifting() {
		example = new ArrayIndexedCollection(3);
		example.add(1);
		example.add(2);
		example.add(3);
		example.insert(1000, 1);
		assertEquals(1, example.get(0));
		assertEquals(1000, example.get(1));
		assertEquals(2, example.get(2));
		assertEquals(3, example.get(3));
	}
	
	@Test
	public void testIndexOfNonExistant() {
		example = new ArrayIndexedCollection(3);
		example.add(1);
		example.add(2);
		example.add(3);
		assertEquals(-1, example.indexOf(4));
	}
	
	@Test
	public void testIndexOfExistant() {
		example = new ArrayIndexedCollection(10);
		example.add(1);
		example.add(2);
		example.add(3);
		assertEquals(2, example.indexOf(3));
	}
	
	@Test
	public void testIndexOfNull() {
		example = new ArrayIndexedCollection(10);
		example.add(1);
		example.add(2);
		example.add(3);
		assertEquals(-1, example.indexOf(null));
	}
	
	@Test
	public void testRemoveWrongArgument() {
		example = new ArrayIndexedCollection(4);
		example.add(1);
		example.add(2);
		example.add(3);
		assertThrows(IndexOutOfBoundsException.class, () -> example.remove(4));
	}
	
	@Test
	public void testRemoving() {
		example = new ArrayIndexedCollection(3);
		example.add(1);
		example.add(2);
		example.add(3);
		example.remove(1);
		assertEquals(1, example.get(0));
		assertEquals(3, example.get(1));
		assertEquals(2, example.size());
	}	
	
}
