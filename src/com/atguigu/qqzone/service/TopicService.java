package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) ;

    Topic getTopicById(Integer id );

    //根据id获取这个日志信息，包含它的作者信息
    public Topic getTopic(Integer id);

     void delTopic(Integer id);


}
