package jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@ToString
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pid")
	private Long pid;
	
	// ....
	
//	@ManyToMany		//중간테이블이 두개생겨서 적합하지않다 -> 엔터티 추가해서 중간테이블을 만들자
//	private List<Customer> customers = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<Order> orders = new ArrayList<>();
}