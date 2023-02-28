package hr.fer.oprpp1.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**	This test class is used for really simple testing of ComplexRootedPolynomial class. It does not guarantee that
 *  the ComplexRootedPolynomial works perfectly. It only covers very simple examples of testing. For more accurate
 *  testing, multiple marginal cases should be written.
 * 
 * 	@author adrian
 */
public class ComplexRootedPolynomialTest {
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
	public void applyTest() {
		Complex num = Complex.IM;
		ComplexRootedPolynomial f = new ComplexRootedPolynomial(
				new Complex(2, 1),
				new Complex(3, 3),
				new Complex(-2, -2)
				);
		Complex result = f.apply(num);
		assertEquals(result.getReal(), 13);
		assertEquals(result.getImag(), -26);
	}
	
	@Test
	public void indexOfClosestRootTest() {
		ComplexRootedPolynomial f = new ComplexRootedPolynomial(
				Complex.IM,
				new Complex(7, 9),
				new Complex(5, 4),
				new Complex(4, 5)
				);
		Complex num = new Complex(1,1);
		assertEquals(f.indexOfClosestRootFor(num, 0), -1);
		assertEquals(f.indexOfClosestRootFor(num, 5), 1);
		assertEquals(f.indexOfClosestRootFor(num, 4), -1);
		assertEquals(f.indexOfClosestRootFor(num, 9), 1);
		assertEquals(f.indexOfClosestRootFor(num, 9.99), 1);
		assertEquals(f.indexOfClosestRootFor(num, 10), 0);
	}
	
	@Test
	public void toStringTest() {
		System.setOut(new PrintStream(outContent));
		ComplexRootedPolynomial poly = new ComplexRootedPolynomial(
				new Complex(1,0),
				new Complex(5,0),
				new Complex(2,0),
				new Complex(7,2)
				);
		System.out.println(poly);
		String shouldBe = "f(z) = (1.0 + i0.0) * (z - (5.0 + i0.0)) * (z - (2.0 + i0.0)) * (z - (7.0 + i2.0))\n";
		assertEquals(shouldBe, outContent.toString());
		System.setOut(originalOut);
	}
	
	@Test
	public void toComplexPolynomTest() {
		System.setOut(new PrintStream(outContent));
		ComplexRootedPolynomial crp = new ComplexRootedPolynomial(
				new Complex(1,-1), new Complex(1, 1), new Complex(2,3)
				);
		ComplexPolynomial shouldBe = new ComplexPolynomial(
				new Complex(4,6),
				new Complex(-7,-1),
				new Complex(1, -1)
				);
		ComplexPolynomial cp = crp.toComplexPolynom();
		assertEquals(shouldBe, cp);
	}
	
	@Test
	public void providedExampleTest() {
		System.setOut(new PrintStream(outContent));
		ComplexRootedPolynomial crp = new ComplexRootedPolynomial(
				new Complex(2,0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG
				);
		ComplexPolynomial cp = crp.toComplexPolynom();
		System.out.println(crp);
		System.out.println(cp);
		System.out.println(cp.derive());
		
		String shouldBe = "f(z) = (2.0 + i0.0) * (z - (1.0 + i0.0)) * (z - (-1.0 + i0.0)) * (z - (0.0 + i1.0)) * (z - (0.0 - i1.0))\n"
				+ "f(z) = (2.0 + i0.0)*z^4 + (0.0 + i0.0)*z^3 + (0.0 + i0.0)*z^2 + (0.0 + i0.0)*z^1 + (-2.0 + i0.0) \n"
				+ "f(z) = (8.0 + i0.0)*z^3 + (0.0 + i0.0)*z^2 + (0.0 + i0.0)*z^1 + (0.0 + i0.0) \n";
	
		assertEquals(shouldBe, outContent.toString());
		System.setOut(originalOut);
	}
	
	
}
