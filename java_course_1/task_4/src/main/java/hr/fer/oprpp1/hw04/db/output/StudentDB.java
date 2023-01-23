package hr.fer.oprpp1.hw04.db.output;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.oprpp1.hw04.db.QueryFilter;
import hr.fer.oprpp1.hw04.db.QueryParser;
import hr.fer.oprpp1.hw04.db.StudentDatabase;
import hr.fer.oprpp1.hw04.db.StudentRecord;

/**	Class used for accepting the queries from user and processing them.
 * 
 * 	@author adrian
 */
public class StudentDB {
	
	/** Method is reading the user input and processing it. Only supported commands are query and exit.
	 * 	If query command is valid, this method prints all the student records from the 
	 * 	database that satisify requirements of the given query.
	 * 	
	 * 	@throws LexerException if given arguments in query are not valid
	 * 	@throws IllegalStateException if the order of the given arguments in query is not valid
	 */
	public static void main(String[] args) throws IOException {
		List<String> retci = Files.readAllLines(Paths.get("src/test/resources/database.txt"), StandardCharsets.UTF_8);
		StudentDatabase data = new StudentDatabase(retci);
		List<StudentRecord> records = new ArrayList<>();
		QueryParser parser;
		int maxLastName = 0;
		int maxFirstName = 0;
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("> ");
			if(scanner.hasNextLine()) {
				String uneseno = scanner.nextLine().trim();
				if(uneseno.equals("exit")) {
					System.out.println("Goodbye!");
					break;
				} 
				if(!uneseno.startsWith("query")) {
					System.out.println("Invalid: The only supported commands are query and exit!");
					continue;
				}
				try {
					parser = new QueryParser(uneseno.substring(5));	
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
					continue;
				}
//				parser = new QueryParser(uneseno.substring(5));	
				if(parser.isDirectQuery()) {
					StudentRecord r = data.forJMBAG(parser.getQueriedJMBAG());
					maxFirstName = r.getFirstName().length();
					maxLastName = r.getLastName().length();
					System.out.println("Using index for record retrieval.");
					records.add(r);
				} else {
					for(StudentRecord r : data.filter(new QueryFilter(parser.getQuery()))) {
						if(r.getLastName().length() > maxLastName)
							maxLastName = r.getLastName().length();
						if(r.getFirstName().length() > maxFirstName)
							maxFirstName = r.getFirstName().length();
						records.add(r);
					}
				}
				
				List<String> output = RecordFormatter.format(records, maxLastName, maxFirstName);
				records.clear();
				maxFirstName = 0;
				maxLastName = 0;
				if(output.size() > 3)
					output.forEach(System.out::println);
				else 
					System.out.println("Records selected: 0");
			} else {
				System.out.println("Scanner does not have next token!");
			}
		}
		scanner.close();

	}

}
