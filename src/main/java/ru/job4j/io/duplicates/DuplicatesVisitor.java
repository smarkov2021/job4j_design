package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, String> pathMap = new HashMap<FileProperty, String>();
    Map<FileProperty, String> totalMap = new HashMap<FileProperty, String>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
            if (totalMap.get(newFile) != null) {
                String attr = pathMap.get(newFile) != null
                        ? pathMap.get(newFile) + "\n" + file.toAbsolutePath()
                        : totalMap.get(newFile) + "\n" + file.toAbsolutePath();
                pathMap.put(newFile, attr);
            } else {
                totalMap.put(newFile, file.toAbsolutePath().toString());
            }
        }
        return super.visitFile(file, attrs);
    }

    public void getPathMap() {
        for (FileProperty fp : pathMap.keySet()) {
            System.out.println(fp.getName() + "-" + fp.getSize());
            System.out.println(pathMap.get(fp));
        }
    }
}