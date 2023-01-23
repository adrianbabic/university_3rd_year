package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ConditionalExpressionTest {


	private List<String> retci;
	private List<StudentRecord> records;
	
	public ConditionalExpressionTest() throws IOException {
		this.retci = Files.readAllLines(Paths.get("src/test/resources/database.txt"), StandardCharsets.UTF_8);
		StudentDatabase data = new StudentDatabase(retci);
		this.records = data.filter(f -> true);
	}
	@Test
	public void exampleTest() {		
		ConditionalExpression expr = new ConditionalExpression(
				 FieldValueGetters.LAST_NAME,
				 "Bos*",
				 ComparisonOperators.LIKE
				);
		StudentRecord record = records.get(
				(int)(Math.random()*records.size())
				);
		boolean toTest;
		toTest = expr.getFieldGetter().get(record).startsWith("Bos") ? true : false; 
		boolean recordSatisfies = expr.getComparisonOperator().satisfied(
		 expr.getFieldGetter().get(record), // returns lastName from given record
		 expr.getStringLiteral() // returns "Bos*"
		);
		
		assertEquals(toTest, recordSatisfies);
	}

}
