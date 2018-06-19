package com.service;

import com.entity.ReplyEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ReplyService {
    /**
     * 教师回复信息，即添加reply至replyEntity中
     *
     * @param reply reply对象
     * @return 成功则返回新的reply对象，失败返回null
     */
    @NotNull Optional<ReplyEntity> addReply(@NotNull ReplyEntity reply);

    /**
     * 撤回回复信息，即删除消息
     *
     * @param reply 有效的含id的回复消息实体
     * @return 是否删除成功
     */
    boolean recallReply(@NotNull ReplyEntity reply);

    // TODO: 2018/6/19  可以做成类似tim或wechat的"撤回了一条消息"么

}
