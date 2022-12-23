package com.solvd.apacheUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ReaderMain {
    public static void wordCounter(String inputPath, String outputPath) throws IOException {
        HashMap<String, Integer> wordOccurrences = new HashMap<>();
        String[] words = StringUtils.lowerCase(FileUtils.readFileToString(new File(inputPath), StandardCharsets.UTF_8)).split("\\W+");
        for (String word : words) {
            if (wordOccurrences.containsKey(word)) {
                wordOccurrences.put(word, wordOccurrences.get(word) + 1);
            } else {
                wordOccurrences.put(word, 1);
            }
        }
        FileWriter answer = new FileWriter(outputPath);
        for (String word : wordOccurrences.keySet()) {
            answer.write(word + " - " + wordOccurrences.get(word) + "\n");
        }
        answer.close();
    }

    public static void main(String[] args) throws IOException {
        wordCounter("src/main/resources/article.txt", "src/main/resources/answer.txt");
    }
}
