package com.terabits.service.impl;

import com.terabits.mapper.MedalExchangeMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.MedalExchangePO;
import com.terabits.service.ConsumeSignService;
import com.terabits.service.MedalExchangeService;
import com.terabits.utils.ScoreRuleUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
@Service("medalExchangeService")
public class MedalExchangeServiceImpl implements MedalExchangeService {
    @Autowired(required = false)
    private MedalExchangeMapper medalExchangeMapper;
    @Autowired
    private ConsumeSignService consumeSignService;

    private static Logger logger = LoggerFactory.getLogger(MedalExchangeServiceImpl.class);

    public int insertMedalExchange(MedalExchangePO medalExchangePO) throws Exception{
        return medalExchangeMapper.insertMedalExchange(medalExchangePO);
    }

    public List<MedalExchangePO> selectMedalExchange(String openId, TimeSpanBO timeSpanBO) throws Exception{
        return medalExchangeMapper.selectMedalExchange(openId, timeSpanBO);
    }

    //提交兑换勋章请求，需要在medalexchange表中记录此笔兑换请求，同时修改consumesign表中的exchangehistory列
    public int exchangeRequest(String openId, int number){
        MedalExchangePO medalExchangePO = new MedalExchangePO();
        medalExchangePO.setOpenId(openId);
        medalExchangePO.setExchange(String.valueOf(number));
        List<ScoreRuleUtil.MedalType> medalTypes = convertExchangeRequest(number);
        medalExchangePO.setMoney(computePresent(medalTypes));
        try{
            insertMedalExchange(medalExchangePO);
        }catch (Exception e){
            logger.error("MedalExchangeService.insertMedalExchangePO error in MedalExchangeServiceImpl" + medalExchangePO);
            return 500;
        }
        int result = consumeSignService.pullExchangeRequest(openId, medalTypes);
        return result;
    }

    //获取某用户本月的勋章兑换历史
    public JSONArray getMonthlyExchange(String openId){
        List<MedalExchangePO> medalExchangePOList = new ArrayList<MedalExchangePO>();
        TimeSpanBO timeSpanBO = getCurrentMonth();
        try{
            medalExchangePOList = selectMedalExchange(openId, timeSpanBO);
        }catch (Exception e){
            logger.error("MedalExchangeService.getMonthlyExchange error in MedalExchangeServiceImpl" + openId);
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(medalExchangePOList);
        return jsonArray;
    }

    //计算本月的时间段
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

    //计算勋章对应的赠送金额
    private double computePresent(List<ScoreRuleUtil.MedalType> medalTypes){
        double present = 0.0;
        Map<ScoreRuleUtil.MedalType, Double> map = new HashMap<ScoreRuleUtil.MedalType, Double>();
        map.put(ScoreRuleUtil.MedalType.GOLD, 0.9);
        map.put(ScoreRuleUtil.MedalType.SILVER, 0.5);
        map.put(ScoreRuleUtil.MedalType.BRONZE, 0.2);
        for (ScoreRuleUtil.MedalType medalType : medalTypes){
            present += map.get(medalType);
        }
        return present;
    }

    //根据前端发来的要兑换勋章的数量，转换成MedalType的list
    private static List<ScoreRuleUtil.MedalType> convertExchangeRequest(int number){
        List<ScoreRuleUtil.MedalType> medalTypes = new ArrayList<ScoreRuleUtil.MedalType>();
        int gold, silver, bronze = 0;
        gold = number / 100;
        for(int i = 0; i < gold; i++){
            medalTypes.add(ScoreRuleUtil.MedalType.GOLD);
        }
        number = number - gold * 100;
        silver = number / 10;
        for(int i = 0; i < silver; i++){
            medalTypes.add(ScoreRuleUtil.MedalType.SILVER);
        }
        bronze = number - silver * 10;
        for(int i = 0; i < bronze; i++){
            medalTypes.add(ScoreRuleUtil.MedalType.BRONZE);
        }
        return medalTypes;
    }



}
