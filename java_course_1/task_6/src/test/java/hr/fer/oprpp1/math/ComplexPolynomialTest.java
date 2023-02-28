package hr.fer.oprpp1.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**	This test class is used for really simple testing of ComplexPolynomial class. It does not guarantee that
 *  the ComplexPolynomial works perfectly. It only covers very simple examples of testing. For more accurate
 *  testing, multiple marginal cases should be written.
 * 
 * 	@author adrian
 */
public class ComplexPolynomialTest {
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
	public void orderTest() {
		ComplexPolynomial poly = new ComplexPolynomial();
		assertEquals(poly.order(), 0);
		poly = new ComplexPolynomial(
				new Complex(1,0),
				new Complex(5,0),
				new Complex(2,0),
				new Complex(7,2)
				);
		assertEquals(poly.order(), 3);
	}
	
	@Test
	public void multiplyTest() {
		ComplexPolynomial poly1 = new ComplexPolynomial(
				new Complex(5, 0),
				new Complex(0, 0),
				new Complex(10, 0),
				new Complex(6, 0)
				);
		ComplexPolynomial poly2 = new ComplexPolynomial(
				new Complex(1,0),
				new Complex(2,0),
				new Complex(4, 0)
				);
		ComplexPolynomial result = poly1.multiply(poly2);
		ComplexPolynomial shouldBe = new ComplexPolynomial(
				new Complex(5, 0),
				new Complex(10, 0),
				new Complex(30, 0),
				new Complex(26, 0),
				new Complex(52, 0),
				new Complex(24, 0)
				);
		assertEquals(result, shouldBe);
	}
	
	// (7+2i)z^3+2z^2+5z+1 returns (21+6i)z^2+4z+5
	@Test
	public void deriveTest() {
		ComplexPolynomial poly = new ComplexPolynomial(
				new Complex(1, 0),
				new Complex(5, 0),
				new Complex(2, 0),
				new Complex(7, 2)
				);
		ComplexPolynomial result = poly.derive();
		ComplexPolynomial shouldBe = new ComplexPolynomial(
				new Complex(5,0),
				new Complex(4,0),
				new Complex(21, 6)
				);
		assertEquals(result, shouldBe);
	}
	
	@Test 
	public void applyTest() {
		ComplexPolynomial poly = new ComplexPolynomial(
				new Complex(1,0),
				new Complex(5,0),
				new Complex(2,0),
				new Complex(7,2)
				);
		Complex num = new Complex(2, 1);
		Complex result = poly.apply(num);
		assertEquals(new Complex(9, 94), result);
	}
	
	@Test
	public void toStringTest() {
		System.setOut(new PrintStream(outContent));
		ComplexPolynomial poly = new ComplexPolynomial(
				new Complex(1,0),
				new Complex(5,0),
				new Complex(2,0),
				new Complex(7,2)
				);
		System.out.println(poly);
		String shouldBe = "f(z) = (7.0 + i2.0)*z^3 + (2.0 + i0.0)*z^2 + (5.0 + i0.0)*z^1 + (1.0 + i0.0) \n";
		assertEquals(shouldBe, outContent.toString());
		System.setOut(originalOut);
	}
	
}
