package com.service.impl;

import com.entity.TeacherEntity;
import com.exception.IdentityNotFoundException;
import com.service.AuthorizeService;
import com.service.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthorizeServiceImpl implements AuthorizeService {

    private final TeacherService teacherService;

    @Autowired
    public AuthorizeServiceImpl(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public @NotNull String applyToken(@NotNull TeacherEntity teacher) {
        //检查teacher有效性，如果有效生成token，如果无效
        Optional<TeacherEntity> teacherEntityOptional = teacherService.findTeacher(teacher.getId());

        teacherEntityOptional.orElseThrow(IdentityNotFoundException::new);
    }

    @Override
    public Optional<TeacherEntity> getTokenIdentity(@NotNull String token) {
        return Optional.empty();
    }

    @Override
    public void destroyToken(@NotNull String token) {

    }

    /**
     * 生成Token
     * @return token
     */
    private @NotNull String generateToken(){
        return "123";
    }
}
