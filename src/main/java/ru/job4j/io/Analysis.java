package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
 public void unavailable(String source, String target) {
     try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)));
          BufferedReader read = new BufferedReader(new FileReader(source))) {
         boolean isActive = true;
        while (read.ready()) {
            String elem = read.readLine();
            if ((elem.contains("400") || elem.contains("500")) == (isActive)) {
                out.append(elem.substring(4))
                        .append(";")
                        .append(isActive ? "" : System.lineSeparator());
                isActive = !isActive;
            }
        }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}