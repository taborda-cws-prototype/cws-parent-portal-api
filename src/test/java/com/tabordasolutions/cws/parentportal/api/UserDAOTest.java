package com.tabordasolutions.cws.parentportal.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {
    private UserDAO dao;
    private SessionFactory sessionFactory;

    @Before
    public void setup() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        dao = new UserDAO(sessionFactory);
    }

    @After
    public void teardown() {
        sessionFactory.close();
    }

    // TODO: manage test database schema -- currently need to migrate the CI schema before pushing to GitHub
    // TODO: manage test fixtures -- currently need to see the database before pushing to GitHub
    @Test
    public void findByUsernameReturnsUser() throws Exception {
        User user = dao.findByUsername("joey.doe@example.com");

        assertNotNull(user);
    }

    @Test
    public void findByUsernameHandlesUnknownUser() throws Exception {
        User user = dao.findByUsername("bogus@example.com");

        assertNull(user);
    }

    @Test
    public void findByIdReturnsUser() throws Exception {
        User user = dao.find(1);

        assertNotNull(user);
    }

    @Test
    public void findByIdHandlesUnknownUser() throws Exception {
        User user = dao.find(-1);

        assertNull(user);
    }
    
    @Test 
    public void updateHandlesExistingUser() throws Exception {
    	User user = dao.find(1);
    	String firstname = user.getFirstName();
    	
    	user.setFirstName("foobar");
    	user = dao.update(user);
    	
    	User updatedUser = dao.find(1);
    	
    	
    	Assert.assertNotEquals(updatedUser.getFirstName(), firstname); 
    }
    
    @Test
    public void updateHandlesNotExistingUser() throws Exception {
		User.Builder builder = new User.Builder();
		User user = builder.id(-1L).city("Sacramento").email("a@test.com")
				.firstName("John").lastName("Doe")
				.imageUrl("http:www.xyzabc.co").inCareOf("Baby Boy")
				.password("password").state("CA")
				.streetAddress1("123 Main Street").zip("90210").build();
		
		User updatedUser = dao.update(user);
		Assert.assertTrue(true); 
    }
    
    @Test
    public void updateUserWithNonUniqueEmail() throws Exception {
    }
    
    @Test
	public void createNewUser() throws Exception {
		User.Builder builder = new User.Builder();
		User user = builder.city("Sacramento").email("joey.doe@example.com")
				.firstName("John").lastName("Doe")
				.imageUrl("http:www.xyzabc.co").inCareOf("Baby Boy")
				.password("password").state("CA")
				.streetAddress1("123 Main Street").zip("90210").build();
		long id = dao.create(user);
		assert(id > 0);
    }
    
    @Test
    public void createUserWhenIdAlreadyExists() throws Exception {
		User.Builder builder = new User.Builder();
		User user = builder.id(1L).city("Sacramento").email("a@test.com")
				.firstName("John").lastName("Doe")
				.imageUrl("http:www.xyzabc.co").inCareOf("Baby Boy")
				.password("password").state("CA")
				.streetAddress1("123 Main Street").zip("90210").build();
		long id = dao.create(user);
    }
    
    @Test
    public void createUserWhenEmailNotUnique() throws Exception {
    	
    }
}
