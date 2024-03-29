package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(String
                    .format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            int index = arg.indexOf("=");
            if (arg.charAt(0) != '-') {
                throw new IllegalArgumentException(String
                        .format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (index == 1) {
                throw new IllegalArgumentException(String
                        .format("Error: This argument '%s' does not contain a key",  arg));
            }
            if (index == arg.length() - 1) {
                throw new IllegalArgumentException(String
                        .format("Error: This argument '%s' does not contain a value",  arg.substring(0, index + 1)));
            }
            if (index == -1) {
                throw new IllegalArgumentException(String
                        .format("Error: This argument '%s' does not contain an equal sign",  arg));
            }
            values.put(arg.substring(1, index), arg.substring(index + 1));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(String
                    .format("Arguments not passed to program"));
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}