package hr.fer.zemris.java.hw05.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellIOException;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

public class CatShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
		if(arguments == null) {
			env.writeln("cat command should have between one and two arguments.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size < 1 || size > 2) {
			env.writeln("cat command should have between one and two arguments.");
			return ShellStatus.CONTINUE;
		} 
		Path path = Paths.get(inputs[0].trim());
		if(!Files.exists(path)) {
			env.writeln("There is no such input file " + path.toString());
			return ShellStatus.CONTINUE;
		}
		Charset charset;
		if(size == 1) {
			 charset = Charset.defaultCharset();
		} else {
			try {
				charset = Charset.forName(inputs[1].trim());
			} catch (Exception ex) {
				env.writeln("Given charset name is not available!");
				return ShellStatus.CONTINUE;
			}
		}
		
		try(BufferedReader reader =
			new BufferedReader(
			 new InputStreamReader(
			  new BufferedInputStream(
			   Files.newInputStream(path, StandardOpenOption.READ)
				), charset)) ) {
				while(true) {
					String redak = reader.readLine();
					if(redak==null) break;
					redak = redak.trim();
					env.writeln(redak);
				}
		} catch(IOException ex) {
			throw new ShellIOException("Error while trying to read from file during cat command.");
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "cat";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("Command cat takes one or two arguments. The first argument is path to some file and is mandatory.");
		descriptions.add("The second argument is charset name that should be used to interpret chars from bytes.");
		descriptions.add("If not provided, a default platform charset should be used (see java.nio.charset.Charset class for details).");
		descriptions.add("This command opens given file and writes its content to console.");
		return Collections.unmodifiableList(descriptions);
	}

}
