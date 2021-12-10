package ru.job4j.inputoutput.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DuplicatesFinder {
    /**
     * 4.2. Поиск дубликатов [#315066 #237811]
     */
    public static List<FileProperty> searcher(Path path) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(path, duplicatesVisitor);
        return duplicatesVisitor.getList();
    }

    public static void main(String[] args) throws IOException {
        Path test = Paths.get("C:\\projects\\test01");
        List<FileProperty> list = searcher(test);
        list.stream().forEach(System.out::println);
    }
}
