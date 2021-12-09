package ru.job4j.inputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        Path test = Paths.get(args[0]);
        search(test, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}
