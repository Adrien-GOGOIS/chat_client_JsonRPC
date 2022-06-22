package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServerImpl implements ChatServer {

    private final List<UserMessage> messages = new CopyOnWriteArrayList<>();
    private final List<ChatClient> clients = new CopyOnWriteArrayList<>();

    /**
     *
     * Retourne les messages existants
     */

    @Override
    public CompletableFuture<List<UserMessage>> fetchMessages() {
        return CompletableFuture.completedFuture(messages);
    }

    /**
     *
     * Sauvegarde le message envoyé par le client et diffuse à tous les clients
     */

    @Override
    public void postMessage(UserMessage message) {
        messages.add(message);
        for (ChatClient client : clients) {
            client.didPostMessage(message);
        }
    }

    /**
     *
     * Connection du client --> Retourn un runnable qui s'éxecutera pour déconnecter le client
     */

    public Runnable addClient(ChatClient client) {
        this.clients.add(client);
        return () -> this.clients.remove(client);
    }

}