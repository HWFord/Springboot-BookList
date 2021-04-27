package com.fr.hwf.springboot.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table(name="book")
public class Book extends BaseEntity{

	@NotNull
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int nbpages;

	@Column(nullable = false)
	private double price;

	@ManyToOne(targetEntity = User.class, optional = true)
    private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbpages() {
		return nbpages;
	}

	public void setNbpages(int nbpages) {
		this.nbpages = nbpages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", nbpages=" + nbpages + ", price=" + price + ", user=" + user + "]";
	}


}
