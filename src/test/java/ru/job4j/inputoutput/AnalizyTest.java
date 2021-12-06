package ru.job4j.inputoutput;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class AnalizyTest тестирование задачи  2. Анализ доступности сервера.[#143743]
 * @author vmyaskovskiy
 * @version $Id$
 * @since 0.1
 */
public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Test
    public void whenTreeDiapasons() {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
        assertThat(analizy.getValue().get(0).key, is("10:57:01"));
        assertThat(analizy.getValue().get(0).value, is("10:59:01"));
        assertThat(analizy.getValue().get(1).key, is("11:01:02"));
        assertThat(analizy.getValue().get(1).value, is("11:02:02"));
    }

    @Test
    public void readFileUnavailableNewCsv() {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailableNew.csv");
        assertThat(analizy.elements.get(0).key, is("200"));
        assertThat(analizy.elements.get(0).value, is("10:56:01"));
    }

    @Test
    public void dropTarget() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailableNew.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        assertThat(analizy.elements.get(0).key, is("200"));
        assertThat(analizy.elements.get(0).value, is("10:56:01"));
    }
}
