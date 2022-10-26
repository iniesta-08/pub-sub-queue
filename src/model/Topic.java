package model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicID;
    private List<String> messages;

    public Topic(String topicID) {
        this.topicID = topicID;
        this.messages = new ArrayList<>();
    }

    private void addMessage(String message) {
        this.messages.add(message);
    }
}
