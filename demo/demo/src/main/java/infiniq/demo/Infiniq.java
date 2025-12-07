package infiniq.demo;

public class Infiniq {

    private final KnowledgeBase kb;
    private final Searchable engine;
    private final ChatSession session;

    public Infiniq(KnowledgeBase kb, Searchable engine, ChatSession session) {
        this.kb = kb;
        this.engine = engine;
        this.session = session;
    }

    public String reply(String msg) {

        // Convert user input to lowercase for matching
        String userMsg = msg.toLowerCase();

        session.add("User: " + userMsg);

        // 1. Exact match ignoring case
        for (String key : kb.getAll().keySet()) {
            if (key.toLowerCase().equals(userMsg)) {
                return kb.getAll().get(key);
            }
        }

        // 2. Fuzzy search using SearchEngine
        String ans = engine.search(userMsg);
        if (ans != null) {
            return ans;
        }

        // 3. Default fallback
        return "Sorry, I don't understand.";
    }
}
