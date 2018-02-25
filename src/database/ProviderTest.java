 package database;
 
 /**
  *  @author Francisco Rovelo
  */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.*;

public class ProviderTest {

	private ProviderRepository pr;
	private Provider p;
	
	@Before
	public void setUp() throws Exception {
		pr = new ProviderRepository();
		p = new Provider(172919951,"Francisco", "Temp Address", "Tuscaloosa", "Alabama", 35405);
	}
	
	/**
	 * @throws IOException throws error
	 */
	@Test
	public void testforsanity() throws IOException {
		pr.save(p);
		assertEquals("Francisco", p.getName());
		assertEquals("Alabama", p.getState());
	}
	/**
	 * @throws IOException throws error
	 */
	@Test
	public void testforSuccessOfaddEntry()throws IOException{
		pr.save(p);
		assertEquals("Francisco", p.getName());
	}
	
	@Test
	public void testforFailure()throws IOException{
		pr.save(p);
		p.setName("Fran");
		assertFalse(p.getName().equals("Francisco"));
	}
	
	/**
	 * @throws IOException throws error
	 */
	@After
	public void closeUp() throws IOException {
		pr.deleteById(172919951);
	}
	
	
}
