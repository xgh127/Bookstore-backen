package com.bookstore.backen.service;

import net.sf.json.JSONArray;

import java.util.Date;

public interface StatisticService {
    /**
     * 获取一段时间内用户消费数据
     * @param starttime
     * @param endtime
     * @return
     */
    JSONArray userConsumeStatistic(Date starttime, Date endtime);

    /**
     * 获取一段时间内书籍的销售数据
     * @param starttime
     * @param endtime
     * @return
     */
    JSONArray bookSellStatistic(Date starttime, Date endtime);

    JSONArray userSelfStatistic_BookWithBuyNum(Date starttime, Date endtime, String username);

    JSONArray userSelfStatistic_BookAllBuyNum(Date starttime, Date endtime, String username);

    JSONArray userSelfStatistic_BookTotalPay(Date starttime, Date endtime, String username);

}
