import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

//
//class MatchingEngineBook{
//
//    public static void matches(PriorityBlockingQueue<Order> ask, PriorityBlockingQueue<Order> bid) {
//        try{
//            Thread.sleep(10000);
//            while(true){
//                System.out.println("Waiting for order");
//                // handle null case
//                // handle same id buy sell
//                if(bid.peek().price >= ask.peek().price){
//                    // selling price is ask price
//                    System.out.println("Order Executed");
//                    System.out.println(bid.peek().orderID + " " + ask.peek().orderID + " ");
//                    System.out.println(bid.peek().quantity + " " + ask.peek().quantity);
//                    System.out.println(bid.peek().price + " " + ask.peek().price);
//                    System.out.println(bid.peek().side + " " + ask.peek().side);
//                }
//            }
//        }
//        catch(Exception e){
//
//        }
//    }
//}

class MatchingEngineBook {
    public static void matches(PriorityBlockingQueue<Order> ask, PriorityBlockingQueue<Order> bid) {
        try {
            while (true) {
                // Check if both queues are not empty before proceeding
                Order askOrder = ask.peek();
                Order bidOrder = bid.peek();

                // Check if peeked orders are null
                if (askOrder == null || bidOrder == null) {
                    System.out.println("Null order encountered.");
                    Thread.sleep(1000);
                    continue;
                }

                // Matching logic
                if (bidOrder.price >= askOrder.price) {
                    System.out.println("Order Executed");
                    System.out.println("Bid Order: " + bidOrder.orderID + " - Ask Order: " + askOrder.orderID);
                    System.out.println("Bid Quantity: " + bidOrder.quantity + " - Ask Quantity: " + askOrder.quantity);
                    System.out.println("Bid Price: " + bidOrder.price + " - Ask Price: " + askOrder.price);
                    System.out.println("Bid Side: " + bidOrder.side + " - Ask Side: " + askOrder.side);
                    System.out.println("---------------------------------------------------------------------------------------------");
                    // Remove matched orders from the queue
                    ask.poll();
                    bid.poll();
                } else {
                    // If no match, sleep for a while before retrying
                    Thread.sleep(1000);
                    System.out.println(ask.size() + " " + bid.size());
//                    System.out.println("No matching orders available.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        //sell ascending
        PriorityBlockingQueue<Order> ask = new PriorityBlockingQueue<>(10, Comparator.comparingDouble(Order::getPrice));
        //buy descending
        PriorityBlockingQueue<Order> bid = new PriorityBlockingQueue<>(10, Comparator.comparingDouble(Order::getPrice).reversed());

        // Sample orders
        Order askOrder1 = new CreateOrder("A1", "AAPL", 150.0, 100, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
        Order askOrder2 = new CreateOrder("A2", "AAPL", 150.50, 200, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
        Order askOrder3 = new CreateOrder("A3", "AAPL", 160.0, 50, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);

        Order bidOrder1 = new CreateOrder("B1", "AAPL", 150.0, 150, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
        Order bidOrder2 = new CreateOrder("B2", "AAPL", 140.0, 100, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
        Order bidOrder3 = new CreateOrder("B3", "AAPL", 151.0, 200, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);

        // Add orders to queues
        ask.add(askOrder1);
        ask.add(askOrder2);
        ask.add(askOrder3);

        bid.add(bidOrder1);
        bid.add(bidOrder2);
        bid.add(bidOrder3);

        // Printing queues
        System.out.println("Ask Queue:");
        for (Order order : ask) {
            System.out.println(order);
        }
        System.out.println("\nBid Queue:");
        for (Order order : bid) {
            System.out.println(order);
        }
//        MatchingEngineBook.matches(ask, bid);
        new Thread(() -> MatchingEngineBook.matches(ask, bid)).start();
        new Thread(() -> ChangeQueue.addRecords(ask, bid)).start();
    }
}
class ChangeQueue{

    public static void addRecords(PriorityBlockingQueue<Order> ask, PriorityBlockingQueue<Order> bid){
        try {
            Thread.sleep(5000);

            // Creating new orders
            Order askOrder1 = new CreateOrder("A4", "AAPL", 150.0, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
            Order bidOrder1 = new CreateOrder("B4", "AAPL", 151.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);

            // Adding new orders to the queues
            ask.add(askOrder1);
            bid.add(bidOrder1);

            Thread.sleep(5000);

            // Creating more new orders
            Order askOrder2 = new CreateOrder("A5", "AAPL", 150.10, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
            Order bidOrder2 = new CreateOrder("B5", "AAPL", 151.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);

            // Adding more new orders to the queues
            ask.add(askOrder2);
            bid.add(bidOrder2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
//        Order askOrder3 = new CreateOrder("A6", "AAPL", 150.20, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder4 = new CreateOrder("A7", "AAPL", 150.30, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder5 = new CreateOrder("A8", "AAPL", 150.40, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder6 = new CreateOrder("A9", "AAPL", 150.50, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder7 = new CreateOrder("A10", "AAPL", 150.60, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder8 = new CreateOrder("A11", "AAPL", 150.70, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder9 = new CreateOrder("A12", "AAPL", 150.80, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//        Order askOrder10 = new CreateOrder("A13", "AAPL", 150.90, 10, OrderType.LIMIT_ORDER, Side.ASK, new Date(), "OPEN", false);
//
//
//        Thread.sleep(2000);
//        Order bidOrder3 = new CreateOrder("B6", "AAPL", 151.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//
//        Order bidOrder4 = new CreateOrder("B7", "AAPL", 141.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder5 = new CreateOrder("B8", "AAPL", 152.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder6 = new CreateOrder("B9", "AAPL", 142.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder7 = new CreateOrder("B10", "AAPL", 153.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder8 = new CreateOrder("B11", "AAPL", 143.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder9 = new CreateOrder("B12", "AAPL", 154.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);
//        Order bidOrder10 = new CreateOrder("B13", "AAPL", 144.0, 10, OrderType.LIMIT_ORDER, Side.BID, new Date(), "OPEN", false);

    }
}
