package hr.fer.oprpp1.hw04.db.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw04.db.StudentRecord;

public class RecordFormatterTest {
	
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
	public void example1() {
		System.setOut(new PrintStream(outContent));
	    
		StudentRecord student1 = new StudentRecord("0000000001", "Peric", "Pero", 4);
		StudentRecord student2 = new StudentRecord("0000000002", "Leonardovic", "Leonardo", 5);
		StudentRecord student3 = new StudentRecord("0000000003", "Nikic", "Nika", 5);
		List<StudentRecord> input = new ArrayList<>();
		input.add(student1);
		input.add(student2);
		input.add(student3);
		List<String> output = RecordFormatter.format(input, 11, 8);
		output.forEach(System.out::println);
		String shouldBe = 
		"+============+=============+==========+===+\n" +
		"| 0000000001 | Peric       | Pero     | 4 |\n" +
		"| 0000000002 | Leonardovic | Leonardo | 5 |\n" +
		"| 0000000003 | Nikic       | Nika     | 5 |\n" + 
		"+============+=============+==========+===+\n" +
		"Records selected: 3\n"
		;
	    assertEquals(shouldBe, outContent.toString());
	    System.setOut(originalOut);
	}
	
	@Test
	public void example2() {
		System.setOut(new PrintStream(outContent));
	    
		StudentRecord student1 = new StudentRecord("0000000001", "Peric", "Pero", 4);
		StudentRecord student3 = new StudentRecord("0000000003", "Maro", "Marica", 5);
		List<StudentRecord> input = new ArrayList<>();
		input.add(student1);
		input.add(student3);
		List<String> output = RecordFormatter.format(input, 5, 6);
		output.forEach(System.out::println);
		String shouldBe = 
		"+============+=======+========+===+\n" +
		"| 0000000001 | Peric | Pero   | 4 |\n" +
		"| 0000000003 | Maro  | Marica | 5 |\n" + 
		"+============+=======+========+===+\n" +
		"Records selected: 2\n"
		;
	    assertEquals(shouldBe, outContent.toString());
	    System.setOut(originalOut);
	}
	
}
