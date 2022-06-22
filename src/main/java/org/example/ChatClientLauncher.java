package org.example;

import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class ChatClientLauncher {

    public static void main(String[] args) throws Exception {
        // create the chat client
        ChatClientImpl chatClient = new ChatClientImpl();

        String host = "localhost";
        Integer port = Integer.valueOf("3000");
        // connect to the server
        try (Socket socket = new Socket(host, port)) {
            // open a JSON-RPC connection for the opened socket
            SocketLauncher<ChatServer> launcher = new SocketLauncher<>(chatClient, ChatServer.class, socket);

            ((CompletableFuture<Void>)launcher.startListening()).thenRun(() -> System.exit(0));
            // start the chat session with a remote chat server proxy
            chatClient.start(launcher.getRemoteProxy());
        }
    }

}
