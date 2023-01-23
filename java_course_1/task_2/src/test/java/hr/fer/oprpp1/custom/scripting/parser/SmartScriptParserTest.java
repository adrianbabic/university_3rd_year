package hr.fer.oprpp1.custom.scripting.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import hr.fer.oprpp1.custom.scripting.lexer.LexerException;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;
import hr.fer.oprpp1.custom.scripting.nodes.EchoNode;
import hr.fer.oprpp1.custom.scripting.nodes.TextNode;

public class SmartScriptParserTest {
	
	/** This test does not guarantee this class is operating properly! Output should be similar to the input. 
	 * 	User should determine if the output is valid. 
	 */
	@Test
	public void nakedEyeTest() {
		String input = "This is sample text.\r\n"
				+ "{$ For i 1 10 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "{$END$}\r\n"
				+ "{$FOR i 0 10 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n"
				+ "{$END$}";
		
		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(input);
		} catch(SmartScriptParserException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Unable to parse document!");
			System.exit(-1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("If this line ever executes, you have failed this class!");
			System.exit(-1);
		}
		
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = document.toString();
		System.out.println(originalDocumentBody);
	
	}
	
	@Test
	public void example1Test() {
		String docBody = "This is sample text.\r\n"
				+ "{$ For i 1 10 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "{$END$}\r\n"
				+ "{$FOR i 0 10 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n"
				+ "{$END$}";
		SmartScriptParser parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = document.toString();
		SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
		DocumentNode document2 = parser2.getDocumentNode();
		// now document and document2 should be structurally identical trees
		boolean same = document.equals(document2);
		assertEquals(true, same);
	}
	
	@Test
	public void example2Test() {
		String docBody = "This is sample text.\r\n"
				+ "{$ For i 1 10 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "{$END$}\r\n"
				+ "{$FOR i 0 10 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n"
				+ "{$END$}";
		SmartScriptParser parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		String docBody2 = "This is sample text.\r\n"
				+ "{$ For i 1 10 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "{$END$}\r\n"
				+ "{$FOR i 0 10 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decft $}\r\n"   //difference in @decfmt
				+ "{$END$}";
		SmartScriptParser parser2 = new SmartScriptParser(docBody2);
		DocumentNode document2 = parser2.getDocumentNode();
		// now document and document2 should be structurally identical trees
		boolean same = document.equals(document2);
		assertEquals(false, same);
	}
	
	@Test
	public void foorLoopNotClosed() {
		String docBody2 = "This is sample text.\r\n"
				+ "{$ For i 1 10 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "\r\n"
				+ "{$FOR i 0 10 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decft $}\r\n"   //difference in @decfmt
				+ "{$END$}";
		// now document and document2 should be structurally identical trees
		assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser(docBody2));
	}
	
	private String readExample(int n) {
		  try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("extra/primjer"+n+".txt")) {
		    if(is==null) throw new RuntimeException("Datoteka extra/primjer"+n+".txt je nedostupna.");
		    byte[] data = is.readAllBytes();
		    String text = new String(data, StandardCharsets.UTF_8);
		    return text;
		  } catch(IOException ex) {
		    throw new RuntimeException("Greška pri čitanju datoteke.", ex);
		  }
	}
	
	@Test
	public void readExample1() {
		String text = readExample(1);
		SmartScriptParser parser = new SmartScriptParser(text);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(1, document.numberOfChildren());
		assertEquals(true, document.getChild(0) instanceof TextNode);
	}
	
	@Test
	public void readExample2() {
		String text = readExample(2);
		SmartScriptParser parser = new SmartScriptParser(text);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(1, document.numberOfChildren());
		assertEquals(true, document.getChild(0) instanceof TextNode);
	}
	
	@Test
	public void readExample3() {
		String text = readExample(3);
		SmartScriptParser parser = new SmartScriptParser(text);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(1, document.numberOfChildren());
		assertEquals(true, document.getChild(0) instanceof TextNode);
	}
	
	@Test
	public void readExample4() {
		String text = readExample(4);
		assertThrows(LexerException.class, () -> new SmartScriptParser(text));
	}
	
	@Test
	public void readExample5() {
		String text = readExample(5);
		assertThrows(LexerException.class, () -> new SmartScriptParser(text));
	}
	
	@Test
	public void readExample6() {
		String text = readExample(6);
		SmartScriptParser parser = new SmartScriptParser(text);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(2, document.numberOfChildren());
		assertEquals(true, document.getChild(0) instanceof TextNode);
		assertEquals(true, document.getChild(1) instanceof EchoNode);
	}
	
	@Test
	public void readExample7() {
		String text = readExample(7);
		SmartScriptParser parser = new SmartScriptParser(text);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(2, document.numberOfChildren());
		assertEquals(true, document.getChild(0) instanceof TextNode);
		assertEquals(true, document.getChild(1) instanceof EchoNode);
	}
	
	@Test
	public void readExample8() {
		String text = readExample(8);
		assertThrows(LexerException.class, () -> new SmartScriptParser(text));
	}
	
	@Test
	public void readExample9() {
		String text = readExample(9);
		assertThrows(LexerException.class, () -> new SmartScriptParser(text));
	}

}
