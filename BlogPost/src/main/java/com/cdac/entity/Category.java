package com.cdac.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
@Column(name="category_name",nullable = false,unique = true)
private String name;
@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "myCategory")
private List<Post> posts=new ArrayList<>();
@Override
public int hashCode() {
	return Objects.hash(id, name);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Category other = (Category) obj;
	return Objects.equals(id, other.id) && Objects.equals(name, other.name);
}

}
