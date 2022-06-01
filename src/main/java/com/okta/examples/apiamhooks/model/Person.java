package com.okta.examples.apiamhooks.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    String email;

    @OneToMany
    List<Beer> favoriteBeers = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Beer> getFavoriteBeers() {
		return favoriteBeers;
	}

	public void setFavoriteBeers(List<Beer> favoriteBeers) {
		this.favoriteBeers = favoriteBeers;
	}
}
