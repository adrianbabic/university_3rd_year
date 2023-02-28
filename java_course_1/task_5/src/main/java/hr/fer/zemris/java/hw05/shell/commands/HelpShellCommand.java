package hr.fer.zemris.java.hw05.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellIOException;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

public class HelpShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
		if(arguments == null) {
			env.writeln("Supporteds commands are: cat, charsets, copy, exit, help, hexdump, ls, mkdir, symbol and tree.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length;
		if(size != 1) {
			env.writeln("help command should come with at maximum one argument.");
			return ShellStatus.CONTINUE;
		}
		SortedMap<String, ShellCommand> commands = env.commands();
		String commandName = inputs[0].trim();
		ShellCommand command = commands.get(commandName);
		if(command == null) {
			env.writeln("Given command is not supported: " + commandName);
		} else {
			List<String> lines = command.getCommandDescription();
			env.writeln(command.getCommandName());
			env.writeln(" ");
			for(String line: lines) {
				env.writeln(line);
			}
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("If started with no arguments, it must list names of all supported commands. If started with one");
		descriptions.add("argument, returns name and the description of selected command. If the command does not ");
		descriptions.add("exist, returns error message");
		return Collections.unmodifiableList(descriptions);
	}

}
