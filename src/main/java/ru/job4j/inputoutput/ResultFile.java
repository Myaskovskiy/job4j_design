package ru.job4j.inputoutput;

import java.io.FileOutputStream;

public class ResultFile {

    public void mul() {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] res = new int[10][11];
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    res[i][j] = i * j;
                    Integer int3 = res[i][j];
                    out.write(int3.toString().getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResultFile resultFile = new ResultFile();
        resultFile.mul();
    }
}