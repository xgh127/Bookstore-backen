package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    //用户个人的订单项目
    @Autowired
    OrderService orderService;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

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
//        String Order_UUID = UUID.randomUUID().toString().toUpperCase();
//        JSONObject data = new JSONObject();
//        data.put("uuid",Order_UUID);
//        kafkaTemplate.send("orderQueue", Order_UUID,orderInfo);
        org.json.JSONObject obj =null;
        try {
            obj = new org.json.JSONObject(orderInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String belonguser = null;
        if (obj != null) {
            belonguser = obj.getString("username");
        }
        String phonenumber = null;
        if (obj != null) {
            phonenumber = obj.getString("phoneNumber");
        }
        double tmpPrice = Double.parseDouble(obj.getString("totalPrice"))*100;
        Integer totalPrice = (int) tmpPrice;
        String postCode = obj.getString("postcode");
        String address = obj.getString("address");
        String receiverName = obj.getString("receiverName");
        JSONArray CartOrderIDGroup = obj.optJSONArray("CartorderIDGroup");
        int [] gp = new int[CartOrderIDGroup.length()];
        for(int i = 0; i < CartOrderIDGroup.length(); ++ i)
        {
            gp[i] =  Integer.parseInt(CartOrderIDGroup.get(i).toString());
            System.out.println( CartOrderIDGroup.get(i).toString());
        }
        Integer orderid = orderService.makeOrder(gp,belonguser,postCode,phonenumber,address,receiverName,totalPrice);
        //return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG,data);
        if (orderid > 0) return MsgUtil.makeMsg(MsgUtil.SUCCESS, String.valueOf(orderid));
        else return MsgUtil.makeMsg(MsgUtil.ERROR,"创建订单错误");
    }
    @RequestMapping(value ="/testUserName")
    public String test()
    {
        return  SessionUtil.getUsername();
    }
}
