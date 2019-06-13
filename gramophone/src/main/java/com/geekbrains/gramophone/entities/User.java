package com.geekbrains.gramophone.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "singer")
    private Boolean singer;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany
    @JoinTable(name = "users_playlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> playlistList;

    // подписчики
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "singer_id")}, //я певец
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")} //на меня подписаны
    )
    private Set<User> subscribers = new HashSet<>();

    // собственные подписки пользователя
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")}, //я подписан
            inverseJoinColumns = {@JoinColumn(name = "singer_id")} //на вот этих певцов
    )
    private Set<User> subscriptions = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Boolean isSinger, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.singer = isSinger;
        this.email = email;
        this.phone = phone;
    }

    public User(String username, String password, String firstName, String lastName, Boolean isSinger, String email, String phone,
                Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.singer = isSinger;
        this.email = email;
        this.roles = roles;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
