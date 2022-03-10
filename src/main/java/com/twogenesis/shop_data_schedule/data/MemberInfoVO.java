package com.twogenesis.shop_data_schedule.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoVO {
    private Integer mi_seq;
    private String mi_email;
    private Integer mi_gen;
    private Integer mi_status;
    
    @Override
    public String toString(){
        return mi_seq+","+mi_email+","+mi_gen+","+mi_status;
    }
}
