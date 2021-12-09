package ru.job4j.inputoutput;

import java.io.File;
/**
 * Class Dir решение задачи 4.0. File [#252491 #236463]
 * @author vmyaskovskiy
 * @version $Id$
 * @since 0.1
 */
public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " " + subfile.length());
        }
    }
}
