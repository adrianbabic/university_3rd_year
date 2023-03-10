package hr.fer.zemris.java.hw05.shell;

import java.util.Collections;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import hr.fer.zemris.java.hw05.shell.commands.CatShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.CharsetsShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.CopyShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.ExitShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.HelpShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.HexdumpShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.LsShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.MkdirShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.SymbolShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.TreeShellCommand;

public class ShellEnvironment implements Environment{
	private Character promptSymbol;
	private Character moreLinesSymbol;
	private Character multiLineSymbol;
	private SortedMap<String, ShellCommand> commands;
	private Scanner scanner;
	
	public ShellEnvironment(Scanner scanner) {
		this.promptSymbol = '>';
		this.moreLinesSymbol = '\\';
		this.multiLineSymbol = '|';
		this.commands = new TreeMap<>();
		this.scanner = scanner;
		loadCommands();
	}

	@Override
	public String readLine() throws ShellIOException {
		if(scanner.hasNext()) {
			return scanner.nextLine();
		} else {
			throw new ShellIOException("Readline method in shell failed!");
		}
	}

	@Override
	public void write(String text) throws ShellIOException {
		System.out.print(text);
	}

	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);
	}

	@Override
	public SortedMap<String, ShellCommand> commands() {
		return Collections.unmodifiableSortedMap(this.commands);
	}

	@Override
	public Character getMultilineSymbol() {
		return this.multiLineSymbol;
	}

	@Override
	public void setMultilineSymbol(Character symbol) {
		this.multiLineSymbol = symbol;
	}

	@Override
	public Character getPromptSymbol() {
		return this.promptSymbol;
	}

	@Override
	public void setPromptSymbol(Character symbol) {
		this.promptSymbol = symbol;
	}

	@Override
	public Character getMorelinesSymbol() {
		return this.moreLinesSymbol;
	}

	@Override
	public void setMorelinesSymbol(Character symbol) {
		this.moreLinesSymbol = symbol;
		
	}
	
	private void loadCommands() {
		commands.put("cat", new CatShellCommand());
		commands.put("charsets", new CharsetsShellCommand());
		commands.put("copy", new CopyShellCommand());
		commands.put("exit", new ExitShellCommand());
		commands.put("help", new HelpShellCommand());
		commands.put("hexdump", new HexdumpShellCommand());
		commands.put("ls", new LsShellCommand());
		commands.put("mkdir", new MkdirShellCommand());
		commands.put("symbol", new SymbolShellCommand());
		commands.put("tree", new TreeShellCommand());
	}
}
