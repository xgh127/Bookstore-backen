package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    @Autowired
    OrderDao orderDao;
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
        orderDao.deletOrderByID(ID);
        return 1;
    }
    @RequestMapping(value ="/makeOrder")
    public void makeOrder(@RequestBody String str) throws JSONException {
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
        Double tmpPrice = Double.parseDouble(obj.getString("totalPrice"))*100;
        Integer totalPrice = tmpPrice.intValue();
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
       orderService.makeOrder(gp,belonguser,postCode,phonenumber,address,receiverName,totalPrice);
    }
}
