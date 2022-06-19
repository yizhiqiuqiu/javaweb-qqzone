package com.atguigu.qqzone.service;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyByTopicId(Integer topicId);
      //添加回复
      void addReply(Reply reply);
      //删除回复
      void delReply(Integer id);
      //删除日志下的所有回复
      void delReplyList(Topic topic);


}
