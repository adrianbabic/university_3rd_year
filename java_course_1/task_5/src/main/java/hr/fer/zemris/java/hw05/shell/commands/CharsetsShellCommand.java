package hr.fer.zemris.java.hw05.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellIOException;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

public class CharsetsShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
		if(arguments != null) {
			env.writeln("command charsets takes no arguments.");
			return ShellStatus.CONTINUE;
		}
		Map<String, Charset> charsets = Charset.availableCharsets();
		for(String name: charsets.keySet()) {
			env.writeln(name);
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "charsets";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("Command charsets takes no arguments and lists names of supported charsets for");
		descriptions.add("your JAVA platform. A single charset is written per line.");
		return Collections.unmodifiableList(descriptions);
	}

}
