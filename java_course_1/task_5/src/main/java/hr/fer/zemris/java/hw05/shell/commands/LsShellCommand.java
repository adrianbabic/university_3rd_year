package hr.fer.zemris.java.hw05.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;
import hr.fer.zemris.java.hw05.shell.commands.visitors.LsCommandVisitor;

public class LsShellCommand implements ShellCommand{
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments == null) {
			env.writeln("ls command should have a single argument.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size != 1) {
			env.writeln("ls command should have a single argument.");
			return ShellStatus.CONTINUE;
		} 
		Path path = Paths.get(inputs[0].trim());
		if(!Files.exists(path)) {
			env.writeln("There is no such directory " + path.toString());
			return ShellStatus.CONTINUE;
		}
		
		LsCommandVisitor visitor = new LsCommandVisitor(path);
		try {
			Files.walkFileTree(path, visitor);
		} catch (IOException e) {
			env.writeln("An error occured while walking file tree using ls command.");
			return ShellStatus.CONTINUE;
		}
		List<String> lines = visitor.getInformation();
		for(String line: lines) {
			env.writeln(line);
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "ls";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("Command ls takes a single argument – directory – and writes a directory listing (not recursive).");
		descriptions.add("The output consists of 4 columns. First column indicates if current object is directory (d),");
		descriptions.add("readable (r),writable (w) and executable (x). Second column contains object size in");
		descriptions.add("bytes that is right aligned and occupies 10 characters.");
		descriptions.add("Follows file creation date/time and finally file name.");
		return Collections.unmodifiableList(descriptions);
	}

}
