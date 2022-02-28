package com.twogenesis.shop_data_schedule.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.twogenesis.shop_data_schedule.data.PageConnVO;
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
}
