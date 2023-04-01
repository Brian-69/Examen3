package examen3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Reader {
    public void execute(int id) {
        int startLine = id % 1000;
        int endLine = startLine + 50;
        String inputFilePath = "emails.csv";
        String outputFilePath = "172761.txt";
        int maxColumns = 3002;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             FileWriter fw = new FileWriter(outputFilePath)) {
            String line;
            int currentLine = 0;
            Map<Integer, String> firstRowWords = new HashMap<>();
            Map<Integer, Integer> columnSums = new HashMap<>();

            while ((line = br.readLine()) != null) {
                if (currentLine == 0) {
                    String[] words = line.split(",", -1);
                    for (int i = 1; i < words.length && i < maxColumns; i++) {
                        firstRowWords.put(i, words[i]);
                        columnSums.put(i, 0);
                    }
                } else if (currentLine >= startLine && currentLine < endLine) {
                    String[] words = line.split(",", -1);

                    for (int i = 1; i < words.length && i < maxColumns; i++) {
                        if (!words[i].isEmpty()) {
                            int value = Integer.parseInt(words[i]);
                            columnSums.put(i, columnSums.get(i) + value);
                        }
                    }
                }

                if (currentLine >= endLine) {
                    break;
                }

                currentLine++;
            }

            for (int i = 1; i < maxColumns; i++) {
                if (firstRowWords.containsKey(i)) {
                    fw.write(firstRowWords.get(i) + ": " + columnSums.get(i) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}