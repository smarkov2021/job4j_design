package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> interval = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(obj -> {
                        if ((obj.contains("400") || obj.contains("500")) && (interval.size() % 5 == 0)) {
                            interval.add(obj.substring(4));
                            interval.add(";");
                        } else if ((obj.contains("200") || obj.contains("300")) && (interval.size() % 5 == 2)) {
                            interval.add(obj.substring(4));
                            interval.add(";");
                            interval.add("\n");

                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
                interval.forEach(out::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}