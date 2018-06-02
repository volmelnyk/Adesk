package com.adesk.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;

    private String firstName;
    private String secondName;

    @Column(unique = true)
    private String username;
    private String password;
    private String description;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;



    public User(String mail, String username, String password) {
        this.email = mail;
        this.username = username;
        this.password = password;
    }

    public User(int id ,String email, String firstName, String secondName, String username, String password, String description, String photo, Role role, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Advert> adverts, City city, List<Response> responses, List<Phone> phones) {

        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
        this.description = description;
        this.photo = photo;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.adverts = adverts;
        this.city = city;
        this.responseList = responses;
        this.phones = phones;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
        authority.add(new SimpleGrantedAuthority(role.name()));
        return authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    List<Advert> adverts = new ArrayList<>();

    @ManyToOne
    private City city;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    List<Response> responseList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    List<Phone> phones = new ArrayList<>();

    @JsonGetter("phones")
    public List<Phone> getPhones() {
        return phones;
    }
    @JsonGetter("adverts")
    public List<Advert> getAdverts() {
        return adverts;
    }

    @JsonGetter("responseList")
    public List<Response> getResponses() {
        return responseList;
    }

}
