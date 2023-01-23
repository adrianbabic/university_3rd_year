package hr.fer.oprpp1.custom.collections.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.custom.collections.Tester;

public class EvenIntegerTesterTest {
	
	
	@Test
	public void homeworkExample() {
		Tester t = new EvenIntegerTester();
		assertEquals(false, t.test("Ivo"));
		assertEquals(true, t.test(22));
		assertEquals(false, t.test(3));
	}

}
