package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import net.sf.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {
    //用户个人的订单项目
    @Autowired
    OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value="/getAllOrder")
    public  List<Order> getAllOrder()
    {
        return orderService.getAllOrder();
    }
    @RequestMapping(value="/getUserOrder/{username}")
    public List<Order> getUserOrder(@PathVariable("username") String username){
        return orderService.getUserOrder(username);
    }
    @RequestMapping(value = "/deleteOrder")
    public int deleteOrderByID(@RequestBody Map<String,String> params)
    {
        Integer ID = Integer.valueOf(params.get("orderID"));
        System.out.println(ID);
        orderService.deleteOrderByID(ID);
        return 1;
    }
    @RequestMapping(value ="/makeOrder")
    public Msg makeOrder(@RequestBody String orderInfo) throws JSONException {
        System.out.println(orderInfo);
        System.out.println("username in make order = "+ SessionUtil.getUsername());
        String Order_UUID = UUID.randomUUID().toString().toUpperCase();
        JSONObject data = new JSONObject();
        data.put("uuid",Order_UUID);
        kafkaTemplate.send("orderQueue", Order_UUID,orderInfo);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG,data);
    }
    @RequestMapping(value ="/testUserName")
    public String test()
    {
        return  SessionUtil.getUsername();
    }
}
