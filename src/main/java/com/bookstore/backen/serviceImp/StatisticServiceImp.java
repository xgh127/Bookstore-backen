package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.Dao.OrderItemDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.service.StatisticService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticServiceImp implements StatisticService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    BookDao bookDao;
    @Override
    public JSONArray userConsumeStatistic(Date starttime, Date endtime) {
        return orderDao.userConsumeStatistic(starttime,endtime);
    }

    @Override
    public JSONArray bookSellStatistic(Date starttime, Date endtime) {
         //获取书籍销售信息
        JSONArray SellData = orderItemDao.bookSellStatistic(starttime,endtime);
        System.out.println("SellData");
        System.out.println(SellData);
         // 将书籍的其他信息加入返回数据，书名和书籍封面
        for(int i=0;i<SellData.size();i++){
            JSONArray RowData = SellData.getJSONArray(i);
            System.out.println("RowData");
            System.out.println(RowData);

            String bookID = RowData.getString(0);
            Book tmpBook = bookDao.getOneBookByID(Integer.parseInt(bookID));

            if(tmpBook!=null){
                RowData.add(2,tmpBook.getName());
                RowData.add(3,tmpBook.getImage());
            }
            SellData.set(i,RowData);
        }

        return SellData;
    }

    @Override
    public JSONArray userSelfStatistic_BookWithBuyNum(Date starttime, Date endtime, String username) {
        JSONArray BookWithBuyNumData = orderItemDao.userSelfStatistic_BookWithBuyNum(starttime, endtime,username);

        for(int i=0;i<BookWithBuyNumData.size();i++){
            JSONArray RowData = BookWithBuyNumData.getJSONArray(i);

            String bookID = RowData.getString(0);
            Book tmpBook = bookDao.getOneBookByID(Integer.parseInt(bookID));

            if(tmpBook!=null){
                RowData.add(2,tmpBook.getName());
                RowData.add(3,tmpBook.getImage());
            }
            BookWithBuyNumData.set(i,RowData);
        }

        return BookWithBuyNumData;
    }

    @Override
    public JSONArray userSelfStatistic_BookAllBuyNum(Date starttime, Date endtime, String username) {
        return orderItemDao.userSelfStatistic_BookAllBuyNum(starttime, endtime,username);
    }

    @Override
    public JSONArray userSelfStatistic_BookTotalPay(Date starttime, Date endtime, String username) {
        return orderDao.userSelfStatistic_BookTotalPay(starttime, endtime,username);
    }
}
