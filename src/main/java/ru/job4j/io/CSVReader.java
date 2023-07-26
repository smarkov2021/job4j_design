package ru.job4j.io;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Error: There is not all parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Error: There is incorrect source extension");
        }
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Error: Sources file doesn't exists");
        }
    }
    public static void handle(ArgsName argsName) throws Exception {
        List<List<String>> allRsl = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(argsName.get("path")))) {
            br.lines().forEach(e -> {
                var scanner = new Scanner(new ByteArrayInputStream(e.getBytes()))
                        .useDelimiter(argsName.get("delimiter"));
                List<String> newList = new ArrayList<>();
                while (scanner.hasNext()) {
                    newList.add(scanner.next());
                }
                allRsl.add(newList);
            });
            String[] elem = argsName.get("filter").split(",");
            for (String filter : elem) {
                if (allRsl.get(0).contains(filter)) {
                    indexes.add(allRsl.get(0).indexOf(filter));
                }

            }
            for (List<String> list : allRsl) {
                StringBuilder res = new StringBuilder();
                for (int innerIndex = 0; innerIndex < indexes.size(); innerIndex++) {
                    res.append(list.get(indexes.get(innerIndex)));
                    if (innerIndex < indexes.size() - 1) {
                        res.append(argsName.get("delimiter"));
                    }
                }
                rsl.add(String.valueOf(res));
            }
            if ("stdout".equals(argsName.get("out"))) {
                for (String elm : rsl) {
                    System.out.println(elm);
                }
            } else {
                try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out"),
                        Charset.forName("WINDOWS-1251"), true))) {
                    rsl.forEach(pw::println);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}