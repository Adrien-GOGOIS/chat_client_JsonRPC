package org.example;

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification;
import org.eclipse.lsp4j.jsonrpc.services.JsonSegment;

@JsonSegment("client")
public interface ChatClient {

    /**
     *
     * client/didPostMessage : envoyé par serveur at tous les clients en réponse du server/postMessage
     */

    @JsonNotification
    void didPostMessage(UserMessage message);
}
