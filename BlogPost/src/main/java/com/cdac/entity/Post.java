package com.cdac.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
@Column(name="post_title",nullable=false)
private String title;
@Column(nullable=false,columnDefinition = "TEXT")
private String content;
@Enumerated(EnumType.STRING)
private PostStatus status;
@Column(nullable=false)
private Integer readingTime;
@CreationTimestamp
private LocalDateTime createdOn;
@UpdateTimestamp
private LocalDateTime updateOn;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="user_id",nullable = false)
private User myUser;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="category_id",nullable=false)
private Category myCategory;

@ManyToMany
@JoinTable(
		name = "post_tags",
		joinColumns=@JoinColumn(name="post_id"),
		inverseJoinColumns = @JoinColumn(name="tags_id")
		)
private Set<Tag> tags=new HashSet<>();

@Override
public int hashCode() {
	return Objects.hash(content, createdOn, id, readingTime, status, title, updateOn);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Post other = (Post) obj;
	return Objects.equals(content, other.content) && Objects.equals(createdOn, other.createdOn)
			&& Objects.equals(id, other.id) && Objects.equals(readingTime, other.readingTime) && status == other.status
			&& Objects.equals(title, other.title) && Objects.equals(updateOn, other.updateOn);
}
@PrePersist
public void createdOn() {
	this.createdOn=LocalDateTime.now();
	this.updateOn=LocalDateTime.now();
}
@PreUpdate
public void updatedOn() {
	this.updateOn=LocalDateTime.now();
}
}
