package com.example.justone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.SampleController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SampleController.class)
@Transactional

public class JustOneRepositoryTest {
	private static final String _1234 = "1234";
	private static final String HELLO_WORLD="hello world";
	private static final int _10 = 10;
	
	@Autowired JustOneRepository repository;	

	@Before
	public void setUp() throws Exception {
/*		JustOne one = new JustOne();
		one.setDateJoin(new Date());
		one.setEnumOne(JustOneEnum.TWO);
		one.setMessage(HELLO_WORLD);
		one.setPasswordConfirm(_1234);
		repository.save(one);
*/
		
	}
	
	@Test
	@Ignore(value="Test Ignore")
	public void 읽고난뒤수정() throws Exception {
		List<JustOne> ones = repository.findAll();
		JustOne one = ones.get(ones.size()-1);
		assertEquals(one.getMessage(), HELLO_WORLD);
		
		one.setMessage("ni3hao3");
		repository.save(one);
		JustOne getOne = repository.getOne(one.getId());
		assertEquals(getOne.getMessage(), one.getMessage());
	}
	
	@Test
	@Ignore(value="Test Ignore")
	public void 삭제() throws Exception {
		repository.deleteAll();
		assertEquals(repository.count(), 0);
	}

	@Test
	@Ignore(value="Test Ignore")
	public void 페이징() throws Exception {
		for(int i = 0; i < 40; i++) {
			JustOne one = new JustOne();
			one.setDateJoin(new Date());
			one.setEnumOne(JustOneEnum.TWO);
			one.setMessage(HELLO_WORLD+i);
			one.setPasswordConfirm(_1234);
			repository.save(one);
		}
		
		Pageable pageable = new PageRequest(0, _10);
		Page<JustOne> ones = repository.findAll(pageable);
		
		List<JustOne> onesList = ones.getContent();
		for(int i = 0; i < 10; i++) {
			System.out.println(onesList.get(i).getMessage()+" , "+ HELLO_WORLD+i);
			assertEquals(onesList.get(i).getMessage(), HELLO_WORLD+i);
		}
		assertEquals(onesList.size(), 10);
	}
	
	@Test
	@Ignore(value="Test Ignore")
	public void 소팅() throws Exception {
		for(int i = 0; i < 40; i++) {
			JustOne one = new JustOne();
			one.setDateJoin(new Date());
			one.setEnumOne(JustOneEnum.TWO);
			one.setMessage(HELLO_WORLD+i);
			one.setPasswordConfirm(_1234);
			repository.save(one);
		}
		Sort sort = new Sort(Direction.DESC, "id");		
		Pageable pageable = new PageRequest(0, _10, sort);
		Page<JustOne> ones = repository.findAll(pageable);
		
		List<JustOne> onesList = ones.getContent();
		for(int i=39, j=0; i >= 30; i--,j++) {			
			assertEquals(onesList.get(j).getMessage(), HELLO_WORLD+i);
		}		
	}
	
	@Test
	@Ignore(value="Test Ignore")
	public void 특정메시지로_가져오기() throws Exception {
		JustOne one = repository.findByMessage(HELLO_WORLD);
		assertNotNull(one);
		assertEquals(one.getMessage(), HELLO_WORLD);
	}
	
	@Test
	public void 날짜이전_특정메시지로찾기() throws Exception {
		repository.deleteAll();
		
		Calendar calendar;
		for (int i = 0; i < 10; i++) {
			calendar = new GregorianCalendar(2010+i, 1, 1, 13, 24, 56);
			JustOne one = new JustOne();
			one.setDateJoin(calendar.getTime());
			one.setMessage(i%2==0? HELLO_WORLD: _1234);
			repository.save(one);
		}
		
		calendar = new GregorianCalendar(2015, 1, 1, 13, 24, 56);
		List<JustOne> getList = repository.findBydateJoinBeforeAndMessage(calendar.getTime(), HELLO_WORLD);
		
		int year=2010;
		for(JustOne justOne : getList) {
			calendar.set(year, 1, 1, 13, 24, 56);
			assertEquals(justOne.getMessage(), HELLO_WORLD);
			assertEquals(justOne.getDateJoin(), calendar.getTime());
			year+=2;
		}
	}
	
	
}
