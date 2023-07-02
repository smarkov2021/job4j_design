package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
 public void unavailable(String source, String target) {
     try (PrintWriter out = new PrintWriter(
             new BufferedOutputStream(
                     new FileOutputStream(target)
             ))) {
         boolean isActive = true;
         try (BufferedReader read = new BufferedReader(new FileReader(source))) {
             for (String elem:read.lines().toList()) {
                 if ((elem.contains("400") || elem.contains("500")) && (isActive)) {
                     out.print(elem.substring(4));
                     out.print(";");
                     isActive = false;
                 } else if ((elem.contains("200") || elem.contains("300")) && (!isActive)) {
                     out.print(elem.substring(4));
                     out.print(";");
                     out.print(System.lineSeparator());
                     isActive = true;
                 }
             }
         }     catch (IOException e) {
         e.printStackTrace();
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