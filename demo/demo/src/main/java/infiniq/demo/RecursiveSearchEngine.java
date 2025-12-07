package infiniq.demo;

import java.util.Map;

public class RecursiveSearchEngine implements Searchable {

    private final Map<String, String> map;

    public RecursiveSearchEngine(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String search(String query) {

        // Normalize input
        query = query.toLowerCase().trim();

        // Fuzzy match: if user question contains ANY part of key
        for (String key : map.keySet()) {

            String normalizedKey = key.toLowerCase().trim();

            // If question contains important words from the key
            if (query.contains(normalizedKey) || normalizedKey.contains(query)) {
                return map.get(key);
            }

            // Second layer: match without punctuation
            if (removePunctuation(query).equals(removePunctuation(normalizedKey))) {
                return map.get(key);
            }
        }

        return null;
    }

    private String removePunctuation(String s) {
        return s.replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }
}
