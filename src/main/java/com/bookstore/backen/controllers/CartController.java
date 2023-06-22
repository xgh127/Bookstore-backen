package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.service.CartOrderService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CartController
{
    @Autowired
    CartOrderService cartOrderService;
    @RequestMapping(value = "/getOrders")
    public List<CartOrder> getCartOrdersByUserName(@RequestBody Map<String, String> userInfo)
    {
        String username = userInfo.get("username");
        return cartOrderService.getCartOrdersByUserName(username);
    }
    @RequestMapping("/addCart")
    public Msg addToCart(@RequestBody Map<String, String> bookInfo)
    {
        System.out.println("enter addCartOrder");
        /*解析参数，丢给服务层*/
        int bookid = Integer.parseInt(bookInfo.get("id"));
        String username = bookInfo.get("username");
        System.out.println("bookid="+bookid);
        int buyNum = Integer.parseInt(bookInfo.get("buyNum"));
       CartOrder cartOrder = cartOrderService.addOneCartItem(username,bookid,buyNum);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS,String.valueOf(cartOrder.getIdCartOrder()));
    }
    /*
    * This function is used to change buyNum
    * */
    @RequestMapping("/changeBuyNum")
    public void changeBuyNum(@RequestBody Map<String, String> params )
    {

        Integer tmpID = Integer.valueOf(params.get("cartOrderID"));
        Integer tmpBuyNum = Integer.valueOf(params.get("buyNum"));

        cartOrderService.changeBuyNum(tmpID,tmpBuyNum);
    }
    @RequestMapping("/changeStatus")
    public  void changeStatus(@RequestBody Map<String,String> params )
    {
        System.out.println("enter changeStatus");
        Integer tmpID = Integer.valueOf(params.get("cartOrderID"));
        Integer newStatus = Integer.valueOf(params.get("submit_status"));
        cartOrderService.changeStatus(tmpID,newStatus);
    }
    @RequestMapping("/removeCartItem")
    public void removeCartItem(@RequestBody Map<String, String> params )
    {
        System.out.println("enter buyNum");
        Integer tmpID = Integer.valueOf(params.get("cartOrderID"));
        cartOrderService.removeItemByid(tmpID);
    }
    @RequestMapping("/getCartOrderByID")
    public CartOrder getCartOrderByID(@RequestBody Map<String, String> param)
    {
        Integer cartOrderID = Integer.valueOf(param.get("id"));
        return cartOrderService.getCartOrderByID(cartOrderID);
    }
    @RequestMapping("/checkBookExist")
    public Msg CheckExist(@RequestBody Map<String,String> param)
    {
        Integer bookid= Integer.valueOf(param.get("bookid"));
        String username = param.get("username");
        CartOrder cartOrder=cartOrderService.checkBookExist(username,bookid);
        String feedBack;
        if(cartOrder != null)
        {
            feedBack= String.valueOf(cartOrder.getIdCartOrder());
        }
        else
        {
            feedBack = "-1";
            System.out.println("null!!!!!!!!!!!!");
        }
        return  MsgUtil.makeMsg(MsgCode.SUCCESS,feedBack );
    }

}
