package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List> pathMap = new HashMap<FileProperty, List>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
        if (pathMap.get(newFile) != null) {
            pathMap.get(newFile).add(file.toAbsolutePath());
        } else {
            List<String> newList = new ArrayList<>();
            newList.add(file.toAbsolutePath().toString());
            pathMap.put(newFile, newList);
        }
        return super.visitFile(file, attrs);
    }

    public void getPathMap() {
        for (Map.Entry<FileProperty, List> entry : pathMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey().toString());
                for (Object path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}