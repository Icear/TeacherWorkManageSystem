package com.service.impl;

import com.dao.ReplyEntityDao;
import com.entity.ReplyEntity;
import com.service.ReplyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
    private final ReplyEntityDao replyEntityDao;

    public ReplyServiceImpl(ReplyEntityDao replyEntityDao) {
        this.replyEntityDao = replyEntityDao;
    }

    @Override
    public @NotNull ReplyEntity addReply(@NotNull ReplyEntity reply) {
        return replyEntityDao.add(reply);
    }

    @Override
    public void recallReply(@NotNull ReplyEntity reply) {
        replyEntityDao.delete(reply);
    }

    @Override
    public @NotNull List<ReplyEntity> findReplys() {
        return replyEntityDao.list();
    }
}
