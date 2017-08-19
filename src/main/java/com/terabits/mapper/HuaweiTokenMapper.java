package com.terabits.mapper;

import com.terabits.meta.po.HuaweiTokenPO;

/**
 * Created by Administrator on 2017/8/18.
 */
public interface HuaweiTokenMapper {

    /**插入华为Token
     *
     * @param huaweiTokenPO
     * @return
     * @throws Exception
     */

    public int insertToken(HuaweiTokenPO huaweiTokenPO) throws Exception;

    /**更新华为Token
     *
     * @param huaweiToken
     * @return
     * @throws Exception
     */
    public int updateToken(HuaweiTokenPO huaweiToken) throws Exception;

    /**取出华为Token
     *
     * @return
     * @throws Exception
     */
    public HuaweiTokenPO selectToken() throws Exception;

}
