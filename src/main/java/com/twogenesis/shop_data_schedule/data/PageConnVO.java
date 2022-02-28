package com.twogenesis.shop_data_schedule.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageConnVO {
    private Integer pch_seq;
    private Integer pch_mi_seq;
    private String pch_url;
    private Double pch_interval;
    private Date pch_reg_dt;

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return this.pch_mi_seq+","+pch_url+","+pch_interval+","+formatter.format(this.pch_reg_dt);
    }
}
