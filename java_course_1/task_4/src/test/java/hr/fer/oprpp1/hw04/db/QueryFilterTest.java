package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class QueryFilterTest {
		
	@Test
	public void exampleTest() {		
		QueryParser parser = new QueryParser("jmbag>=\"0000000008\" and lastName>\"J\"");
		QueryFilter filter = new QueryFilter(parser.getQuery());
		StudentRecord student1 = new StudentRecord("0000000008", "Peric", "Pero", 4);
		StudentRecord student2 = new StudentRecord("00000000010", "Ivic", "Ivo", 5);
		StudentRecord student3 = new StudentRecord("0000000007", "Karlovic", "Karlo", 5);
		StudentRecord student4 = new StudentRecord("00000000400", "Markovic", "Marko", 5);
		assertEquals(true, filter.accepts(student1));
		assertEquals(false, filter.accepts(student2));
		assertEquals(false, filter.accepts(student3));
		assertEquals(true, filter.accepts(student4));
	}

}
