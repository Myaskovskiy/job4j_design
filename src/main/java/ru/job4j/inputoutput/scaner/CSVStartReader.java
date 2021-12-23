package ru.job4j.inputoutput.scaner;

import ru.job4j.inputoutput.ArgsName;

public class CSVStartReader {
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
