package com.xmy.service;

import com.xmy.common.redis.RedisCache;
import com.xmy.entity.PageModel;
import com.xmy.entity.Student;
import com.xmy.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    // 自定义注解(aop 插入Redis缓存)
    @RedisCache(type = Student.class)
    public Student selectByPrimaryKey(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    // 自定义注解(aop 插入Redis缓存)
    @RedisCache(type = PageModel.class)
    public PageModel getPages(int pageIndex, int pageSize) {
        PageModel page = new PageModel();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setDataCount(10);
        List<Student> data = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            Student student = new Student();
            student.setId(i + pageIndex);
            student.setName("number_" + pageIndex + "_" + i);
            student.setAge(10 + i);
            student.setGrade(90 + i);
            data.add(student);
        }
        page.setData(data);
        return page;
    }
}
