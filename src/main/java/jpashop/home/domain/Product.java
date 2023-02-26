package jpashop.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Product {

	@Id @GeneratedValue
	@Column(name="PRODUCT_ID")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockAmount;
}
