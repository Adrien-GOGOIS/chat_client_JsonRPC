package org.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServerLauncher {

    public static void main(String[] args) throws Exception {
        // Création du serveur
        ChatServerImpl chatServer = new ChatServerImpl();
        ExecutorService threadPool = Executors.newCachedThreadPool();

        int port = 3000;
        // Création du Socket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("The chat server is running on port " + port);
            threadPool.submit(() -> {
                while (true) {
                    // wait for clients to connect
                    Socket socket = serverSocket.accept();
                    // create a JSON-RPC connection for the accepted socket
                    SocketLauncher<ChatClient> launcher = new SocketLauncher<>(chatServer, ChatClient.class, socket);
                    // connect a remote chat client proxy to the chat server
                    Runnable removeClient = chatServer.addClient(launcher.getRemoteProxy());
                    /*
                     * Start listening for incoming messages.
                     * When the JSON-RPC connection is closed
                     * disconnect the remote client from the chat server.
                     */
                    ((CompletableFuture<Void>)launcher.startListening()).thenRun(removeClient);
                }
            });
            System.out.println("Enter any character to stop");
            System.in.read();
            System.exit(0);
        }
    }

}