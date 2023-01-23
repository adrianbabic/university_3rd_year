package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FieldValueGettersTest {
	
	private List<String> retci;
	private List<StudentRecord> records;
	
	public FieldValueGettersTest() throws IOException {
		this.retci = Files.readAllLines(Paths.get("src/test/resources/database.txt"), StandardCharsets.UTF_8);
		StudentDatabase data = new StudentDatabase(retci);
		this.records = data.filter(f -> true);
	}
	@Test
	public void exampleTest() {
		StudentRecord record = records.get(
				(int)(Math.random()*records.size())
				);
		assertEquals(record.getFirstName(), FieldValueGetters.FIRST_NAME.get(record));
		assertEquals(record.getLastName(), FieldValueGetters.LAST_NAME.get(record));
		assertEquals(record.getJmbag(), FieldValueGetters.JMBAG.get(record));
	}

}
