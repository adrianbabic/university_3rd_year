package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DictionaryTest {
	
	Dictionary<Integer, String> example;
	
	
	@Test
	public void puttingNullKeysTest() {
		example = new Dictionary<>();
		assertThrows(IllegalArgumentException.class, () -> example.put(null, "Null key"));
	}
	
	@Test
	public void sizeTest() {
		example = new Dictionary<>();
		assertEquals(0, example.size());
		example.put(1, "Prvi");
		example.put(2, "Drugi");
		example.put(3, "Treci");
		assertEquals(3, example.size());
	}
	
	@Test
	public void isEmptyTest() {
		example = new Dictionary<>();
		assertEquals(true, example.isEmpty());
		example.put(1, "Prvi");
		assertEquals(false, example.isEmpty());
	}
	
	@Test
	public void clearTest() {
		example = new Dictionary<>();
		assertEquals(true, example.isEmpty());
		example.put(1, "Prvi");
		example.put(2, "Drugi");
		example.put(3, "Treci");
		assertEquals(false, example.isEmpty());
		example.clear();
		assertEquals(true, example.isEmpty());
	}
	
	@Test
	public void putAndGetTest() {
		example = new Dictionary<>();
		example.put(1, "Prvi");
		example.put(2, "Drugi");
		assertEquals("Drugi", example.get(2));
		assertEquals(2, example.size());
		example.put(2, "DrugiUpdated");
		assertEquals("DrugiUpdated", example.get(2));
		assertEquals(2, example.size());
	}
	
	@Test
	public void removeTest() {
		example = new Dictionary<>();
		example.put(1, "Prvi");
		example.put(2, "Drugi");
		example.put(3,  "Treci");
		assertEquals("Drugi", example.get(2));
		assertEquals(3, example.size());
		assertEquals(null, example.remove(4));
		assertEquals(3, example.size());
		assertEquals("Treci", example.remove(3));
		assertEquals(2, example.size());
	}

}
