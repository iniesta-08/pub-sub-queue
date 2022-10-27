package model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicID;
    private List<String> messages;
    private List<TopicSubscriber> subscribers;

    public Topic(String topicID) {
        this.topicID = topicID;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public List<TopicSubscriber> getSubscribers() {
        return this.subscribers;
    }

    public int getMessagesLength() {
        return messages.size();
    }

    public String getMessage(int index) {
        return messages.get(index);
    }
}
