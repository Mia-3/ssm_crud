package com.itheima.test;

import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 测试dao层的工作
 * @author lfy
 *推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {


	@Test
	public void testCRUD() throws Exception{

		InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
		DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
		Dept dept = mapper.selectByPrimaryKey(1);
		System.out.println(dept);

		EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
		Emp emp = empMapper.selectByPrimaryKeyWithDept(1);
		System.out.println(emp);
		System.out.println("===================");
		System.out.println(empMapper.selectByPrimaryKey(2));

		EmpExample empExample = new EmpExample();
		empExample.createCriteria().andEmpIdEqualTo(5);
		System.out.println(empMapper.selectByExampleWithDept(empExample));

		System.out.println(PageHelper.startPage(3,6));
		List<Emp> list = empMapper.selectByExample(null);
		PageInfo<Emp> pageInfo = new PageInfo<>(list,5);
		System.out.println(pageInfo.getList());

	}

}
