package infiniq.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ChatController {

    private final Infiniq bot;

    public ChatController() {
        KnowledgeBase kb = new KnowledgeBase();
        kb.loadDefaults();

        Searchable engine = new RecursiveSearchEngine(kb.getAll());
        ChatSession session = new ChatSession();

        bot = new Infiniq(kb, engine, session);
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest req) {
        String reply = bot.reply(req.getMessage());
        return new ChatResponse(reply);
    }

    @GetMapping("/")
    public String home() {
        return "Backend is running!";
    }
}
