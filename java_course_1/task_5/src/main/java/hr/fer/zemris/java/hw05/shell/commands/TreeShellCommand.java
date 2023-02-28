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
import hr.fer.zemris.java.hw05.shell.commands.visitors.TreeCommandVisitor;

public class TreeShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments == null) {
			env.writeln("tree command should have a single argument.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size != 1) {
			env.writeln("tree command should have a single argument.");
			return ShellStatus.CONTINUE;
		} 
		Path path = Paths.get(inputs[0].trim());
		if(!Files.exists(path)) {
			env.writeln("There is no such directory " + path.toString());
			return ShellStatus.CONTINUE;
		}
		
		TreeCommandVisitor visitor = new TreeCommandVisitor();
		try {
			Files.walkFileTree(path, visitor);
		} catch (IOException e) {
			env.writeln("An error occured while walking file tree using tree command.");
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
		return "tree";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("The tree command expects a single argument: directory name and prints a tree (each directory");
		descriptions.add("level shifts output two charatcers to the right).");
		return Collections.unmodifiableList(descriptions);
	}

}
