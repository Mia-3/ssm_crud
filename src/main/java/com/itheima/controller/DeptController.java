package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mia
 * @create 2022-03-14-19:44
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/dept")
    @ResponseBody
    public List<Dept> getDepts(){
        return deptService.getDepts();
    }
}
