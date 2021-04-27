package com.fr.hwf.springboot.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

	private String firstname;
	private String lastname;
	private Long roleId;
	private List<Long> listOfBookIds = new ArrayList<Long>();

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Long> getListOfBookIds() {
		return listOfBookIds;
	}

	public void setListOfBookIds(List<Long> listOfBookIds) {
		this.listOfBookIds = listOfBookIds;
	}

}
