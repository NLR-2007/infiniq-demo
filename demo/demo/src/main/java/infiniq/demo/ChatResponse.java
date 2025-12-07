package infiniq.demo;

public class ChatResponse {

    private String response;  // ✅ MUST be named "response"

    public ChatResponse(String response) {
        this.response = response;
    }

    public String getResponse() {   // ✅ REQUIRED for JSON
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
