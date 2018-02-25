package database;
/**
 *  @author Raychel Delaney
 */
import static org.junit.Assert.*;
import java.io.IOException;


import org.junit.Before;
import org.junit.Test;

import database.Member;
import database.MemberRepository;
public class MemberTest {
	Member memeber;
	MemberRepository repo;
	//private MemberRepository members;
	//private ProviderRepository providers;
	//private VisitRepository visits;
	@Before
	public void setUp() {	
	}
	
	@Test
	public void sanityTest() throws IOException{
		memeber= new Member(123456789,"John Smith","123 Main Street","Tuscaloosa","AL",35404);
		assertEquals(123456789,memeber.getId());
		assertEquals("John Smith",memeber.getName());
		assertEquals("123 Main Street",memeber.getAddress());
		assertEquals("Tuscaloosa",memeber.getCity());
		assertEquals("AL",memeber.getState());
	}
	
	@Test
	public void failureTest() throws IOException{
		memeber= new Member(123456789,"John Smith","123 Main Street","Tuscaloosa","AL",35404);
		memeber.setName("Raychel Delaney");
		assertFalse(memeber.getName().equals("John Smith"));
		//repo.save(member);
		//repo.deleteById(123456789);
		//assertTrue(repo.findById(123456789).equals(""));
	}
	
	@Test
	public void successTest() throws IOException{
		memeber= new Member(123456789,"John Smith","123 Main Street","Tuscaloosa","AL",35404);
		//repo.save(member);
		//repo.deleteById(123456789);
		memeber.setState("CA");
		assertEquals("CA",memeber.getState());
	}
	
//	@After
//	public void closeUp() throws IOException {
//		repo.deleteById(123456789);
//	}

}
