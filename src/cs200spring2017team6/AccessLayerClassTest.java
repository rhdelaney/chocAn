package cs200spring2017team6;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import database.*;

/**
 * 
 * @author Jacob Cowles
 *
 */
public class AccessLayerClassTest {

	
	//TESTING FOR INVALID MEMBERS
	@Test(expected = FileNotFoundException.class)
	public void TestInvalidCode() throws IOException {
		boolean temp = AccessLayerClass.memberExists(0);
		assertFalse(temp);
	}

	
//TESTING FOR INVALID SERVICE
	@Test(expected = FileNotFoundException.class)
	public void TestServiceDoesNotExist() throws FileNotFoundException {
		boolean temp = AccessLayerClass.serviceExists(0);
		assertFalse(temp);
	}


//TESTING FOR VALID PROVIDER CODE
	@Test
	public void TestProviderExists() throws IOException {
		Provider testProvider = new Provider(123456789, "Jake", "Waffle House", "Tuscaloosa", "Alabama", 35401);
		AccessLayerClass.providers = new ProviderRepository();
		AccessLayerClass.providers.save(testProvider);
		assertTrue(AccessLayerClass.providerExists(123456789));
	}

}