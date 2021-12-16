package ru.job4j.inputoutput;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("не заданы аргументы");
        }
        for (String s: args) {
            String key = s.substring(s.indexOf("-") + 1, s.indexOf("="));
            String value = s.substring(s.indexOf("=") + 1);
            if (key.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException();
            }
                this.values.put(key, value);
            }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println("Xmx" + " arg " + jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println("out" + " arg " + zip.get("out"));
    }
}
