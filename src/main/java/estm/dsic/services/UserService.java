package estm.dsic.services;

import estm.dsic.dal.UserDao;
import estm.dsic.models.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class UserService {
	private UserDao userdao=new UserDao();
	public User Login(User u){
		return userdao.login(u);
	}

	public Vector<User> getUsers(){
		return userdao.getUsers();
	}

	public User getUser(String email){
		return userdao.getUser(email);
	}

	public boolean register(User u){
		return userdao.register(u);
	}

	public boolean editUser(User u){
		return userdao.update(u);
	}

	public boolean deleteUser(User u){
		return userdao.delete(u);
	}


}
