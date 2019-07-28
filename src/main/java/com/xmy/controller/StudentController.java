package com.xmy.controller;

import com.xmy.entity.PageModel;
import com.xmy.entity.Student;
import com.xmy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/getStudentByKey/{id}")
    public Student getStudentByKey(@PathVariable int id) {
        return studentService.selectByPrimaryKey(id);
    }

    @RequestMapping("/getStudents")
    public PageModel getStudents(Integer pageIndex, Integer pageSize) {
        return studentService.getPages(pageIndex, pageSize);
    }

    @RequestMapping("/getStudentlist")
    public PageModel getStudentsDefault() {
        return studentService.getPages(1, 10);
    }

    @RequestMapping("/gettest")
    public PageModel getStudentsDefault(Integer count) {
        for (int i = 0; i <= count; i++) {
            studentService.getPages(1, 10);
        }

        return studentService.getPages(1, 10);
    }

    @RequestMapping("/settest")
    public PageModel setTest(Integer index, Integer count) {
        for (int i = index; i <= count; i++) {
            studentService.getPages(i, 10);
        }

        return studentService.getPages(1, 10);
    }
}
