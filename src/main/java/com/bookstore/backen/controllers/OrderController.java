package com.bookstore.backen.controllers;

import com.bookstore.backen.constant.constant;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        net.sf.json.JSONObject auth = SessionUtil.getAuth();
        if (auth == null ){
            System.out.println("auth is null");
            return null;
        }
        org.json.JSONObject obj =null;
        try {
            obj = new org.json.JSONObject(orderInfo);
            if (!Objects.equals(auth.get(constant.USERNAME),obj.getString("username"))){
                System.out.println(auth.get(constant.USERNAME) + " is not equal " + obj.getString("username"));
                System.out.println("username not match");
                return null;
            }
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
        System.out.println("下订单完成，订单的id是："+orderid);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, orderid.toString());
    }
}
