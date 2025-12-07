// Load chat on startup
window.onload = function () {
    loadChatHistory();
};

function sendMessage() {
    let msg = document.getElementById("message").value;
    if (msg.trim() === "") return;

    // Show user message
    addMessage(msg, "user");
    saveMessage("user", msg);

    // Send JSON to backend
    fetch("http://localhost:8080/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: msg })
    })
    .then(response => response.json())
    .then(data => {
        addMessage(data.response, "bot");
        saveMessage("bot", data.response);
    });

    document.getElementById("message").value = "";
}

function addMessage(text, type) {
    let chatBox = document.getElementById("chat-box");

    let messageDiv = document.createElement("div");
    messageDiv.classList.add("message", type);
    messageDiv.innerText = text;

    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

/* ------------------------
    LOCAL STORAGE
------------------------- */

// Save message
function saveMessage(type, text) {
    let messages = JSON.parse(localStorage.getItem("chatHistory")) || [];
    messages.push({ type: type, text: text });
    localStorage.setItem("chatHistory", JSON.stringify(messages));
}

// Load saved messages
function loadChatHistory() {
    let messages = JSON.parse(localStorage.getItem("chatHistory")) || [];

    messages.forEach(msg => {
        addMessage(msg.text, msg.type);
    });
}