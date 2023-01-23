package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ComparisonOperatorsTest {

	@Test
	public void lessTest() {
		IComparisonOperator oper = ComparisonOperators.LESS;
		assertEquals(oper.satisfied("Ana", "Jasna"), true); 
		assertEquals(oper.satisfied("Jasna", "Ana"), false);
		assertEquals(oper.satisfied("Ana", "Ana"), false); 
	}
	
	@Test
	public void lessOrEqualsTest() {
		IComparisonOperator oper = ComparisonOperators.LESS_OR_EQUALS;
		assertEquals(oper.satisfied("Ana", "Jasna"), true); 
		assertEquals(oper.satisfied("Jasna", "Ana"), false);
		assertEquals(oper.satisfied("Ana", "Ana"), true); 
	}
	
	@Test
	public void greaterTest() {
		IComparisonOperator oper = ComparisonOperators.GREATER;
		assertEquals(oper.satisfied("Ana", "Jasna"), false); 
		assertEquals(oper.satisfied("Jasna", "Ana"), true);
		assertEquals(oper.satisfied("Ana", "Ana"), false); 
	}
	
	@Test
	public void greaterOrEqualsTest() {
		IComparisonOperator oper = ComparisonOperators.GREATER_OR_EQUALS;
		assertEquals(oper.satisfied("Ana", "Jasna"), false); 
		assertEquals(oper.satisfied("Jasna", "Ana"), true);
		assertEquals(oper.satisfied("Ana", "Ana"), true); 
	}
	
	@Test
	public void equalsTest() {
		IComparisonOperator oper = ComparisonOperators.EQUALS;
		assertEquals(oper.satisfied("Ana", "Jasna"), false); 
		assertEquals(oper.satisfied("Jasna", "Ana"), false);
		assertEquals(oper.satisfied("Ana", "Ana"), true); 
	}
	
	@Test
	public void notEqualsTest() {
		IComparisonOperator oper = ComparisonOperators.NOT_EQUALS;
		assertEquals(oper.satisfied("Ana", "Jasna"), true); 
		assertEquals(oper.satisfied("Jasna", "Ana"), true);
		assertEquals(oper.satisfied("Ana", "Ana"), false); 
	}
	
	@Test
	public void likeTest() {
		IComparisonOperator oper = ComparisonOperators.LIKE;
		assertEquals(oper.satisfied("Zagreb", "Aba*"), false);
		assertEquals(oper.satisfied("AAA", "AA*AA"), false); 
		assertEquals(oper.satisfied("AAAA", "AA*AA"), true);
		assertEquals(oper.satisfied("AAAA", "AAA"), false);
		assertEquals(oper.satisfied("AAAA", "AAAAB"), false);
		assertEquals(oper.satisfied("AAAAB", "AAAA"), false);
		assertEquals(oper.satisfied("AAAA", "*AAA"), true);
		assertEquals(oper.satisfied("AAAA", "AAAA"), true);
		assertThrows(IllegalArgumentException.class, () -> oper.satisfied("Zagreb", "Za*gr*eb"));
	}

}
