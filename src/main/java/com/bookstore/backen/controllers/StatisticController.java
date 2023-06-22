package com.bookstore.backen.controllers;

import com.bookstore.backen.constant.constant;
import com.bookstore.backen.service.StatisticService;
import com.bookstore.backen.utils.Session.SessionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    /**
     * 工具函数：从参数中的str中解析出Date
     * @param str
     * @return
     * @throws ParseException
     */
    public Date getDate(String str) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.parse(str);
    }

    /**
     * 获取用户的消费信息，一段时间内消费的多少
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/statistic/userConsume")
    public JSONArray userConsumeData(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if (auth != null) {
            auth.get(constant.PRIVILEGE);
        }
        if(params.get("startDate")!=null && params.get("endDate")!=null){
            return statisticService.userConsumeStatistic(getDate(params.get("startDate")),getDate(params.get("endDate")));
        }
        else{
            return statisticService.userConsumeStatistic(getDate("1000-01-01 00:00:00"),getDate("9999-12-31 23:59:59"));
        }

    }

    /**
     * 获取书籍的销售信息，一段时间内销量的多少
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/statistic/bookSell")
    public JSONArray bookSell(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null || !Objects.equals(auth.get(constant.PRIVILEGE),0))
            return null;
        System.out.println("SellData");
        if(params.get("startDate")!=null && params.get("endDate")!=null){
           return statisticService.bookSellStatistic(getDate(params.get("startDate")),getDate(params.get("endDate")));
        }
        else{
            return statisticService.bookSellStatistic(getDate("1000-01-01 00:00:00"),getDate("9999-12-31 23:59:59"));
        }
    }

    /**
     * 统计用户总的消费金额
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/statistic/userStatistic/bookTotalPay")
    public JSONArray userBookTotalPay(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);
        if(params.get("startDate")!=null && params.get("endDate")!=null){

            return statisticService.userSelfStatistic_BookTotalPay(getDate(params.get("startDate")),getDate(params.get("endDate")),username);
        }

        else{
            return statisticService.userSelfStatistic_BookTotalPay(getDate("1000-01-01 00:00:00"),getDate("9999-12-31 23:59:59"),username);
        }

    }
    /**
     * 获取用户的购买书籍信息，用户在一段时间内买了多少本书
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/statistic/userStatistic/bookAllBuyNum")
    public JSONArray userBookAllBuyNum(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);

        if(params.get("startDate")!=null && params.get("endDate")!=null){
            return statisticService.userSelfStatistic_BookAllBuyNum(getDate(params.get("startDate")),getDate(params.get("endDate")),username);
        }
        else{

            return statisticService.userSelfStatistic_BookAllBuyNum(getDate("1000-01-01 00:00:00"),getDate("9999-12-31 23:59:59"),username);
        }
    }

    /**
     * 获取用户的购买书籍信息，在一段时间内每本书买了多少本
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/statistic/userStatistic/bookWithBuyNum")
    public JSONArray userbookWithBuyNum(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);

        if(params.get("startDate")!=null && params.get("endDate")!=null){
            return statisticService.userSelfStatistic_BookWithBuyNum(getDate(params.get("startDate")),getDate(params.get("endDate")),username);
        }

        else{
            return statisticService.userSelfStatistic_BookWithBuyNum(getDate("1000-01-01 00:00:00"),getDate("9999-12-31 23:59:59"),username);
        }
    }


}
