package hr.fer.oprpp1.custom.scripting.lexer;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class LexerTest {
	
	@Test
	public void example1Test() {
		String input ="{$ FOR i-1.35bbb\"1\" $}";
		Lexer lexer = new Lexer(input);
		Token current = lexer.nextToken();
		
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("for", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("-1.35", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("bbb", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.STRING, current.getTokenType());
		assertEquals("\"1\"", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
	}
	
	@Test
	public void example2Test() {
		String input = "This is sample text.\r\n"
				+ "{$ For i 1 10 1 $}\r\n"
				+ "This is {$= i $}-th time this message is generated.\r\n"
				+ "{$END$}\r\n"
				+ "{$FOR i 0 10 2 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n"
				+ "{$END$}";
		Lexer lexer = new Lexer(input);
		Token current = lexer.nextToken();
		
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("This is sample text.\r\n", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("for", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("1", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("10", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("1", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("\r\nThis is ", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("=", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("-th time this message is generated.\r\n", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("end", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("\r\n", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("for", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("0", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("10", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.NUMBER, current.getTokenType());
		assertEquals("2", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("\r\n sin(", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("=", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("^2) = ", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("=", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("i", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.OPERATOR, current.getTokenType());
		assertEquals("*", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.FUNCTION, current.getTokenType());
		assertEquals("@sin", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.STRING, current.getTokenType());
		assertEquals("\"0.000\"", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.FUNCTION, current.getTokenType());
		assertEquals("@decfmt", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TEXT, current.getTokenType());
		assertEquals("\r\n", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_START, current.getTokenType());
		assertEquals("{$", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.VARIABLE, current.getTokenType());
		assertEquals("end", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.TAG_END, current.getTokenType());
		assertEquals("$}", current.getElement().asText());
		
		current = lexer.nextToken();
		assertEquals(TokenType.EOF, current.getTokenType());
		assertEquals(null, current.getElement());
	}
	
	
}
