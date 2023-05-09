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
     * 从参数中的str中解析出Date
     * @param str
     * @return
     * @throws ParseException
     */
    public Date getDate(String str) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.parse(str);
    }

    /**
     * 获取用户的消费信息
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
     * 获取书籍的销售信息
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
    @RequestMapping("/statistic/userStatistic/bookTotalPay")
    public JSONArray userBookTotalPay(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);
        if(params.get("startDate")!=null && params.get("endDate")!=null){
            String startstr = params.get("startDate");
            String endstr = params.get("endDate");

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookTotalPay(datastart,dataend,username);
        }

        else{
            String startstr = "1000-01-01 00:00:00";
            String endstr = "9999-12-31 23:59:59";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookTotalPay(datastart,dataend,username);
        }

    }
    @RequestMapping("/statistic/userStatistic/bookAllBuyNum")
    public JSONArray userBookAllBuyNum(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);

        if(params.get("startDate")!=null && params.get("endDate")!=null){
            String startstr = params.get("startDate");
            String endstr = params.get("endDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookAllBuyNum(datastart,dataend,username);
        }

        else{
            String startstr = "1000-01-01 00:00:00";
            String endstr = "9999-12-31 23:59:59";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookAllBuyNum(datastart,dataend,username);
        }
    }
    @RequestMapping("/statistic/userStatistic/bookWithBuyNum")
    public JSONArray userbookWithBuyNum(@RequestBody Map<String, String> params) throws ParseException {
        JSONObject auth = SessionUtil.getAuth();
        if(auth == null)
            return null;
        String username = (String) auth.get(constant.USERNAME);

        if(params.get("startDate")!=null && params.get("endDate")!=null){
            String startstr = params.get("startDate");
            String endstr = params.get("endDate");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookWithBuyNum(datastart,dataend,username);
        }

        else{
            String startstr = "1000-01-01 00:00:00";
            String endstr = "9999-12-31 23:59:59";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date datastart = sdf1.parse(startstr);
            Date dataend = sdf1.parse(endstr);

            return statisticService.userSelfStatistic_BookWithBuyNum(datastart,dataend,username);
        }
    }


}
