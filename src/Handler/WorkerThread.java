package Handler;

import model.Subscriber;
import model.Topic;
import model.TopicSubscriber;

public class WorkerThread implements Runnable{

    private final TopicSubscriber topicSubscriber;

    public WorkerThread(TopicSubscriber topicSubscriber) {
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run(){
        synchronized (topicSubscriber) {
            do {
                final Topic topic = this.topicSubscriber.getTopic();
                final Subscriber subscriber = this.topicSubscriber.getSubscriber();
                int currentTopicLength = topic.getMessagesLength();
                while (topicSubscriber.getOffset() < currentTopicLength) {
                    subscriber.consume(topic.getMessage(topicSubscriber.getOffset()));
                    topicSubscriber.increaseOffset();
                }
                try {
                    topicSubscriber.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while(true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}