package ru.job4j.inputoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    private static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        String substring = "404";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains(substring)) {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {

            log.stream().forEach(out::println);
        //for (String str: log) {
            //       out.println(str);
         //   }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}