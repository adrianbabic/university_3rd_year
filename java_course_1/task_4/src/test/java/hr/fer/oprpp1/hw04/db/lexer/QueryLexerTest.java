package hr.fer.oprpp1.hw04.db.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QueryLexerTest {
	private QueryLexer example;
	
	@Test
	public void attributeTest() {
		example = new QueryLexer("    lastName   = \"0000000009\"    ");
		Token token = example.nextToken();
		assertEquals(TokenType.ATTRIBUTE, token.getType());
		assertEquals("lastName", token.getValue());
		example = new QueryLexer(" LASTNAME = \"0000000003\"");
		assertThrows(LexerException.class, () -> example.nextToken());
	}
	
	@Test
	public void operatorTest() {
		example = new QueryLexer("    lastName   >= \"0000000009\"    ");
		example.nextToken();
		Token token = example.nextToken();
		assertEquals(TokenType.OPERATOR, token.getType());
		assertEquals(">=", token.getValue());
	}
	
	@Test
	public void stringLiteralTest() {
		example = new QueryLexer("    lastName   >= \"0000000009\"    ");
		example.nextToken();
		example.nextToken();
		Token token = example.nextToken();
		assertEquals(TokenType.LITERAL, token.getType());
		assertEquals("0000000009", token.getValue());
	}
	
	@Test
	public void logicalOperatorTest() {
		example = new QueryLexer("    lastName   >= \"0000000009\"  AnD firstName != \"B*\"  ");
		example.nextToken();
		example.nextToken();
		example.nextToken();
		Token token = example.nextToken();
		assertEquals(TokenType.LOGICAL_OPERATOR, token.getType());
		assertEquals(null, token.getValue());
	}

}
