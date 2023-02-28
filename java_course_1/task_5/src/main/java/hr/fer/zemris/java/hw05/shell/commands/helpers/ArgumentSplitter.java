package hr.fer.zemris.java.hw05.shell.commands.helpers;

import java.util.ArrayList;
import java.util.List;

public class ArgumentSplitter {
	
	public static List<String> pathSplitter(String arguments){
		List<String> result = new ArrayList<>();
		int indexStart = arguments.indexOf("\"");
		int indexEnd;
		while(indexStart != -1) {
			indexEnd = arguments.indexOf("\"", indexStart + 1);
			if(indexEnd == -1){
				throw new IllegalArgumentException("Missing quotation marks!");
			}
			result.add(arguments.substring(indexStart, indexEnd));
		}
		return result;
	}
	
	public static List<String> normalSplitter(String arguments){
		List<String> result = new ArrayList<>();
		String[] args = arguments.split(" ");
		for(String arg: args) {
			result.add(arg.trim());
		}
		return result;
	}
	
	public static void main(String[] args) {
		String arguments1 = "/src/main/resources hw05.txt";
		List<String> result = normalSplitter(arguments1);
		result.forEach(System.out::println);
	}
}
