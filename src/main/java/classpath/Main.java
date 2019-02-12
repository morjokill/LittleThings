package classpath;

import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
        //Show all classpaths
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) classLoader).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }

        //Check class in classpath existence
//        Class.forName("javax.xml.crypto.XMLStructure");
    }
}
