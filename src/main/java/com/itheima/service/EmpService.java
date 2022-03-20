package com.itheima.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.engine.TemplateManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Mia
 * @create 2022-03-13-21:41
 */
@Service
public class EmpService {

    public PageInfo<Emp> list(Integer pn) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        PageHelper.startPage(pn, 6);
        List<Emp> list = empMapper.selectByExampleWithDept(null);
        PageInfo<Emp> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    //保存员工
    public void saveEmp (Emp emp) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        empMapper.insertSelective(emp);
    }


    public Boolean checkEmp(String empName) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameEqualTo(empName);
        List<Emp> list = empMapper.selectByExample(empExample);
        if(list.isEmpty())
            return false;
        else
            return true;
    }

    public Emp getEmp(Integer empId) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        return empMapper.selectByPrimaryKey(empId);
    }

    public void updateEmp(Emp emp) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        empMapper.updateByPrimaryKeySelective(emp);

    }

    public void deleteEmpById(Integer empId) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        empMapper.deleteByPrimaryKey(empId);
    }

    public void deleteEmps(String empIds) {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        String[] emps = empIds.split("_");
        for(int i=0;i<emps.length;i++){
            Integer empId = Integer.parseInt(emps[i]);
            empMapper.deleteByPrimaryKey(empId);
        }
    }
}
