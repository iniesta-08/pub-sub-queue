import Handler.TopicHandler;
import model.Subscriber;
import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class Queue {
    private final Map<Topic, TopicHandler> topicHandler;

    public Queue() {
        this.topicHandler = new HashMap<>();
    }

    public Topic createTopic(String topicID) {
        Topic topic = new Topic(topicID);
        topicHandler.put(topic, new TopicHandler(topic));
        return topic;
    }

    public void addMessageToTopic(Topic topic, String message) {
        topic.addMessage(message);
        startPublishing(topic);
    }

    private void startPublishing(Topic topic) {
        new Thread(() -> topicHandler.get(topic).publish()).start();
    }

    public void subscribeToTopic(Topic topic, Subscriber subscriber) {
        topic.addSubscriber(new TopicSubscriber(topic, subscriber));
        startPublishing(topic);
    }
}
