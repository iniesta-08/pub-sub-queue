import model.Subscriber;
import model.Topic;

public class Main {
    public static void main(String[] args) {


        Queue distributedQueue = new Queue();

        Topic topic_one = distributedQueue.createTopic("Topic 1");
        Topic topic_two = distributedQueue.createTopic("Topic 2");

        Subscriber subscriber_one = new Subscriber("Subscriber 1");
        Subscriber subscriber_two = new Subscriber("Subscriber 2");

        distributedQueue.subscribeToTopic(topic_one, subscriber_one);
        distributedQueue.subscribeToTopic(topic_two, subscriber_two);
        distributedQueue.subscribeToTopic(topic_two, subscriber_one);

        distributedQueue.addMessageToTopic(topic_one, "Message 1");
        distributedQueue.addMessageToTopic(topic_one, "Message 2");
        distributedQueue.addMessageToTopic(topic_one, "Message 3");
        distributedQueue.addMessageToTopic(topic_one, "Message 4");
        distributedQueue.addMessageToTopic(topic_two, "Message 5");

        distributedQueue.subscribeToTopic(topic_two, subscriber_two);

        distributedQueue.addMessageToTopic(topic_two, "Message 6");
        distributedQueue.addMessageToTopic(topic_one, "Message 7");

        Subscriber subscriber_three = new Subscriber("Subscriber 3");

        distributedQueue.subscribeToTopic(topic_one, subscriber_three);

//        Scanner myObj = new Scanner(System.in);
//        while(true) {
//            String topic_name = myObj.nextLine();
//            String message = myObj.nextLine();
//            Topic topic = distributedQueue.createTopic(topic_name);
//            distributedQueue.addMessageToTopic(topic_one, message);
//        }
    }
}