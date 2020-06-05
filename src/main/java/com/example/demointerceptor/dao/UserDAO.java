package com.example.demointerceptor.dao;

import java.util.ArrayList;

import com.example.demointerceptor.model.User;

public class UserDAO {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user){
        User u = findUser(user.getUsername());
        if(u != null){
            users.remove(u);
        }
        users.add(user);
    }

    public User findUser(String username){
        for (User user : users) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

	public ArrayList<User> findAll() {
		return users;
	}
}