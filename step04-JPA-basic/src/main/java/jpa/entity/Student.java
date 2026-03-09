package jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity			//등록해줘야됨
public class Student {
	@Id			//등록해줘야됨 pk설정
	@Column(name = "sid")
	private Integer sid;
	
	@Column(name="sname")	
//	 컬럼 옵셕값 설정해줄수 있다.
//	@Column(columnDefinition = "VARCHAR(30) DEFAULT 'SNAME", insertable = false, updatable = false)
//	@Column(name = "sname", nullable = false, length = 10)
	private String sname;

//	@Column(precision = 10, scale =2)
//		private BigDecimal price;

	
	

	
}
