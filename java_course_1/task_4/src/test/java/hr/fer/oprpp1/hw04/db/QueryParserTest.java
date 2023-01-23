package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import hr.fer.oprpp1.hw04.db.lexer.LexerException;

public class QueryParserTest {
		
	@Test
	public void singleQuery() {		
		QueryParser qp1 = new QueryParser(" jmbag =\"0123456789\" ");
		assertEquals(true, qp1.isDirectQuery()); //true
		assertEquals("0123456789", qp1.getQueriedJMBAG()); // 0123456789
		assertEquals(1, qp1.getQuery().size()); // 1
	}
	
	@Test
	public void mutltipleQueries() {		
		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\"");
		assertEquals(false, qp2.isDirectQuery()); // false
		assertEquals(2, qp2.getQuery().size()); // 2
		assertThrows(IllegalStateException.class, () -> qp2.getQueriedJMBAG()); // would throw
	}
	
	@Test
	public void wrongLogicalOperatorTest() {		
		assertThrows(LexerException.class, () -> {
			QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" andd lastName>\"J\"");
			qp2.getQueriedJMBAG();
		}); 
	}
	
	@Test
	public void missingConditionalExpression() {		
		assertThrows(IllegalStateException.class, () -> {
			QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName=\"J\" jmbag  ");
			qp2.getQueriedJMBAG();
		}); 
	}
	
	@Test
	public void incorrectConditionalExpression() {		
		assertThrows(IllegalStateException.class, () -> {
			QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName\"J\"");
			qp2.getQueriedJMBAG();
		}); 
	}
	
}
