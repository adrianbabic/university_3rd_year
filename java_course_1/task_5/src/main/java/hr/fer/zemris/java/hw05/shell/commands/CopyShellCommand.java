package hr.fer.zemris.java.hw05.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

public class CopyShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments == null) {
			env.writeln("copy command should have two arguments.");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size != 2) {
			env.writeln("copy command should have two arguments.");
			return ShellStatus.CONTINUE;
		} 
		Path pathOriginal = Paths.get(inputs[0].trim());
		Path pathNew = Paths.get(inputs[1].trim());
		if(!Files.exists(pathOriginal)) {
			env.writeln("Given original file does not exist: " + pathOriginal.toString());
			return ShellStatus.CONTINUE;
		}
		if(Files.isDirectory(pathNew)) {
			pathNew = Paths.get(inputs[1].trim(), pathOriginal.getFileName().toString());
		}
		if(Files.exists(pathNew)) {
			String answer;
			env.writeln("Destination file already exits. Do you want to overwrite it? (y/n)");
			env.write(env.getPromptSymbol() + " ");
			while(true) {
				answer = env.readLine().toLowerCase();
				if(answer.equals("y") || answer.equals("n")) 
					break;
				env.writeln("Answer with a single letter (y/n). Do you want to overwrite already existing file?");
				env.write(env.getPromptSymbol() + " ");
			}
			if(answer.equals("n")) {
				env.writeln("Copy command aborted.");
				return ShellStatus.CONTINUE;
			}
		} else {
			env.writeln("Given directory does not exist: " + pathNew.getFileName().toString());
			return ShellStatus.CONTINUE;
		}
		try(InputStream is = new BufferedInputStream(
			 new FileInputStream(pathOriginal.toString())
			);
			OutputStream os = new BufferedOutputStream(
			 new FileOutputStream(pathNew.toString())
			);
		) {
			byte[] spremnik = new byte[1024];
			while(true) {
				int procitano = is.read(spremnik);
				if(procitano < 1) break;
				os.write(spremnik, 0, procitano);
			}
		} catch (IOException ex) {
			env.writeln("An error occured while copying files.");
		}
		env.writeln("Succesfully copied.");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "copy";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("The copy command expects two arguments: source file name and destination file name (i.e. paths and names).");
		descriptions.add("If destination file exists, user is asked is it allowed to overwrite it. Copy command must");
		descriptions.add("work only with files. If the second argument is directory, original file will be copied");
		descriptions.add("inside that directory.");
		return Collections.unmodifiableList(descriptions);
	}

}
