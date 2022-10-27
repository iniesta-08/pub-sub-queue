package Handler;

import model.Subscriber;
import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {

    private final Map<String, workerThread> workerThreadHashmap;
    private final Topic topic;

    public TopicHandler(Topic topic) {
        workerThreadHashmap = new HashMap<>();
        this.topic = topic;
    }

    public void publish() {
        for(TopicSubscriber subscriber : topic.getSubscribers()) {
            final String subscriberId = subscriber.getSubscriber().getSubscriberID();
            if(!workerThreadHashmap.containsKey(subscriberId)) {
                final workerThread workerThread = new workerThread(subscriber);
                workerThreadHashmap.put(subscriberId, workerThread);
                new Thread(workerThread).start();
            }
            workerThread workerThread = workerThreadHashmap.get(subscriberId);
            workerThread.wakeUpIfNeeded();
        }
    }

    public static class workerThread implements Runnable{

        private final TopicSubscriber topicSubscriber;

        public workerThread(TopicSubscriber topicSubscriber) {
            this.topicSubscriber = topicSubscriber;
        }

        @Override
        public void run() {
            synchronized (topicSubscriber) {
                final Topic topic = this.topicSubscriber.getTopic();
                final Subscriber subscriber = this.topicSubscriber.getSubscriber();
                int currentTopicLength = topic.getMessagesLength();
                while(topicSubscriber.getOffset() < currentTopicLength) {
                    subscriber.consume(topic.getMessage(topicSubscriber.getOffset()));
                    topicSubscriber.increaseOffset();
                }
    //            System.out.println(this.offset);
            }
        }

        synchronized public void wakeUpIfNeeded() {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
    }
}
