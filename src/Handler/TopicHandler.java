package Handler;

import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {

    private final Map<String, WorkerThread> workerThreadHashmap;
    private final Topic topic;

    public TopicHandler(Topic topic) {
        workerThreadHashmap = new HashMap<>();
        this.topic = topic;
    }

    public void publish() {
        for(int index = 0; index < topic.getSubscribers().size(); index++) {

            TopicSubscriber topicSubscriber = topic.getSubscribers().get(index);
            final String subscriberId = topicSubscriber.getSubscriber().getSubscriberID();
            if(!workerThreadHashmap.containsKey(subscriberId)) {
                final WorkerThread workerThread = new WorkerThread(topicSubscriber);
                workerThreadHashmap.put(subscriberId, workerThread);
                new Thread(workerThread).start();
            }
            WorkerThread workerThread = workerThreadHashmap.get(subscriberId);
            workerThread.wakeUpIfNeeded();

        }
    }
}
