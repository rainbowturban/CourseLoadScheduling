package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface UserService
{
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param registerUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */
	public List<Integer> createUser(String email, String password) throws SQLException;
    public User login(String email, String password) throws SQLException;
	public List<Integer> editUser(int id, String fname, String lname, String pass) throws SQLException;
    public List<Integer> promote(String email) throws SQLException;
}
