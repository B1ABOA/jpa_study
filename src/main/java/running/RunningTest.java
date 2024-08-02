package running;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import model.domain.entity.Dept;
import model.domain.entity.Emp;
import util.DBUtil;

public class RunningTest {
	
	@Test
	public void stpe01Test() {
		EntityManager em = DBUtil.getEntityManager();
		Emp emp = em.find(Emp.class, 7782L);
		System.out.println("사원 아이디가 7782 사람 : " + emp.getEname());
		System.out.println("사원 아이디가 7782 사람 : " + emp.getEname() + " / 부서명 :  " + emp.getDeptno().getDeptno());
		System.out.println("사원 아이디가 7782 사람 : " + emp.getDeptno());

		
		Dept dept = em.find(Dept.class, 10L);
		System.out.println("부서 아이디가 10인 부사 : " + dept.getDname());
		
		List<Emp> emps = dept.getEmps();
		emps.forEach(System.out::println);
		
		em = null;
		
	}

}
