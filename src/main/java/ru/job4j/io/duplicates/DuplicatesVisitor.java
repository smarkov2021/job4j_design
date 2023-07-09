package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> pathMap = new HashMap<FileProperty, List<String>>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
        List<String> newList = pathMap.get(newFile) == null ? new ArrayList<>() : pathMap.get(newFile);
        newList.add(file.toAbsolutePath().toString());
        pathMap.put(newFile, newList);
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