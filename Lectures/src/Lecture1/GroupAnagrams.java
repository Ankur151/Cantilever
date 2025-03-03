package Lecture1;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        // HashMap to store grouped anagrams
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            // Sort the word to get the key
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            // Add to the map
            map.putIfAbsent(sortedWord, new ArrayList<>());
            map.get(sortedWord).add(word);
        }

        // Convert the HashMap values into a list of lists
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
    }
}
