package ru.job4j.inputoutput.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
/**
 * 4.2. Поиск дубликатов [#315066 #237811]new
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toFile().isDirectory()) {
            FileProperty fileProp = new FileProperty(Files.size(file), file.toFile().getName());
            if (files.containsKey(fileProp)) {
                List<Path> res2 = files.get(fileProp);
                res2.add(file.toAbsolutePath());
                files.put(fileProp, res2);
            } else {
                List<Path> res = new ArrayList<>();
                res.add(file.toAbsolutePath());
                files.put(fileProp, res);
            }
        }
        return super.visitFile(file, attrs);
    }

    public  List<Path> getListPath() {
        List<Path> listPath = new ArrayList<>();
        for (List<Path> l: files.values()) {
            if (l.size() > 1) {
                listPath.addAll(l);
            }
        }
        return listPath;
    }
}
