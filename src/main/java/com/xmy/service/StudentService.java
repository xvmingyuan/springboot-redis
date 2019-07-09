package com.xmy.service;

import com.xmy.entity.PageModel;
import com.xmy.entity.Student;

public interface StudentService {
    Student selectByPrimaryKey(int id);

    PageModel getPages(int pageIndex, int pageSize);
}
