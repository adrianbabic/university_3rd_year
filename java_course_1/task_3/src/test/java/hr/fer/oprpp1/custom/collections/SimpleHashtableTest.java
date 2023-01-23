package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {
	
	SimpleHashtable<String, Integer> example;
	
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
	public void toStringTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Kristina", 4);
		example.put("Ivana", 5);
		assertEquals("[Ante=2, Ivana=5, Jasna=3, Kristina=4]", example.toString());
		assertEquals(4, example.size());
	}
	
	@Test
	public void putTest() {
		example = new SimpleHashtable<>(2); 
		assertThrows(NullPointerException.class, () -> example.put(null, 5));
	}
	
	@Test
	public void getTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Kristina", 4);
		example.put("Ivana", 5);
		assertEquals(5, example.get("Ivana"));
		assertEquals(2, example.get("Ante"));
		assertEquals(null, example.get("Dominik"));
	}
	
	@Test
	public void containsKeyTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		assertEquals(true, example.containsKey("Ante"));
		assertEquals(false, example.containsKey("Dominik"));
		assertEquals(false, example.containsKey("JAsna"));
	}
	
	@Test
	public void containsValueTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Ivana", 5);
		assertEquals(true, example.containsValue(5));
		assertEquals(false, example.containsValue(1));
		assertEquals(false, example.containsValue(8));
	}
	
	@Test
	public void removeTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Ivana", 5);
		assertEquals(3, example.size());
		assertEquals(3, example.remove("Jasna"));
		assertEquals(null, example.remove("Jasna"));
		assertEquals(2, example.size());
	}
	
	@Test
	public void isEmptyTest() {
		example = new SimpleHashtable<>(2); 
		assertEquals(true, example.isEmpty());
		example.put("Ivana", 1);
		example.put("Ante", 2);
		assertEquals(false, example.isEmpty());
	}
	
	@Test
	public void toArrayTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Ivana", 5);
		assertEquals(3, example.toArray().length);
	}
	
	@Test
	public void adjustingCapacityTest() {
		example = new SimpleHashtable<>(13); 
		assertEquals(16, example.getCapacity());
	}
	
	@Test
	public void capacityTest() {
		example = new SimpleHashtable<>(7); 
		assertEquals(8, example.getCapacity());
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Kristina", 4);
		example.put("Marko", 5);
		example.put("Ivan", 6);
		assertEquals(8, example.getCapacity());
		example.put("Marko", 55);
		assertEquals(8, example.getCapacity());
		assertEquals(0.75, (double)example.size() / example.getCapacity());
		example.put("Zvonimir", 7);
		assertEquals(16, example.getCapacity());
	}
	
	@Test
	public void clearTest() {
		example = new SimpleHashtable<>(2); 
		example.put("Ivana", 1);
		example.put("Ante", 2);
		example.put("Jasna", 3);
		example.put("Ivana", 5);
		example.clear();
		assertEquals(true, example.isEmpty());
		assertEquals(4, example.getCapacity());
	}
	
	@Test
	public void iteratorExample1Test() {
		System.setOut(new PrintStream(outContent));
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); 
		for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks) {
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
		}
		assertEquals("Ante => 2\n"
				+ "Ivana => 5\n"
				+ "Jasna => 2\n"
				+ "Kristina => 5\n", outContent.toString());
		System.setOut(originalOut);
	}
	
	
	@Test
	public void iteratorExample2Test() {
		System.setOut(new PrintStream(outContent));
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		for(SimpleHashtable.TableEntry<String,Integer> pair1 : examMarks) {
			for(SimpleHashtable.TableEntry<String,Integer> pair2 : examMarks) {
			System.out.printf(
				"(%s => %d) - (%s => %d)%n",
				pair1.getKey(), pair1.getValue(),
				pair2.getKey(), pair2.getValue()
			);		
			}
		}
		assertEquals("(Ante => 2) - (Ante => 2)\n"
				+ "(Ante => 2) - (Ivana => 2)\n"
				+ "(Ivana => 2) - (Ante => 2)\n"
				+ "(Ivana => 2) - (Ivana => 2)\n", outContent.toString());
		System.setOut(originalOut);	
	}
	
	@Test
	public void iteratorExample3Test() {
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
		while(iter.hasNext()) {
			SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
			if(pair.getKey().equals("Ivana")) {
				iter.remove(); 
			}
		}
		assertEquals(false, examMarks.containsKey("Ivana"));
		assertEquals(1, examMarks.size());
	}
	
	@Test
	public void iteratorExample4Test() {
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
		while(iter.hasNext()) {
			SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
			if(pair.getKey().equals("Ivana")) {
				iter.remove();
				assertThrows(IllegalStateException.class, () -> iter.remove());
			}
		}
	}
	
	@Test
	public void iteratorExample5Test() {
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
		assertThrows(ConcurrentModificationException.class, () -> {
			while(iter.hasNext()) {
				SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
				if(pair.getKey().equals("Ivana")) {
					examMarks.remove("Ivana");
				}
			}
		});
	}
	
	@Test
	public void iteratorExample6Test() {
		System.setOut(new PrintStream(outContent));
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5);
		Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
		while(iter.hasNext()) {
			SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
			iter.remove();
		}
		assertEquals("Ante => 2\n"
				+ "Ivana => 5\n"
				+ "Jasna => 2\n"
				+ "Kristina => 5\n", outContent.toString());
		System.setOut(originalOut);	
		assertEquals(0, examMarks.size());
	}
	
}
