package com.terabits.manager;


import com.terabits.meta.model.TerminalModel;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface RedisTemplateTest {
    public void createTerminal(TerminalModel terminalModel);
    public String getTerminalTime(String terminalId);
    public String getLeftTime(String terminalId);
}
