package infiniq.demo;

import java.util.ArrayList;
import java.util.List;

public class ChatSession {

    private final List<String> history = new ArrayList<>();

    public void add(String h) {
        history.add(h);
    }

    public List<String> getHistory() {
        return history;
    }
}
