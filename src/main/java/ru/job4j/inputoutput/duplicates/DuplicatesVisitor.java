package ru.job4j.inputoutput.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
/**
 * 4.2. Поиск дубликатов [#315066 #237811]
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    List<FileProperty> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toFile().isDirectory()) {
            this.list.add(new FileProperty(Files.size(file), file.getFileName().toString()));
        }
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getList() {
        Set<FileProperty> set = new HashSet<>();
        List<FileProperty> res = list.stream().filter(e -> !set.add(e)).collect(Collectors.toList());
        for (FileProperty fs: set) {
            for (FileProperty fl : res) {
                if (fs.equals(fl)) {
                    res.add(fs);
                    break;
                }
            }
        }
        return res;
    }
}
