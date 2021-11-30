package ru.job4j.generic;

import java.util.*;

public class GenericUsage {

    static class Pipl {
        String name;

        Pipl(String name) {
            this.name = name;
        }
    }

    static class Progr extends Pipl {
        public Progr(String name) {
            super(name);
        }
    }


    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<Pipl> pipls = Arrays.asList(new Pipl("333"));
        list.add("11");
        list.add("22");

        String st = (String) list.get(1);
        GenericUsage genericUsage = new GenericUsage();
        genericUsage.printRsl(list);

        list.stream().forEach(System.out::println);
        pipls.stream().forEach(System.out::println);
    }
}
