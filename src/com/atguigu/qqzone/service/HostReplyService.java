package com.atguigu.qqzone.service;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.qqzone.pojo.HostReply;

public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer id);
    void delHostReply(Integer id);


}
