package com.service;

import com.entity.ReplyEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ReplyService {
    /**
     * 教师回复信息，即添加reply至replyEntity中
     *
     * @param reply reply对象
     * @return 成功则返回新的reply对象，失败返回null
     */
    @NotNull ReplyEntity addReply(@NotNull ReplyEntity reply);

    /**
     * 撤回回复信息，即删除消息
     *
     * @param reply 有效的含id的回复消息实体
     */
    void recallReply(@NotNull ReplyEntity reply);

    /**
     * 查找指定replyMission下的所有回复信息
     *
     * @return reply的对象集合
     */
    @NotNull List<ReplyEntity> findReplys();

    /**
     * 根据replyid查找reply
     *
     * @param id replyid
     * @return 实体
     */
    @NotNull Optional<ReplyEntity> findReplyByReplyId(int id);

}
