package com.xujiahui.edumain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.edumain.entity.XTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 先生 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
public interface XTeacherService extends IService<XTeacher> {

    List<XTeacher> getAllTeacher();

    void deleteTeacherById(String teacherId);

    Map<String, Object> getTeacherFrontList(Page<XTeacher> pageTeacher);
}
