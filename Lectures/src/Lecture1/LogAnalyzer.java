package Lecture1;

import java.util.*;

public class LogAnalyzer {
    // TreeMap stores timestamps as keys and endpoint URLs as values
    private TreeMap<Long, String> logMap;

    public LogAnalyzer() {
        logMap = new TreeMap<>();
    }

    // Log a request with a timestamp and endpoint URL
    public void logRequest(long timestamp, String endpoint) {
        logMap.put(timestamp, endpoint);
    }

    // Get total requests in the given time range
    public int getRequestCount(long startTime, long endTime) {
        return logMap.subMap(startTime, endTime + 1).size();
    }

    // Get the most accessed endpoint in a given time range
    public String getMostAccessedEndpoint(long startTime, long endTime) {
        Map<Long, String> subLogs = logMap.subMap(startTime, endTime + 1);
        HashMap<String, Integer> countMap = new HashMap<>();

        // Count occurrences of each endpoint
        for (String endpoint : subLogs.values()) {
            countMap.put(endpoint, countMap.getOrDefault(endpoint, 0) + 1);
        }

        // Find the most accessed endpoint
        String mostAccessed = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostAccessed = entry.getKey();
            }
        }

        return mostAccessed;
    }

    public static void main(String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer();

        // Log requests
        analyzer.logRequest(1000, "/home");
        analyzer.logRequest(1500, "/home");
        analyzer.logRequest(2000, "/profile");
        analyzer.logRequest(2500, "/home");
        analyzer.logRequest(3000, "/profile");

        // Queries
        System.out.println("Total requests between 1000-2500: " + analyzer.getRequestCount(1000, 2500));
        // Output: 4

        System.out.println("Most accessed endpoint between 1000-3000: " + analyzer.getMostAccessedEndpoint(1000, 3000));
        // Output: "/home"
    }
}
