package ru.job4j.io.exam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static Predicate<Path> actualPredicate(String typeSearch, String expression) {
        Predicate<Path> rsl = null;
        if ("name".equals(typeSearch)) {
            rsl = p -> p.toFile().getName().equals(expression);
        } else if ("regex".equals(typeSearch)) {
            Pattern pattern = Pattern.compile(expression);
            rsl = path -> {
                Matcher matcher = pattern.matcher(path.toFile().getName());
                return matcher.matches();
            };
        } else if ("mask".equals(typeSearch)) {
            String chgExpression = expression.replaceAll("\\*", "\\\\S{0,}")
                    .replaceAll("\\?", "\\\\S{0,1}");
            Pattern pattern = Pattern.compile(chgExpression);
            rsl = path -> {
                Matcher matcher = pattern.matcher(path.toFile().getName());
                return matcher.matches();
            };
        } else {
            throw new IllegalArgumentException(String
                    .format("Error: '%s' it's incorrect parameter."
                            + "You can choose one possible value(name, regex, mask)", typeSearch));
        }
        return rsl;
    }

    public static void main(String[] args)  throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = in.readLine();
                    String[] str = answer.substring(11, answer.length() - 9).split("-");
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    ArgsName names = ArgsName.of(str);
                    File path = new File(names.get("d"));
                    List<Path> newList = search(path.toPath(), actualPredicate(names.get("t"), names.get("n")));
                    try (PrintWriter pw = new PrintWriter(new FileWriter(names.get("o")))) {
                        newList.forEach(pw::println);
                    }
                    out.flush();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        }
    }
}
