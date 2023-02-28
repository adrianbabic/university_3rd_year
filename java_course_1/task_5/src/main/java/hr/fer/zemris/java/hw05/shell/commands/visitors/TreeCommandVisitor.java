package hr.fer.zemris.java.hw05.shell.commands.visitors;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class TreeCommandVisitor implements FileVisitor<Path>{
	private List<String> lines;
	private int identation;
	
	public TreeCommandVisitor() {
		this.lines = new ArrayList<>();
		this.identation = 0;
	}
	
	public List<String> getInformation(){
		return this.lines;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		lines.add(" ".repeat(identation) + dir.getFileName().toString());
		identation = identation + 2;
		return FileVisitResult.CONTINUE;

	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		lines.add(" ".repeat(identation) + file.getFileName().toString());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		lines.add(("Visit failed for file: " + file.getFileName().toString()));
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		identation = identation - 2;
		return FileVisitResult.CONTINUE;
	}

}
