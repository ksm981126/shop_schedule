package com.twogenesis.shop_data_schedule.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.twogenesis.shop_data_schedule.data.MemberInfoVO;
import com.twogenesis.shop_data_schedule.data.MemberProdVO;
import com.twogenesis.shop_data_schedule.data.PageConnVO;
import com.twogenesis.shop_data_schedule.data.ProductInfoVO;
import com.twogenesis.shop_data_schedule.data.ReviewInfoVO;
import com.twogenesis.shop_data_schedule.data.ShoppingRecordVO;
import com.twogenesis.shop_data_schedule.mapper.HistoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class ScheduleComponent {
    @Value("${history.path}")
    String path;
    @Value("${history.conn.path}")
    String conn_path;
    @Value("${history.prod.path}")
    String prod_path;
    @Value("${history.review.path}")
    String review_path;
    @Value("${history.shopping_cart.path}")
    String shopping_cart_path;
    @Value("${history.shopping_cancel.path}")
    String shopping_cancel_path;
    @Value("${history.shopping_buy.path}")
    String shopping_buy_path;
    @Value("${history.member_info.path}")
    String member_info_path;
    @Value("${history.product_info.path}")
    String product_info_path;

    @Autowired HistoryMapper history_mapper;
    @Scheduled(cron="0 0 3 * * *")
    public void userConnDataSchdule()throws Exception{
        System.out.println("접속 기록 수집 스케줄 실행");
        Calendar search_dt= Calendar.getInstance(); // 2022-02-28 04:00:00
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt=search_dt.getTime();

        List<PageConnVO> list =history_mapper.selectPageConnHistory(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"connect"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(PageConnVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();

        String dest = conn_path+"connect"+c.getTimeInMillis()+".txt";

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
    }
    @Scheduled(cron= "0 0 3 * * *")
    public void memberProdDataSchdule()throws Exception{
        System.out.println("회원 접속 기록 스케줄 실행");
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR,0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        List<MemberProdVO> list =history_mapper.selectMemberProdHistory(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"member_prod"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(MemberProdVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
        
        String dest = prod_path+"member_prod"+c.getTimeInMillis()+".txt";
        File srcFile =new File(src);
        File dsefFile =new File(dest);

        srcFile.renameTo(dsefFile);
    }

    @Scheduled(cron= "0 10 3 * * *")
    public void reviewDataSchdule()throws Exception{
        System.out.println("리뷰 정보 수집 스케줄 실행");
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR,0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        List<ReviewInfoVO> list =history_mapper.selectReviewInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"review"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ReviewInfoVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
        
        String dest = review_path+"review"+c.getTimeInMillis()+".txt";
        File srcFile =new File(src);
        File dsefFile =new File(dest);

        srcFile.renameTo(dsefFile);
    }

    @Scheduled(cron= "0 20 3 * * *")
    public void shoppingDataSchdule()throws Exception{
        System.out.println("쇼핑 정보 수집 스케줄 실행");
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR,0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        List<ShoppingRecordVO> list =history_mapper.selectShoppingRecord(startDt, endDt , 0);
        Calendar c = Calendar.getInstance();
        String src = path+"shopping_cart"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ShoppingRecordVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
        
        String dest = shopping_cart_path+"shopping_cart"+c.getTimeInMillis()+".txt";
        File srcFile =new File(src);
        File dsefFile =new File(dest);

        srcFile.renameTo(dsefFile);

        list =history_mapper.selectShoppingRecord(startDt, endDt , 1);
        //Calendar c = Calendar.getInstance();
        src = path+"shopping_buy"+c.getTimeInMillis()+".txt";
        writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ShoppingRecordVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
        
        dest = shopping_buy_path+"shopping_buy"+c.getTimeInMillis()+".txt";
        srcFile =new File(src);
        dsefFile =new File(dest);

        srcFile.renameTo(dsefFile);

        list =history_mapper.selectShoppingRecord(startDt, endDt , 2);
        //Calendar c = Calendar.getInstance();
        src = path+"shopping_cancel"+c.getTimeInMillis()+".txt";
        writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ShoppingRecordVO data : list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();
        
        dest = shopping_cancel_path+"shopping_cancel"+c.getTimeInMillis()+".txt";
        srcFile =new File(src);
        dsefFile =new File(dest);

        srcFile.renameTo(dsefFile);

    }
    @Scheduled(cron="0 30 3 * * *")
    public void memberDataSchedule() throws Exception{
        System.out.println("회원 정보 수집 스케줄 실행");
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR,0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        List<MemberInfoVO> list =history_mapper.selectMemberInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"member"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(MemberInfoVO data : list){
            writer.write(data.toString());
            writer.newLine();
            
        }
        writer.close();

        String dest = member_info_path+"member"+c.getTimeInMillis()+".txt";

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
    }
    @Scheduled(cron="0 35 3 * * *")
    public void productDataSchedule() throws Exception{
        System.out.println("제품 정보 수집 스케줄 실행");
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR,0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        List<ProductInfoVO> list =history_mapper.selectProductInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"product"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ProductInfoVO data : list){
            writer.write(data.toString());
            writer.newLine();
            
        }
        writer.close();

        String dest = product_info_path+"product"+c.getTimeInMillis()+".txt";

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
    }
}
