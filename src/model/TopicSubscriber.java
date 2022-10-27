package model;

public class TopicSubscriber{
    private final Topic topic;
    private final Subscriber subscriber;
    private int offset;

    public TopicSubscriber(Topic topic, Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
        offset = 0;
    }

    public Topic getTopic() {
        return topic;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public int getOffset() {
        return offset;
    }

    public void increaseOffset() {
        this.offset++;
    }
}
