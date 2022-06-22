package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment;


@JsonSegment("server")
public interface ChatServer {

    /**
     *
     * Requête server/fetchMessage envoyées par le client pour récupérer messages déjà postée
     */

    @JsonRequest
    CompletableFuture<List<UserMessage>> fetchMessages();

    /**
     *
     * server/postMessage : envoyées par le client pour poster un nouveau message
     */

    @JsonNotification
    void postMessage(UserMessage message);
}