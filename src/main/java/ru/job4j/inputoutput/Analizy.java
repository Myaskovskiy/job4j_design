package ru.job4j.inputoutput;
import java.io.*;
import java.util.*;
public class Analizy {
      private String line;
      List<Element> elements = new ArrayList<>();
    String startVal = "null";
    String finishVal = "null";
    // объект для хранения строчки с разбитием на ключ - значение
    class Element {
        String key;
        String value;
        public Element(String key, String value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return key + " " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Element element = (Element) o;
            return Objects.equals(key, element.key)
                    && Objects.equals(value, element.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
     public void makeList(String line) {
        String[] str = line.split(" ");
        Element element = new Element(str[0], str[1]);
        this.elements.add(element);
     }

    public List<Element> getValue() {
        Iterator<Element> it = this.elements.iterator();
        List<Element> res = new ArrayList<>();
        Element el;

        while (it.hasNext()) {
            Element f = it.next();
            String start = f.key;
            String startV = f.value;

            if ((start.equals("500") || start.equals("400"))) {
                if (this.startVal.equals("null")) {
                    this.startVal = startV;
                }
            } else if (!this.startVal.equals("null")) {
                this.finishVal = startV;
                el = new Element(this.startVal, this.finishVal);
                res.add(el);
                this.startVal = "null";
                this.finishVal = "null";
            }
        }
        return res;
    }

    public void unavailable(String path, String target) {
       try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
           while ((this.line = reader.readLine()) != null) {
               makeList(line);
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
           out.println(getValue());
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
    }
}
