package hr.fer.zemris.java.hw05.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellIOException;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

public class SymbolShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws ShellIOException {
		if(arguments == null) {
			env.writeln("symbol command should have between one and two arguments.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size < 1 || size > 2) {
			env.writeln("symbol command should have between one and two arguments.");
			return ShellStatus.CONTINUE;
		} else if(size == 1) {
			String option = inputs[0].trim();
			switch (option) {
			  case "PROMPT":
			    env.writeln("Symbol for PROMPT is '" + env.getPromptSymbol() +"'");
			    break;
			  case "MORELINES":
				env.writeln("Symbol for MORELINES is '" + env.getMorelinesSymbol() +"'");
			    break;
			  case "MULTILINE":
				env.writeln("Symbol for MULTILINE is '" + env.getMultilineSymbol() +"'");
			    break;
			  default:
			    env.writeln("Invalid option for command symbol. Available options are PROMPT, MORELINES and MULTILINE");
			}
		} else {
			String option = inputs[0].trim();
			String symbolString = inputs[1].trim();
			if(symbolString.length() != 1) {
				env.writeln("symbol command's second argument should be a single character!");
				return ShellStatus.CONTINUE;
			}
			Character symbol = symbolString.charAt(0);
			switch (option) {
			  case "PROMPT":
			    env.writeln("Symbol for PROMPT changed from '" + env.getPromptSymbol() +"' to '" + symbol +"'");
			    env.setPromptSymbol(symbol);
			    break;
			  case "MORELINES":
				env.writeln("Symbol for MORELINES changed from '" + env.getMorelinesSymbol() +"' to '" + symbol +"'");
				env.setMorelinesSymbol(symbol);
			    break;
			  case "MULTILINE":
				env.writeln("Symbol for MULTILINE changed from '" + env.getMultilineSymbol() +"' to '" + symbol +"'");
				env.setMultilineSymbol(symbol);
			    break;
			  default:
			    env.writeln("Invalid option for command symbol. Availiable options are PROMPT, MORELINES and MULTILINE");
			}
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "symbol";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("This command is used for returning current values of PROMPT, MORELINES and MULTILINE symbols.");
		descriptions.add("It is also used for changing the values of those symbols.");
		return Collections.unmodifiableList(descriptions);
	}

}
