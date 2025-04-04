package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "S_USERS")
@NoArgsConstructor
@Data
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private Integer id;
	
	@Column(name = "X_USER_NAME")
	private String name;
	
	@Column(name = "X_USER_EMAIL")
	private String email;

	@Column(name = "X_USER_PASSWORD")
	private String password;
	
	@Column(name = "X_USER_ABOUT")
	private String about;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "S_USER_ROLE", 
			joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ROW_ID"),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROW_ID")
	)
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = this.roles.stream().map(m -> new SimpleGrantedAuthority(m.getName())).collect(Collectors.toList());
		return list;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
