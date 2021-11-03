package jcpdev.day2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jcpdev.day2.mapper.CustomerMapper;
import jcpdev.day2.mapper.FreeboardMapper;
import jcpdev.day2.service.FreeboardService;


 /*jUnit: 테스트 케이스 도구(단위 케이스)
*	지금 사용하는 버전은 junit5(jupiter)
*	@Test 어노테이션붙은 경우면 실행
*/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/applicationContext.xml"})
class MybatisTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	FreeboardMapper fdao;
	
	@Autowired
	FreeboardService fservice;
	
	@Autowired
	CustomerMapper cdao;
	
	//@Test	// test할 메소드에 작성 -> 출력문 작성 또는 test cate 메소드로 성공 또는 실패로 리턴
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void sqlTest() {
		// System.out.println(fdao.getCount());
		System.out.println(cdao);
		assertNotNull(cdao.selectOne(1));
		System.out.println(cdao.selectOne(1));
	}
	
	//@Test
	void connect() {
		SqlSessionTemplate sqlSession = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
		assertNotNull(sqlSession);	// null인지 확인해주는 메소드 true이면 성공 false이면 x
		System.out.println(sqlSession);
		
		assertNull(fdao);	// null이라면 성공 아니라면 x 실패
		System.out.println(fdao);

		assertNotNull(fservice);
		System.out.println(fservice);
		
	}

}
