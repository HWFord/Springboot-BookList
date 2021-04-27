package com.fr.hwf.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.hwf.springboot.entites.Role;
import com.fr.hwf.springboot.entites.User;
import com.fr.hwf.springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return this.repository.findAll();
	}

	public void save(User user) {
		this.repository.save(user);
	}

	public User findUser(final Long userId) {
        return this.repository.findById(userId).orElse(null);
    }

    public Map<Long, String> getTemplateList(){
        Map<Long, String> result = new HashMap<>();

        for (User item : this.repository.findAll()) {
            result.put(item.getId(), item.getFirstname());
        }

        return result;
    }

    public List<User> findSellers() {
    	List<User> sellers = new ArrayList<>();
    	List<User> users = this.repository.findAll();
    	for (User user : users) {
    		if(user.getRole().getId()==2) {
    			sellers.add(user);
    		}
    	}
        return sellers;
    }
}
