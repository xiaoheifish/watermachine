package com.terabits.mapper;

import com.terabits.meta.po.InvitationPO;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface InvitationMapper {

    /**
     * 插入新的邀请信息
     * @param invitationPO
     * @return
     * @throws Exception
     */
    int insertInvitation(InvitationPO invitationPO) throws Exception;

    /**
     * 根据inviterPhone查询全部邀请信息
     * @param inviterPhone
     * @return
     * @throws Exception
     */
    List<InvitationPO> selectInvitation(String inviterPhone) throws  Exception;


}
