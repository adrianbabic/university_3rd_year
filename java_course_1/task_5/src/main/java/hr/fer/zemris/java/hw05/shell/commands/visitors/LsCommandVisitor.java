package hr.fer.zemris.java.hw05.shell.commands.visitors;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LsCommandVisitor implements FileVisitor<Path>{
	private List<String> lines;
	private Path path;
	
	public LsCommandVisitor(Path path) {
		this.lines = new ArrayList<>();
		this.path = path;
	}
	
	public List<String> getInformation(){
		return this.lines;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		printInformation(dir, attrs);
		if(this.path.equals(dir))
			return FileVisitResult.CONTINUE;
		else 
			return FileVisitResult.SKIP_SUBTREE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		printInformation(file, attrs);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		lines.add(("Visit failed for file: " + file.getFileName().toString()));
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	private void printInformation(Path path, BasicFileAttributes attrs) {
		StringBuilder sb = new StringBuilder();
		sb.append(attrs.isDirectory() ? "d" : "-");
		sb.append(Files.isReadable(path) ? "r" : "-");
		sb.append(Files.isWritable(path) ? "w" : "-");
		sb.append(Files.isExecutable(path) ? "x" : "-");
		sb.append(String.format(" %10d ", attrs.size()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FileTime fileTime = attrs.creationTime();
		String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
		sb.append(formattedDateTime + " ");
		sb.append(path.getFileName().toString());
		lines.add(sb.toString());
	}
}
