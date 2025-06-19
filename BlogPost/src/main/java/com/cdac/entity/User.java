package com.cdac.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
@Column(name="email",nullable = false,unique = true)
private String email;
@Column(name="password",nullable = false)
private String password;
@Column(name="name",nullable = false)
private String name;
@OneToMany(mappedBy ="myUser",cascade =CascadeType.ALL,orphanRemoval = true)
private List<Post> posts;
@CreationTimestamp
private LocalDateTime createdAt;
@Override
public int hashCode() {
	return Objects.hash(createdAt, email, id, name, password);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
			&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
			&& Objects.equals(password, other.password);
}
@PrePersist
protected void onCreate() {
	this.createdAt=LocalDateTime.now();
}

}
