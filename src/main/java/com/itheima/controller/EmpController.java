package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Msg;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mia
 * @create 2022-03-13-20:09
 */
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/list")
    @ResponseBody
    public Msg list(String pn) {
        int pageNo = Integer.parseInt(pn);
        PageInfo<Emp> pageInfo = empService.list(pageNo);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg save(@Valid Emp emp, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            empService.saveEmp(emp);
            return Msg.success();
        }

    }

    @RequestMapping("/checkEmp")
    @ResponseBody
    public Msg checkEmp(String empName) {
        Boolean empIsExist = empService.checkEmp(empName);
        if (empIsExist == true)
            return Msg.fail();
        else
            return Msg.success();
    }

    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(Integer empId){
        Emp emp =  empService.getEmp(empId);
        return Msg.success().add("emp",emp);
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Emp emp,@PathVariable("id") Integer id){
        emp.setEmpId(id);
        empService.updateEmp(emp);
        return Msg.success();
    }

    @RequestMapping(value = "/emp/{empIds}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("empIds") String empIds){
        if(empIds.contains("_")){
            //批量删除
            empService.deleteEmps(empIds);
        }
        else{
            Integer empId = Integer.parseInt(empIds);
            empService.deleteEmpById(empId);
        }
        return Msg.success();
    }
}
