package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;


public class StudentDatabaseTest {
	List<String> retci;
	
	public StudentDatabaseTest() throws IOException {
		this.retci = Files.readAllLines(Paths.get("src/test/resources/database.txt"), StandardCharsets.UTF_8);
	}

	
	@Test
	public void forJMBAGtest() throws IOException {
		StudentDatabase data = new StudentDatabase(retci);
		int randomNumber = (int) (Math.random()*retci.size());
		String[] newRow = retci.get(randomNumber).split("\t");
		assertEquals(data.forJMBAG(newRow[0]), new StudentRecord(newRow[0], newRow[1], newRow[2], Integer.valueOf(newRow[3])));
	}
	
	@Test
	public void databaseFilterTrueTest() throws IOException {
		StudentDatabase data = new StudentDatabase(retci);
		assertEquals(data.filter(f -> true).size(), retci.size());	
	}
	
	@Test
	public void databaseFilterFalseTest() throws IOException {
		StudentDatabase data = new StudentDatabase(retci);
		assertEquals(data.filter(f -> false).size(), 0);	
	}
	
}
