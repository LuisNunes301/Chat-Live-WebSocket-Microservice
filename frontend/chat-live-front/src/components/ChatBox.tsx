"use client";

import { useEffect, useState } from "react";
import { connect, sendMessage, disconnect } from "@/api/websocket";

export default function ChatPage() {
  const [messages, setMessages] = useState<any[]>([]);
  const [input, setInput] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token") || "";
    connect(token, (msg) => setMessages((prev) => [...prev, msg]));

    return () => disconnect();
  }, []);

  const handleSend = () => {
    const username = localStorage.getItem("username") || "Anon";
    sendMessage({ sender: username, content: input });
    setInput("");
  };

  return (
    <main className="p-4">
      <h1 className="text-xl font-bold">💬 Chat</h1>
      <div className="border p-2 h-64 overflow-y-auto my-4">
        {messages.map((m, i) => (
          <div key={i}>
            <b>{m.sender}:</b> {m.content}
          </div>
        ))}
      </div>
      <input
        className="border p-2 mr-2"
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />
      <button className="bg-blue-500 text-white px-4 py-2" onClick={handleSend}>
        Enviar
      </button>
    </main>
  );
}
