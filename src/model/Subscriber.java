package model;

public class Subscriber {
    private String subscriberID;

    public Subscriber(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void consume(String message) {
        System.out.println("Subscriber " + subscriberID + " consuming message " + message);
    }
}
