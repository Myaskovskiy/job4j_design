package ru.job4j.inputoutput;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class AnalizyTest тестирование задачи  2. Анализ доступности сервера.[#143743]
 * @author vmyaskovskiy
 * @version $Id$
 * @since 0.1
 */
public class AnalizyTest {
    // прочитаем файл лога в котором три диапазона и запишем результат в файл анализа
    // сверим данные из внутренней коллекции, которая потом пишется в исходящий файл
    @Test
    public void whenTreeDiapasons() {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
        assertThat(analizy.getValue().get(0).key, is("10:57:01"));
        assertThat(analizy.getValue().get(0).value, is("10:59:01"));
        assertThat(analizy.getValue().get(1).key, is("11:01:02"));
        assertThat(analizy.getValue().get(1).value, is("11:02:02"));
    }
    // прочитаем полученный файл анализа unavailable.csv
    // данные сверим из внутренней колекции в которую записался наш файл
    @Test
    public void readFileUnavailableNewCsv() {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailableNew.csv");
        assertThat(analizy.elements.get(0).key, is("200"));
        assertThat(analizy.elements.get(0).value, is("10:56:01"));
    }
}
