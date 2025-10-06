import { Client } from "@stomp/stompjs";
import  SockJS from "sockjs-client";

let client: Client;

export function connect(token: string, onMessage: (msg: any) => void) {
  client = new Client({
    webSocketFactory: () => new SockJS("http://localhost:8080/ws-chat"),
    connectHeaders: {
      Authorization: `Bearer ${token}`,
    },
    onConnect: () => {
      console.log("Conectado ao chat!");
      client.subscribe("/topic/public", (message) => {
        onMessage(JSON.parse(message.body));
      });
    },
  });

  client.activate();
}

export function sendMessage(msg: { sender: string; content: string }) {
  client.publish({ destination: "/app/sendMessage", body: JSON.stringify(msg) });
}

export function disconnect() {
  client.deactivate();
}
