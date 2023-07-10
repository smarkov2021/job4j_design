package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> pathMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
        pathMap.computeIfAbsent(newFile, k -> new ArrayList<>()).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attrs);
    }

    public void getPathMap() {
        for (Map.Entry<FileProperty, List<String>> entry : pathMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey().toString());
                for (Object path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}