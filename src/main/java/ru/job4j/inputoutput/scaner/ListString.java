package ru.job4j.inputoutput.scaner;

import java.util.ArrayList;
import java.util.List;

public class ListString {

    List<String> list = new ArrayList<>();

    public void setList(String s) {
        this.list.add(s);
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ListString{"
                + "list="
                + list + '}';
    }
}
