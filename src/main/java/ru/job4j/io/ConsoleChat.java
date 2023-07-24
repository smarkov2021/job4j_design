package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        String ask;
        boolean isStop = false;
        do {
            ask = in.nextLine();
            log.add(ask);
            if (STOP.equals(ask))  {
                isStop = true;
            } else if (CONTINUE.equals(ask) && isStop) {
                String answer = answers.get(rand.nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
                isStop = false;
            } else if (!isStop && !OUT.equals(ask)) {
                String answer = answers.get(rand.nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
            }
        } while (!OUT.equals(ask));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = null;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            rsl = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./log.txt", "./text.txt");
        cc.run();
    }
}