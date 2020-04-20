package com.hearc.agendarc.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User owner;

    @JsonIgnore
    @ManyToMany
    private Set<User> users;

    private String roleName;
    
    public Calendar() {
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String role) {
		this.roleName = role;
	}

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    
    /**
     * Returns whether the given user has admin rights.
     * 
     * @param user User to test
     * @return True if the user is the owner of the ToDont
     */
    public boolean isOwner(User user) {
        return user == owner;
    }

    @Override
    public String toString() {
        return "Calendar [id=" + id + ", name=" + name + ", owner=" + owner + ", users=" + users + "]";
    }
}