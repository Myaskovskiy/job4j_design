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
    /**
     * 3.0. Тестирование IO [#173905 #236457]
     */
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
