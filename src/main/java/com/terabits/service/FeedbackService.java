package com.terabits.service;

import com.terabits.meta.po.FeedbackPO;

/**
 * Created by Administrator on 2017/8/31.
 */
public interface FeedbackService {
    //插入新的反馈数据
    public int insertFeedback(FeedbackPO feedbackPO)throws Exception;
}
