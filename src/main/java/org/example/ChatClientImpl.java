package org.example;

import java.util.Scanner;

public class ChatClientImpl implements ChatClient {

    private final Scanner scanner = new Scanner(System.in);

    /**
     *
     * 1 --> Demande à l'utilisateur son nom
     * 2 --> Récupère les messages existants et les affiche
     * 3 --> Demande à l'utilisateur son message
     * 4 --> Affiche le nouveau message et recommence l'étape 3
     */
    public void start(ChatServer server) throws Exception {
        System.out.print("Enter your name: ");
        String user = scanner.nextLine();
        System.out.println("Hello " + user);
        server.fetchMessages().get().forEach(this::didPostMessage);
        while (true) {
            String content = scanner.nextLine();
            server.postMessage(new UserMessage(user, content));
        }
    }

    /**
     *
     * Affiche le message envoyé
     */
    @Override
    public void didPostMessage(UserMessage message) {
        System.out.println(message.getUser() + ": " + message.getContent());
    }

}