import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input;
            while ((input = reader.readLine()) != "end") {
                int lineNumber = Integer.parseInt(input);

                System.out.println(getLine(lineNumber));
            }
        }catch (Exception e) {}
    }

    public static final class Cache {
        static HashMap<Integer, String> cache = new HashMap<>();

        public static boolean checCachekLine(int lineNumber) {
            return cache.containsKey(lineNumber);
        }

        public static void addLineToCache(int lineNumber, String string) {
            cache.put(lineNumber, string);
        }

        public static String getStringFromCache(int lineNumber) {
            for (Map.Entry<Integer, String> pair : cache.entrySet()) {
                if (pair.getKey() == lineNumber)
                    return pair.getValue();
            }
            return "";
        }
    }

    public static String getLine(int lineNumber) {
        if (Cache.checCachekLine(lineNumber)) {
            return Cache.getStringFromCache(lineNumber);
        }

        String temp = readLineFromFile("text.txt", lineNumber);
        Cache.addLineToCache(lineNumber, temp);
        return temp;
    }

    public static String readLineFromFile(String fileName, int lineNumber) {
        int num = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));

            for(String line:lines){
                if (num == lineNumber)
                    return line;

                num++;
            }
        }catch (Exception e) {}

        return "Line does not exist";
    }

}