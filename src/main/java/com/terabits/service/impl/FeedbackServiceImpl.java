package com.terabits.service.impl;

import com.terabits.mapper.FeedbackMapper;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/31.
 */
@Service("feedBackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired(required = false)
    private FeedbackMapper feedbackMapper;

    public int insertFeedback(FeedbackPO feedbackPO)throws Exception{
        return feedbackMapper.insertFeedback(feedbackPO);
    }
}
