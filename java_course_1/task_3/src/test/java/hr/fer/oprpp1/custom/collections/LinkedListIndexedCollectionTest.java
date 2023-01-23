package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.custom.collections.Collection.ElementsGetter;
import hr.fer.oprpp1.custom.collections.demo.EvenIntegerTester;

class LinkedListIndexedCollectionTest {

	private LinkedListIndexedCollection<Integer> example;
	private LinkedListIndexedCollection<String> example1;
	
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
	public void testConstructorWithArgument() {
		example = new LinkedListIndexedCollection<>();
		example.add(1);
		example.add(2);
		example.add(3);
		LinkedListIndexedCollection<Integer> exampleTest = new LinkedListIndexedCollection<>(example);
		Object[] exampleArray = example.toArray();
		Object[] exampleTestArray = exampleTest.toArray();
		assertEquals(exampleArray[0], exampleTestArray[0]);
		assertEquals(exampleArray[1], exampleTestArray[1]);
		assertEquals(exampleArray[2], exampleTestArray[2]);
	}
	
	@Test
	public void testAddingNull() {
		example = new LinkedListIndexedCollection<>();
		assertThrows(NullPointerException.class, () -> example.add(null));
	}
	
	@Test
	public void testFirstElement() {
		example1 = new LinkedListIndexedCollection<>();
		example1.add("Carrot");
		assertEquals(1, example1.size());
		assertEquals("Carrot", example1.toArray()[0]);
	}
	
	@Test
	public void testAddingToEnd() {
		example1 = new LinkedListIndexedCollection<>();
		example1.add("Carrot");
		example1.add("Potato");
		example1.add("Tomato");
		assertEquals(3, example1.size());
		assertEquals("Tomato", example1.toArray()[2]);
	}
	
	@Test
	public void testGetMethodWrongIndex() {
		example1 = new LinkedListIndexedCollection<>();
		example1.add("Carrot");
		example1.add("Potato");
		example1.add("Tomato");
		assertThrows(IndexOutOfBoundsException.class, () -> example1.get(3));
	}
	
	@Test
	public void testGetMethodCorrectIndex() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(2, example.get(2));
		assertEquals(8, example.get(8));
	}
	
	@Test
	public void testClear() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(10, example.size());
		example.clear();
		assertEquals(0, example.size());
	}
	
	@Test
	public void testIndexOfNonExistant() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(-1, example.indexOf(10));
	}
	
	
	@Test
	public void testIndexOfExistant() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(7, example.indexOf(7));
	}
	
	@Test
	public void testIndexOfNull() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertEquals(-1, example.indexOf(null));
	}
	
	@Test
	public void testRemoveWrongArgument() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertThrows(IndexOutOfBoundsException.class, () -> example.remove(10));
	}
	
	@Test
	public void testRemoving() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		example.remove(1);
		assertEquals(9, example.toArray()[8]);
		assertEquals(2, example.toArray()[1]);
		assertEquals(9, example.size());
	}
	
	@Test
	public void testInsertWrongIndex() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		assertThrows(IndexOutOfBoundsException.class, () -> example.insert(1000, 10));
	}
	
	@Test
	public void testInsertCorrectIndex() {
		example = new LinkedListIndexedCollection<>();
		for(int i = 0; i < 10; i++) example.add(i);
		example.insert(1000, 3);
		assertEquals(1000, example.toArray()[3]);
		assertEquals(3, example.toArray()[4]);
		assertEquals(9,example.toArray()[10]);
	}
	
	@Test
	public void elementsGetterReturningValues() {
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter = col.createElementsGetter();
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
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter = col.createElementsGetter();
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
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter = col.createElementsGetter();
		assertEquals("Ivo", getter.getNextElement());
		assertEquals("Ana", getter.getNextElement());
		assertEquals("Jasna", getter.getNextElement());
		assertThrows(NoSuchElementException.class, () -> getter.getNextElement());
	}
	
	@Test
	public void elementsGetterIndependence() {
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter1 = col.createElementsGetter();
		ElementsGetter<String> getter2 = col.createElementsGetter();
		assertEquals("Ivo", getter1.getNextElement());
		assertEquals("Ana", getter1.getNextElement());
		assertEquals("Ivo", getter2.getNextElement());
		assertEquals("Jasna", getter1.getNextElement());
		assertEquals("Ana", getter2.getNextElement());
	}
	
	@Test
	public void doubleCollectionElementsGetter() {
		Collection<String> col1 = new LinkedListIndexedCollection<>();
		Collection<String> col2 = new LinkedListIndexedCollection<>();
		col1.add("Ivo");
		col1.add("Ana");
		col1.add("Jasna");
		col2.add("Jasmina");
		col2.add("Štefanija");
		col2.add("Karmela");
		ElementsGetter<String> getter1 = col1.createElementsGetter();
		ElementsGetter<String> getter2 = col1.createElementsGetter();
		ElementsGetter<String> getter3 = col2.createElementsGetter();
		assertEquals("Ivo", getter1.getNextElement());
		assertEquals("Ana", getter1.getNextElement());
		assertEquals("Ivo", getter2.getNextElement());
		assertEquals("Jasmina", getter3.getNextElement());
		assertEquals("Štefanija", getter3.getNextElement());
	}
	
	@Test
	public void concurrentModification() {
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter = col.createElementsGetter();
		assertEquals("Ivo", getter.getNextElement());
		assertEquals("Ana", getter.getNextElement());
		col.clear();
		assertThrows(ConcurrentModificationException.class, () -> getter.getNextElement());
	}
	
	@Test
	public void processRemainingTest() {
		System.setOut(new PrintStream(outContent));
		Collection<String> col = new LinkedListIndexedCollection<>();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter<String> getter = col.createElementsGetter();
		getter.getNextElement();
	    getter.processRemaining(System.out::println);
	    assertEquals("Ana\nJasna\n", outContent.toString());
	    System.setOut(originalOut);
	}	
	
	@Test
	public void addAllSatisfyingTest() {
		System.setOut(new PrintStream(outContent));
		Collection<Integer> col1 = new ArrayIndexedCollection<>();
		Collection<Integer> col2 = new LinkedListIndexedCollection<>();
		col1.add(2);
		col1.add(3);
		col1.add(4);
		col1.add(5);
		col1.add(6);
		col2.add(12);
		col2.addAllSatisfying(col1, new EvenIntegerTester<>());
		col2.forEach(System.out::println);
		
		assertEquals("12\n2\n4\n6\n", outContent.toString());
	    System.setOut(originalOut);
	}
	
	@Test
	public void testInterfaceList() {
		System.setOut(new PrintStream(outContent));
		List<String> col2 = new ArrayIndexedCollection<>();
		List<String> col1 = new LinkedListIndexedCollection<>();
		col1.add("Ivana");
		col2.add("Jasna");
		Collection<String> col3 = col1;
		Collection<String> col4 = col2;
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
	
	@Test
	public void testContainsAfterParametrization() {
		List<String> col1 = new LinkedListIndexedCollection<>();
		col1.add("Boeing747");
		assertEquals(false, col1.contains(new Plane("Boeing747")));
	}
	
	/** Class used for testContainsAfterParametrization() test.
	 *
	 */
	private static class Plane{
		@SuppressWarnings("unused")
		private String type;
		
		public Plane(String type) {
			this.type = type;
		}
	}
	

}
