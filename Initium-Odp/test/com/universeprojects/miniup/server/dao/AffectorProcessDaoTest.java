package com.universeprojects.miniup.server.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.universeprojects.cacheddatastore.CachedDatastoreService;
import com.universeprojects.miniup.server.ODPDBAccess;
import com.universeprojects.miniup.server.domain.AffectorProcess;

import helper.utilities.HttpServletRequestImpl;

public class AffectorProcessDaoTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private AffectorProcessDao testObj;

	@Before
	public void before() {
		helper.setUp();
		CachedDatastoreService.disableRemoteAPI();
		testObj = new AffectorProcessDao(new ODPDBAccess(new HttpServletRequestImpl()).getDB());
	}

	@After
	public void after() {
		helper.tearDown();
	}

	@Test
	public void saveAndGet() { // This works because of the caching
		AffectorProcess expectedAffectorProcess = new AffectorProcess();

		testObj.save(expectedAffectorProcess);
		AffectorProcess actualAffectorProcess = testObj.get(expectedAffectorProcess.getCachedEntity().getKey());

		assertEquals(expectedAffectorProcess, actualAffectorProcess);
	}
}
