package schedule.service.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

//import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import schedule.service.contract.IGroupService;

//@RunWith(Arquillian.class)
public class GroupServiceTest {

	@Inject
	private IGroupService groupService;

	@Test
	public void testGetAll() throws Exception {
		// fail("Not yet implemented");
		groupService.getAll();
	}

	@Test
	public void testGetForId() throws Exception {
		// fail("Not yet implemented");

		throw new Exception("lol");
	}

	@Test
	public void testGetForTitle() {
		// fail("Not yet implemented");
	}

	@Test
	public void testDeleteForId() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateIfNotExist() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteListOfGroup() {
		fail("Not yet implemented");
	}

}
