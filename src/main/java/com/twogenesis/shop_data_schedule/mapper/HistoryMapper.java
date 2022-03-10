package com.twogenesis.shop_data_schedule.mapper;

import java.util.Date;
import java.util.List;

import com.twogenesis.shop_data_schedule.data.MemberInfoVO;
import com.twogenesis.shop_data_schedule.data.MemberProdVO;
import com.twogenesis.shop_data_schedule.data.PageConnVO;
import com.twogenesis.shop_data_schedule.data.ProductInfoVO;
import com.twogenesis.shop_data_schedule.data.ReviewInfoVO;
import com.twogenesis.shop_data_schedule.data.ShoppingRecordVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    List<PageConnVO> selectPageConnHistory(Date start_dt, Date end_dt);
    List<MemberProdVO> selectMemberProdHistory(Date start_dt, Date end_dt);
    List<ReviewInfoVO> selectReviewInfo(Date start_dt, Date end_dt);
    List<ShoppingRecordVO> selectShoppingRecord(Date start_dt, Date end_dt, Integer status);

    List<MemberInfoVO> selectMemberInfo(Date start_dt, Date end_dt);
    List<ProductInfoVO> selectProductInfo(Date start_dt, Date end_dt);
}
