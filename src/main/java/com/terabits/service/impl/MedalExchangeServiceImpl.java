package com.terabits.service.impl;

import com.terabits.mapper.MedalExchangeMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.MedalExchangePO;
import com.terabits.service.MedalExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
@Service("medalExchangeService")
public class MedalExchangeServiceImpl implements MedalExchangeService {
    @Autowired(required = false)
    private MedalExchangeMapper medalExchangeMapper;

    private static Logger logger = LoggerFactory.getLogger(MedalExchangeServiceImpl.class);

    public int insertMedalExchange(MedalExchangePO medalExchangePO) throws Exception{
        return medalExchangeMapper.insertMedalExchange(medalExchangePO);
    }

    public List<MedalExchangePO> selectMedalExchange(String openId, TimeSpanBO timeSpanBO) throws Exception{
        return medalExchangeMapper.selectMedalExchange(openId, timeSpanBO);
    }

    public int getMonthlyExchange(String openId){
        List<MedalExchangePO> medalExchangePOList = new ArrayList<MedalExchangePO>();
        TimeSpanBO timeSpanBO = getCurrentMonth();
        try{
            medalExchangePOList = selectMedalExchange(openId, timeSpanBO);
        }catch (Exception e){
            logger.error("MedalExchangeService.getMonthlyExchange error in MedalExchangeServiceImpl" + openId);
            return 500;
        }
        int medal = 0;
        for (MedalExchangePO medalExchangePO : medalExchangePOList){
            medal += Integer.parseInt(medalExchangePO.getExchange());
        }
        return medal;
    }

    private static TimeSpanBO getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);//
        ca.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = ca.getTime();
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = ca.getTime();
        String firstDay = sdf.format(firstDate) + " 00:00:00";
        String lastDay = sdf.format(lastDate) + " 23:59:59";
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        timeSpanBO.setBeginTime(firstDay);
        timeSpanBO.setEndTime(lastDay);
        return timeSpanBO;
    }


}
