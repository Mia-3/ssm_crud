package com.itheima.service;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Mia
 * @create 2022-03-14-19:46
 */
@Service
public class DeptService {
    public List<Dept> getDepts() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> list = deptMapper.selectByExample(null);
        return list;
    }
}
