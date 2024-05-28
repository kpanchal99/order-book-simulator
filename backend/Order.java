import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

enum Side {
  ASK, // sell
  BID, // buy
}

enum OrderType {
  LIMIT_ORDER,
  MARKET_ORDER,
}

class Order{
  String orderID;
  String ticker;
  double price;
  int quantity;
  OrderType orderType;
  Side side;

  //getter and setters
}
class CreateOrder{
  String orderID;
  String ticker;
  double price;
  int quantity;
  OrderType orderType;
  Side side;

  //getter and setters
}
class ModifyOrder{
  // check if order exist
  // partial fill order quantity checks
  String orderID;
  String ticker;
  double price;
  int quantity;
  OrderType orderType;
  Side side;
  //getter and setters constructor
}

class DeleteOrder{
  // order exist should exist
  // partial fill order quantity checks
  String orderID;
  String ticker;
  double price;
  int quantity;
  OrderType orderType;
  Side side;
  //getter and setters constructor
}
class OrderBook {

  //need to use comparable/comparator
  PriorityBlockingQueue<Order> = new PriorityBlockingQueue<>();
  void createOrder() {}

  void updateOrder() {}

  void cancelOrder() {}
}

// matching engine
