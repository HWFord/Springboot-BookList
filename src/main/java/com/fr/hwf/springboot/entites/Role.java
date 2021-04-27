package com.fr.hwf.springboot.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Role extends BaseEntity {

	@Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}

