package com.xujiahui.edumain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujiahui.edumain.entity.XTeacher;
import com.xujiahui.edumain.mapper.XTeacherMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xujiahui.edumain.service.XTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 先生 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-11-17
 */
@Service
public class XTeacherServiceImpl extends ServiceImpl<XTeacherMapper, XTeacher> implements XTeacherService {
    /**
     * @return teacher List
     */
    @Override
    public List<XTeacher> getAllTeacher() {
        List<XTeacher> xTeachers = baseMapper.selectList(null);
        return xTeachers;
    }

    /**
     * @param teacherId
     * @return null
     */
    @Override
    public void deleteTeacherById(String teacherId) {
        baseMapper.deleteById(teacherId);
    }

    /**
     * front get  teacherInfo
     * @param pageTeacher
     * @return
     */
    @Override
    public Map<String, Object> getTeacherFrontList(Page<XTeacher> pageTeacher) {
        QueryWrapper<XTeacher> queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(pageTeacher,queryWrapper);
        //将分页查询获取的数据放到map中，
        List<XTeacher> records = pageTeacher.getRecords();//每页数据的list
        long current = pageTeacher.getCurrent(); //当前页
        long total = pageTeacher.getTotal();//总记录树
        long size = pageTeacher.getSize();//每页记录
        long pages = pageTeacher.getPages();//总页数

        boolean p = pageTeacher.hasPrevious(); //是否有上页
        boolean b = pageTeacher.hasNext(); //是否有下一页
        Map<String,Object> map=new HashMap<>();




        map.put("records",records);
        map.put("current",current);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("p",p);
        map.put("b",b);

        return map;
    }
}
