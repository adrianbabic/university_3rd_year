package hr.fer.oprpp1.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**	This test class is used for really simple testing of Complex class. It does not guarantee that the Complex works
 * 	perfectly. It only covers very simple examples of testing. For more accurate testing, marginal cases should be 
 * 	written.
 * 
 * 	@author adrian
 */
public class ComplexTest {
	
	@Test
	public void moduleTest() {
		Complex num = new Complex(4, 3);
		assertEquals(5, num.module());
	}
	
	@Test
	public void multiplyTest() {
		Complex num1 = new Complex(1, 1);
		Complex num2 = new Complex(1, 1);
		Complex result1 = num1.multiply(num2);
		Complex result2 = num2.multiply(num1);
		assertEquals(0, result1.getReal());
		assertEquals(2, result1.getImag());
		assertEquals(result1, result2);
	}
	
	@Test
	public void divideTest() {
		Complex num1 = new Complex(-3, -5);
		Complex num2 = new Complex(2, 2);
		Complex result1 = num1.divide(num2);
		assertEquals(-2.0, result1.getReal());
		assertEquals(-0.5, result1.getImag());
	}
	
	@Test
	public void addTest() {
		Complex num1 = new Complex(5, 3);
		Complex num2 = new Complex(4, 2);
		Complex result1 = num1.add(num2);
		Complex result2 = num2.add(num1);
		assertEquals(9, result1.getReal());
		assertEquals(5, result1.getImag());
		assertEquals(result1, result2);
	}
	
	@Test
	public void subTest() {
		Complex num1 = new Complex(5, 3);
		Complex num2 = new Complex(4, 2);
		Complex result1 = num1.sub(num2);
		Complex result2 = num2.sub(num1);
		assertEquals(1, result1.getReal());
		assertEquals(1, result1.getImag());
		assertEquals(-1, result2.getReal());
		assertEquals(-1, result2.getImag());
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void negateTest() {
		Complex num1 = new Complex(5, 3);
		Complex result1 = num1.negate();
		assertEquals(-5, result1.getReal());
		assertEquals(-3, result1.getImag());
		assertEquals(result1.module(), num1.module());
	}
	
	@Test 
	public void powerTest() {
		Complex num1 = new Complex(1, 1);
		Complex result1 = num1.power(18);
		List<Complex> roots = result1.root(18);
		boolean exists = false;
		for(Complex root: roots) {
			if(root.equals(num1)) {
				exists = true;
				break;
			}
		}
		assertEquals(true, exists);
	}
	
	@Test 
	public void rootTest() {
		Complex num1 = new Complex(3, 4);
		List<Complex> result1 = num1.root(2);
		assertEquals(2, result1.size());
		Complex num2 = result1.get(0);
		Complex num3 = result1.get(1);
		assertEquals(num1, num2.power(2));
		assertEquals(num1, num3.power(2));
	}
}
