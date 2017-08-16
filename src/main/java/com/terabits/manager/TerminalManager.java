package com.terabits.manager;

import com.terabits.meta.model.TerminalModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface TerminalManager {

    /**
     * 新增一条terminal数据，包含插入时间和过期时间，过期时间双保险
     * @param terminalModel
     */

    public void createNewTerminal(TerminalModel terminalModel);

    /**
     * 获取插入此条terminal的时间
     * @param terminalId
     */
    public String getTerminalTime(long terminalId) throws Exception;

    /**
     * 清除terminal
     * @param terminalId
     */
    public void deleteTerminal(long terminalId);

    /**
     * 获取转换格式后的剩余生存时间
     */
    public String getLeftTime(long terminalTd);
}
