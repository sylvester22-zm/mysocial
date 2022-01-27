package com.vanilla.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vanilla.security.UserRole;
import com.vanilla.utilities.Authorities;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
    Long id ;
    String username;
    String firstname;
    String lastname;
    String email;
     String password;
     boolean enabled=true;

     @OneToMany(mappedBy = "user", cascade= CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
   Set<UserRole> userRole=new HashSet<>();
       
	/*
	 * @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "profile_id", referencedColumnName = "id") private
	 * UserProfile profile;
	 */
     
     public User(Object o) {
    	 this.firstname=o.toString().formatted(firstname);
    	 this.username=o.toString().formatted(username);
     }
     
	public Set<UserRole> getUserRole() {
    return userRole;
}
 public User() {}
public void setUserRole(Set<UserRole> userRole) {
    this.userRole = userRole;
}
       public User(Long id) {
    	   this.id=id;
       }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        Set<GrantedAuthority> authority=new HashSet<>();
        userRole.forEach(ur->authority.add(new Authorities(ur.getRole().getName())));
                
      
        return authority;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    
    


}
