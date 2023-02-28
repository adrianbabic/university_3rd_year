package hr.fer.zemris.java.hw05.shell;

import java.util.Scanner;
import java.util.SortedMap;

public class MyShell {

	public MyShell() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to MyShell v 1.0");
		Environment shell = new ShellEnvironment(scanner);
		SortedMap<String, ShellCommand> commands = shell.commands();
		ShellStatus shellStatus = ShellStatus.CONTINUE;
		while(shellStatus == ShellStatus.CONTINUE) {
			System.out.print(shell.getPromptSymbol() + " ");
			if(scanner.hasNext()) {
				String uneseno = scanner.nextLine().trim();
				uneseno = readWholeCommand(uneseno, scanner, shell);
				int splitter = uneseno.indexOf(" ");
				String commandName;
				String arguments = null;
				if(splitter == -1)
					commandName = uneseno;
				else {
					commandName = uneseno.substring(0, splitter);
					arguments = uneseno.substring(splitter + 1);
				}
				ShellCommand command = commands.get(commandName);
				if(command == null) {
					System.out.println("Entered command is not supported. Enter 'help' to get a list of all supported commands.");
					continue;
				}
				try {
					shellStatus = command.executeCommand(shell, arguments);
				} catch (ShellIOException ex) {
					System.out.println("Error: Communication with shell is not possible at the moment.");
					shellStatus = ShellStatus.TERMINATE;
				}
			} else {
				System.out.println("Scanner does not have next token!");
			}
		}
		scanner.close();
	}
	
	private static String readWholeCommand(String firstLine, Scanner scanner, Environment shell) {
		Character lastChar = firstLine.charAt(firstLine.length() - 1);
		if(!lastChar.equals(shell.getMorelinesSymbol()))
			return firstLine;
		StringBuilder sb = new StringBuilder();
		String currentLine = firstLine;
		while(lastChar.equals(shell.getMorelinesSymbol())) {
			currentLine = currentLine.substring(0, currentLine.length() - 1);
			sb.append(currentLine);
			System.out.print(shell.getMultilineSymbol() + " ");
			if(scanner.hasNext()) {
				currentLine = scanner.nextLine().trim();
				lastChar = currentLine.charAt(currentLine.length() - 1);
			} else {
				System.out.println("Missing token while reading the whole command!");
			}
		}
		sb.append(currentLine);
		return sb.toString();
	}

}
