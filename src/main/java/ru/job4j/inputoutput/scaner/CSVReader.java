package ru.job4j.inputoutput.scaner;

import ru.job4j.inputoutput.ArgsName;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> list = new ArrayList<>();
        List<ListString> listStrings = new ArrayList<>();
        String strOut = new String();

        try (Scanner scanner = new Scanner(Paths.get(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
        }
        if (list.size() != 0) {
            for (String s: list) {
                String[] r = s.split(argsName.get("delimiter"));
                ListString listString = new ListString();
                for (String r1: r) {
                    listString.setList(r1);
                }
                listStrings.add(listString);
            }
        }

        if (listStrings.size() != 0) {
            String f = argsName.get("filter");
            String[] r = f.split(",");
            int size = r.length;
            List<Integer> lInt = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < listStrings.get(0).getList().size(); j++) {
                    if (r[i].equals(listStrings.get(0).getList().get(j))) {
                        lInt.add(j);
                    }
                }
            }
            List<String> resOutNew = new ArrayList<>();
            for (ListString l: listStrings) {
                    String str = new String();
                    for (int i: lInt) {
                        if (str.isEmpty()) {
                            str = l.getList().get(i);
                        } else {
                            if (!(l.getList().size() == i)) {
                                str = str + ";" +  (l.getList().get(i));
                            }
                        }
                    }
                    resOutNew.add(str);
            }

            for (String s: resOutNew) {
                if (strOut.isEmpty()) {
                    strOut = s;
                } else {
                    strOut = strOut + System.lineSeparator() + s;
                }
            }
            strOut = strOut + System.lineSeparator();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            out.print(strOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
