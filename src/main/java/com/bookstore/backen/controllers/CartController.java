package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.service.CartOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CartController
{
    @Autowired
    CartDao cartDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CartOrderService cartOrderService;
    @RequestMapping(value = "/getOrders")
    public List<CartOrder> getCartOrdersByUserName(@RequestBody Map<String, String> userInfo)
    {
        String username = userInfo.get("username");
        return cartDao.getCartOrdersByUserName(username);
    }
    @RequestMapping("/addCart")
    public String addToCart(@RequestBody Map<String, String> bookInfo)
    {
        System.out.println("enter addCartOrder");
        /*解析参数，丢给服务层*/
        Integer bookid = Integer.valueOf(bookInfo.get("id"));
        String username = bookInfo.get("username");
        Integer buyNum = Integer.valueOf(bookInfo.get("buyNum"));
        cartOrderService.addOneCartItem(username,bookid,buyNum);

        return "success add";
    }
    /*
    * This function is used to change buyNum
    * */
    @RequestMapping("/changeBuyNum")
    public void changeBuyNum(@RequestBody Map<String, String> params )
    {
        System.out.println("enter buyNum");
        Integer tmpID = Integer.valueOf(params.get("cartOrderID"));
        Integer tmpBuyNum = Integer.valueOf(params.get("buyNum"));

        cartDao.changeBuyNum(tmpID,tmpBuyNum);
    }

    @RequestMapping("/removeCartItem")
    public void removeCartItem(@RequestBody Map<String, String> params )
    {
        System.out.println("enter buyNum");
        Integer tmpID = Integer.valueOf(params.get("cartOrderID"));
        cartDao.removeItemByid(tmpID);
    }


}
