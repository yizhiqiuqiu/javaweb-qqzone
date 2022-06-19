package com.atguigu.qqzone.controller;

/*
 * @author 邱传泽
 * @version 1.0
 */

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService;

    public  String addReply(String content, Integer topicId , HttpSession session){
       //获取author，从session中获取
        UserBasic author=(UserBasic) session.getAttribute("userBasic");
        //获取reply的内容
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        replyService.addReply(reply);

        //因为是增加删除，直接跳转到topicDetail是没有更新的，所以我们要用重定向
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }


    public  String  delReply(Integer replyId,Integer topicId){


        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;

    }




}
