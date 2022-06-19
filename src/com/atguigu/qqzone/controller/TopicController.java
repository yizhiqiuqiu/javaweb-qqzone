package com.atguigu.qqzone.controller;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {

    private TopicService topicService;
//把那个replyService和topicService关联后到TopicServiceImpl层，然后这里在调用TopicService，就两个都能调用到了

    public String topicDetail(Integer id , HttpSession session){
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic",topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId){

        topicService.delTopic(topicId);
         //这里因为不想刷新其他的页面，所以重新return一个范围
        //重新刷新日志列表，把删掉的信息刷新，更新最新的日志列表
        return "redirect:topic.do?operate=getTopicList";
    }


    public String getTopicList(HttpSession session){
        //从session中获取用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询当前用户关联的所有日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表（因为之前session中关联的friend的topicList和此刻数据库中不一致）
        userBasic.setTopicList(topicList);
        //重新覆盖一下friend中的信息
        session.setAttribute("friend",userBasic);

        return "frames/main";






    }

}
