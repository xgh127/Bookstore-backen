//package com.bookstore.backen.Listener;
//
//import com.bookstore.backen.WebSocket.WebSocketServer;
//import com.bookstore.backen.service.OrderService;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MakeOrderListener {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private WebSocketServer webSocketServer;
//
//    @KafkaListener(topics = "orderQueue",groupId = "group_topic_order")
//    public void makeOrderListener(ConsumerRecord<String,String> record) throws JSONException {
//        System.out.println("enter makeOrderListener"+record.value());
////        JSONObject obj = record.value();
//        String str = record.value();
//        JSONObject obj =null;
//        try {
//            obj = new JSONObject(str);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String belonguser = null;
//        if (obj != null) {
//            belonguser = obj.getString("username");
//        }
//        String phonenumber = null;
//        if (obj != null) {
//            phonenumber = obj.getString("phoneNumber");
//        }
//        double tmpPrice = Double.parseDouble(obj.getString("totalPrice"))*100;
//        Integer totalPrice = (int) tmpPrice;
//        String postCode = obj.getString("postcode");
//        String address = obj.getString("address");
//        String receiverName = obj.getString("receiverName");
//        JSONArray CartOrderIDGroup = obj.optJSONArray("CartorderIDGroup");
//        int [] gp = new int[CartOrderIDGroup.length()];
//        for(int i = 0; i < CartOrderIDGroup.length(); ++ i)
//        {
//            gp[i] =  Integer.parseInt(CartOrderIDGroup.get(i).toString());
//            System.out.println( CartOrderIDGroup.get(i).toString());
//        }
//        Integer orderid = orderService.makeOrder(gp,belonguser,postCode,phonenumber,address,receiverName,totalPrice);
//        kafkaTemplate.send("OrderFinished",record.key(), String.valueOf(orderid));
//    }
//
//    @KafkaListener(topics = "OrderFinished",groupId = "group_topic_order")
//     public void orderFinishedListener(ConsumerRecord<String,String> record) throws InterruptedException {
//        String value = record.key();
//        System.out.println("OrderFinishedListener 输出 = "+value+"value = "+record.value());
//        webSocketServer.sendMessageToUser(value, "您的订单号为："+record.value()+" 具体信息可前往<我的订单>查看！");
//    }
//}
