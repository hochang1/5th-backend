package jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"lecture"})
//@Entity
public class Student {
	@Id
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name = "sname")
	private String sname;
	
//	@Column(name = "lid")
//	private Long lid;
	
	@ManyToOne				//패러다임 불일치를 해결하면서 엔터티간의 관계설정
	@JoinColumn(name = "lid")		//Lecture가 가지고 있는 컬럼을 이용하니까 join (foreign key 키로)
	//@Exclude
	private Lecture lecture;			//불일치 해결
	
}