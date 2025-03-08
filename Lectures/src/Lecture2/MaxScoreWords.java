package Lecture2;

import java.util.*;

public class MaxScoreWords {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterCount = new int[26];

        // Count the frequency of available letters
        for (char c : letters) {
            letterCount[c - 'a']++;
        }

        return backtrack(words, letterCount, score, 0);
    }

    private int backtrack(String[] words, int[] letterCount, int[] score, int index) {
        if (index == words.length) return 0;

        // Skip the current word
        int maxScore = backtrack(words, letterCount, score, index + 1);

        // Try using the current word
        int wordScore = 0;
        boolean canUse = true;
        int[] tempCount = Arrays.copyOf(letterCount, letterCount.length);

        for (char c : words[index].toCharArray()) {
            if (tempCount[c - 'a'] == 0) {
                canUse = false;
                break;
            }
            tempCount[c - 'a']--;
            wordScore += score[c - 'a'];
        }

        if (canUse) {
            maxScore = Math.max(maxScore, wordScore + backtrack(words, tempCount, score, index + 1));
        }

        return maxScore;
    }

    public static void main(String[] args) {
        MaxScoreWords solver = new MaxScoreWords();
        String[] words = {"dog", "cat", "dad", "good"};
        char[] letters = {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};

        System.out.println(solver.maxScoreWords(words, letters, score)); // Output: 23
    }
}

































//        Count the available letters in letterCount[].
//        Use backtracking to explore:
//        Skipping a word.
//        Including a word if possible and proceeding further.
//        Check if the word can be formed with available letters.
//        If valid, reduce letter count and compute the score.
//        Recurse for next words and maximize the score.