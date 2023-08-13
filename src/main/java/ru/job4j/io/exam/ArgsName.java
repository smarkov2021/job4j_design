package ru.job4j.io.exam;

import java.util.HashMap;
import java.util.Map;

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
            if (index == 0) {
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
            values.put(arg.substring(0, index), arg.substring(index + 1));
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
}