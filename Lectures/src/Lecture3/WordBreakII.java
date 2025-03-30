package Lecture3;

import java.util.*;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private List<String> backtrack(String s, Set<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        List<String> res = new ArrayList<>();
        if (wordDict.contains(s)) res.add(s); // If whole string is a word

        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if (wordDict.contains(left)) {
                List<String> rightParts = backtrack(s.substring(i), wordDict, memo);
                for (String right : rightParts) res.add(left + " " + right);
            }
        }
        memo.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreakII wb = new WordBreakII();
        System.out.println(wb.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
