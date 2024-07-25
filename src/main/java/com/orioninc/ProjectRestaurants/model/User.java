package com.orioninc.ProjectRestaurants.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password_hash")
  private String passwordHash;

  //  @Enumerated(EnumType.STRING)
  //    @OneToMany(mappedBy = "user")
  //    private Role role;
  @JsonBackReference
  @ManyToMany(fetch = FetchType.EAGER) // n+1
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

  @JsonBackReference
  //  @OneToMany(mappedBy = "restaurant")
  @OneToMany(mappedBy = "user") // n+1
  private List<Order> orderList;
}

// TODO leverage entities by spec. attributes