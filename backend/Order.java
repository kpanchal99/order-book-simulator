package backend;

import java.util.Comparator;
import java.util.Date;

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
  Date orderDateTime;
  String orderStatus;
  Boolean isCompleted;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getOrderDateTime() {
    return orderDateTime;
  }

  public void setOrderDateTime(Date orderDateTime) {
    this.orderDateTime = orderDateTime;
  }


  //getter and setters
}
class CreateOrder extends Order{
  public CreateOrder (String orderID, String ticker, double price, int quantity, OrderType orderType, Side side, Date orderDateTime, String orderStatus, Boolean isCompleted) {
    this.orderID = orderID;
    this.ticker = ticker;
    this.price = price;
    this.quantity = quantity;
    this.orderType = orderType;
    this.side = side;
    this.orderDateTime = orderDateTime;
    this.orderStatus = orderStatus;
    this.isCompleted = isCompleted;
  }

  @Override
  public String toString() {
    return "CreateOrder{" +
            "orderID='" + orderID + '\'' +
            ", ticker='" + ticker + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            ", orderType=" + orderType +
            ", side=" + side +
            ", orderDateTime=" + orderDateTime +
            ", orderStatus='" + orderStatus + '\'' +
            ", isCompleted=" + isCompleted +
            '}';
  }
}
class ModifyOrder extends Order{
  // check if order exist
  // partial fill order quantity checks
  ModifyOrder(OrderType orderType, String ticker, double price, int quantity, Side side, String orderStatus, Boolean isCompleted) {
    this.orderType = orderType;
    this.ticker = ticker;
    this.price = price;
    this.quantity = quantity;
    this.side = side;
    this.orderDateTime = new Date();
    this.orderStatus = orderStatus;
    this.isCompleted = isCompleted;

  }
  //getter and setters constructor
}

class DeleteOrder extends Order{
  // order exist should exist
  // partial fill order quantity checks
  DeleteOrder(OrderType orderType, String ticker, double price, int quantity, Side side, String orderStatus, Boolean isCompleted) {
    this.orderType = orderType;
    this.ticker = ticker;
    this.price = price;
    this.quantity = quantity;
    this.side = side;
    this.orderDateTime = new Date();
    this.orderStatus = orderStatus;
    this.isCompleted = isCompleted;

  }
  //getter and setters constructor
}
class OrderBook {

  //need to use comparable/comparator
  PriorityBlockingQueue<Order> ask = new PriorityBlockingQueue<>(100, Comparator.comparing(Order::getPrice));
  PriorityBlockingQueue<Order> bid = new PriorityBlockingQueue<>(100, Comparator.comparingDouble(Order::getPrice).reversed());

  void createOrder() {}

  void updateOrder() {}

  void cancelOrder() {}
}

// matching engine
