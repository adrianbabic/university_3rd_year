package hr.fer.zemris.java.hw05.shell.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

public class HexdumpShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments == null) {
			env.writeln("hexdump command should have a single argument");
			return ShellStatus.CONTINUE;
		}
		String[] inputs = arguments.split(" ");
		int size = inputs.length; 
		if(size != 1) {
			env.writeln("hexdump command should have a single argument");
			return ShellStatus.CONTINUE;
		} 
		Path path = Paths.get(inputs[0].trim());
		if(!Files.exists(path)) {
			env.writeln("There is no such input file " + path.toString());
			return ShellStatus.CONTINUE;
		}
		try(BufferedReader reader =
				new BufferedReader(
				 new InputStreamReader(
				   Files.newInputStream(path, StandardOpenOption.READ)
					, StandardCharsets.UTF_8)) ) {
					char[] buf = new char[16];
					int counter = 0;
					while(true) {
						StringBuilder sb = new StringBuilder();
						sb.append(String.format("%08x", counter) + ": ");
						counter = counter + 16;
						int tmp = reader.read(buf);	
//						String redak = reader.readLine();
//						if(redak==null) break;
						if(tmp == -1) break;
//						env.writeln("Broj: " + tmp);
//						env.writeln("Duljina: " + buf.length);
						int max1 = Math.min(16,  tmp);
						for(int i = 0; i < max1; i++){
							sb.append(charToHex(buf[i]) + " ");
							if(i == 7)
								sb.append("| ");
						}
						if(max1 < 8) {
							sb.append("   ".repeat(8 - max1));
							sb.append("| ");
						}
						if(max1 < 16) {
							sb.append("   ".repeat(16 - Math.max(8, tmp)));
						}
						sb.append("| ");
						for(int i = 0; i < tmp; i++) {
							char c = buf[i];
							if(c < 32 || c > 127)
								sb.append(".");
							else
								sb.append(c);
						}
						env.writeln(sb.toString());
					}
			} catch(IOException ex) {
				throw new ShellIOException("Error while trying to read from file during cat command.");
			}
			return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "hexdump";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> descriptions = new ArrayList<>();
		descriptions.add("The hexdump command expects a single argument: file name. On the right side of the output ");
		descriptions.add("only a standard subset of characters is shown; for all other characters a");
		descriptions.add("'.' is printed instead (all bytes whose value is less than 32 or greater");
		descriptions.add(" than 127 are replaced with '.').");
		return Collections.unmodifiableList(descriptions);
	}
	
	private String charToHex(char c) {
        byte lo = (byte) (c & 0xff);
        return byteToHex(lo);
	}
	
	private String byteToHex(byte b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
        return new String(array);
	}

}
