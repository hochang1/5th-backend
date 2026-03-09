package com.spring.jpa.entitiy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lid")
	private Long lid;
	
	@Column(name = "lname")
	private String lname;
	
	@OneToMany(mappedBy = "lecture", fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)		//cascade all은 잘 안씀(위험), persist나 merge 주로 씀		
	@BatchSize(size = 10)
	private List<Student> students = new ArrayList<>();
	
}
