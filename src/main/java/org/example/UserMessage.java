package org.example;

public class UserMessage {
    private String user;
    private String content;

    public UserMessage(String userName, String content) {
        this.user = userName;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
