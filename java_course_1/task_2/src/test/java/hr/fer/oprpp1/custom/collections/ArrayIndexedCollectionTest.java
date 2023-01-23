/**
 * 
 */
package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.custom.collections.Collection.ElementsGetter;
import hr.fer.oprpp1.custom.collections.demo.EvenIntegerTester;



/**
 * @author adrian
 *
 */
public class ArrayIndexedCollectionTest {
	
	private ArrayIndexedCollection example;
	
	
	/** Next four variables are used for storing messages that are
	 * 	written to standard output. They are used for various test usages.
	 *
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	
	/** Test controls if everything is working with tests that are
	 * 	testing standard output messages. 
	 *
	 */
	@Test
	public void out() {
	    System.setOut(new PrintStream(outContent));
	    System.out.print("hello");
	    assertEquals("hello", outContent.toString());
	    System.setOut(originalOut);
	}

	/** Test controls if everything is working with tests that are
	 * 	testing standard error messages. 
	 *
	 */
	@Test
	public void err() {
		System.setErr(new PrintStream(errContent));
	    System.err.print("hello again");
	    assertEquals("hello again", errContent.toString());
	    System.setErr(originalErr);
	}

	
	@Test
	public void testTooSmallArray() {
		assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection(-1));
	}
	
	@Test
	public void testDefaultConstructor() {
		example = new ArrayIndexedCollection();
		assertEquals(16, example.toArray().length);
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
		assertEquals(5, toTest.toArray().length);
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
		assertEquals(5, toTest.toArray().length);
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
		assertEquals(6, example.toArray().length);
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
		assertEquals(4, example.toArray().length);
		assertEquals(3, example.size());
		example.clear();
		assertEquals(4, example.toArray().length);
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
		assertEquals(1000, example.toArray()[3]);
	}
	
	@Test
	public void testInsertShifting() {
		example = new ArrayIndexedCollection(3);
		example.add(1);
		example.add(2);
		example.add(3);
		example.insert(1000, 1);
		assertEquals(1, example.toArray()[0]);
		assertEquals(1000, example.toArray()[1]);
		assertEquals(2, example.toArray()[2]);
		assertEquals(3, example.toArray()[3]);
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
		assertEquals(1, example.toArray()[0]);
		assertEquals(3, example.toArray()[1]);
		assertEquals(2, example.size());
	}
	
	@Test
	public void elementsGetterReturningValues() {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		assertEquals(true, getter.hasNextElement());
		assertEquals("Ivo", getter.getNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals("Ana", getter.getNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals("Jasna", getter.getNextElement());
		assertEquals(false, getter.hasNextElement());
		assertThrows(NoSuchElementException.class, () -> getter.getNextElement());
	}
	
	@Test
	public void hasNextElementIdempotency() {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals("Ivo", getter.getNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals("Ana", getter.getNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals(true, getter.hasNextElement());
		assertEquals("Jasna", getter.getNextElement());
		assertEquals(false, getter.hasNextElement());
		assertEquals(false, getter.hasNextElement());
	}
	
	@Test
	public void withoutHasNextElementMethod() {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		assertEquals("Ivo", getter.getNextElement());
		assertEquals("Ana", getter.getNextElement());
		assertEquals("Jasna", getter.getNextElement());
		assertThrows(NoSuchElementException.class, () -> getter.getNextElement());
	}
	
	@Test
	public void elementsGetterIndependence() {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter1 = col.createElementsGetter();
		ElementsGetter getter2 = col.createElementsGetter();
		assertEquals("Ivo", getter1.getNextElement());
		assertEquals("Ana", getter1.getNextElement());
		assertEquals("Ivo", getter2.getNextElement());
		assertEquals("Jasna", getter1.getNextElement());
		assertEquals("Ana", getter2.getNextElement());
	}
	
	@Test
	public void doubleCollectionElementsGetter() {
		Collection col1 = new ArrayIndexedCollection();
		Collection col2 = new ArrayIndexedCollection();
		col1.add("Ivo");
		col1.add("Ana");
		col1.add("Jasna");
		col2.add("Jasmina");
		col2.add("Štefanija");
		col2.add("Karmela");
		ElementsGetter getter1 = col1.createElementsGetter();
		ElementsGetter getter2 = col1.createElementsGetter();
		ElementsGetter getter3 = col2.createElementsGetter();
		assertEquals("Ivo", getter1.getNextElement());
		assertEquals("Ana", getter1.getNextElement());
		assertEquals("Ivo", getter2.getNextElement());
		assertEquals("Jasmina", getter3.getNextElement());
		assertEquals("Štefanija", getter3.getNextElement());
	}
	
	
	@Test
	public void concurrentModification() {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		assertEquals("Ivo", getter.getNextElement());
		assertEquals("Ana", getter.getNextElement());
		col.clear();
		assertThrows(ConcurrentModificationException.class, () -> getter.getNextElement());
	}	
	
	@Test
	public void processRemainingTest() {
		System.setOut(new PrintStream(outContent));
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		getter.getNextElement();
	    getter.processRemaining(System.out::println);
	    assertEquals("Ana\nJasna\n", outContent.toString());
	    System.setOut(originalOut);
	}	
	
	@Test
	public void addAllSatisfyingTest() {
		System.setOut(new PrintStream(outContent));
		Collection col1 = new LinkedListIndexedCollection();
		Collection col2 = new ArrayIndexedCollection();
		col1.add(2);
		col1.add(3);
		col1.add(4);
		col1.add(5);
		col1.add(6);
		col2.add(12);
		col2.addAllSatisfying(col1, new EvenIntegerTester());
		col2.forEach(System.out::println);
		
		assertEquals("12\n2\n4\n6\n", outContent.toString());
	    System.setOut(originalOut);
	}
	
	@Test
	public void testInterfaceList() {
		System.setOut(new PrintStream(outContent));
		List col1 = new ArrayIndexedCollection();
		List col2 = new LinkedListIndexedCollection();
		col1.add("Ivana");
		col2.add("Jasna");
		Collection col3 = col1;
		Collection col4 = col2;
		col1.get(0);
		col2.get(0);
//		col3.get(0); // neće se prevesti jer sucelje Collection po definiciji nema metodu get
//		col4.get(0); 
		col1.forEach(System.out::println); 
		col2.forEach(System.out::println); 
		col3.forEach(System.out::println); 
		col4.forEach(System.out::println); 
		
		assertEquals("Ivana\nJasna\nIvana\nJasna\n", outContent.toString());
	    System.setOut(originalOut);
	}
	
	
	
}
