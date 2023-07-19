package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static void validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Error: There is not all parameters");
        }
        File file = new File(args[0].substring(args[0].indexOf("=") + 1));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        if (!args[1].substring(args[0].indexOf("=") + 1).startsWith(".")
                || args[1].substring(args[0].indexOf("=") + 1).length() < 2) {
            throw new IllegalArgumentException(String
                    .format("'%s' - it's not extension", args[1].substring(args[0].indexOf("=") + 1)));
        }
        if (!args[2].endsWith(".zip")) {
            throw new IllegalArgumentException("Error: There is incorrect extension");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                    zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName jvm = ArgsName.of(args);
        Path start = Paths.get(jvm.get("d"));
        File target = new File(jvm.get("o"));
        List<Path> sources = new ArrayList<>(Search.search(start, p -> !p.toFile().getName().endsWith(jvm.get("e"))));
        Zip zip = new Zip();
        zip.packFiles(sources, target);
    }
}