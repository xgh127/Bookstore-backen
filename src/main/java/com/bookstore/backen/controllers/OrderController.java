package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value="/getAllOrder")
    public  List<Order> getAllOrder()
    {
        return orderService.getAllOrder();
    }
    @RequestMapping(value="/getUserOrder")
    public List<Order> getUserOrder(@RequestBody Map<String,String> params){
        String username = params.get("username");
        System.out.println("enter order");
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
    public Msg makeOrder(@RequestBody String str) throws JSONException {
        JSONObject  obj =null;
        try {
             obj = new JSONObject(str);
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
       if(orderid > 0) {
           return MsgUtil.makeMsg(MsgCode.SUCCESS, String.valueOf(orderid));
       }
       else{
           return MsgUtil.makeMsg(-1,"库存不足");
       }
    }
}
