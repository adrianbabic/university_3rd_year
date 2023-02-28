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

public class MkdirShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments == null) {
			env.writeln("mkdir command should have a single argument.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size != 1) {
			env.writeln("mkdir command should have a single argument.");
			return ShellStatus.CONTINUE;
		} 
		Path path = Paths.get(inputs[0].trim());
		try {
		    Files.createDirectories(path);
		    env.writeln("Directory successfully created!");
		} catch (IOException e) {
			env.writeln("Failed to create directory!");
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "mkdir";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("The mkdir command takes a single argument: directory name, and creates");
		descriptions.add("the appropriate directory structure.");
		return Collections.unmodifiableList(descriptions);
	}

}
