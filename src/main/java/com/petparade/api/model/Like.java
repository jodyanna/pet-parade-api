package com.petparade.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Likes")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Temporal(TemporalType.DATE)
  @Column(name = "dateCreated")
  private Date dateCreated;

  @JsonBackReference(value = "likedPet")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "FK_Likes_Pets")
  private Pet pet;

  @JsonBackReference(value = "likingUser")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "FK_Likes_Users")
  private User user;

  // Constructors
  public Like() {}

  public Like(Long id, Date dateCreated, Pet pet, User user) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.pet = pet;
    this.user = user;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public Pet getPet() {
    return pet;
  }

  public User getUser() {
    return user;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Like like = (Like) o;
    return Objects.equals(getId(), like.getId()) && Objects.equals(getDateCreated(), like.getDateCreated()) && Objects.equals(getPet(), like.getPet()) && Objects.equals(getUser(), like.getUser());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDateCreated());
  }

  @Override
  public String toString() {
    return "Like{" +
        "id=" + id +
        ", dateCreated=" + dateCreated +
        ", pet=" + pet +
        ", user=" + user +
        '}';
  }
}
