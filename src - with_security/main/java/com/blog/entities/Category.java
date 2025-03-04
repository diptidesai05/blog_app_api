package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY) TODO: difference betn AUTO n IDENTITY
	private Integer categoryId;
	@Column(name="title", length=100, nullable=false)
	private String categoryTitle;
	@Column(name="description")
	private String categoryDes;
	
	@OneToMany(mappedBy="category", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();

}
