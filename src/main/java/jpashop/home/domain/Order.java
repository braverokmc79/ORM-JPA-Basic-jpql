package jpashop.home.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;


/**
 * Order 주의 Order 는 예약어라 Orders 사용
 */
@Entity
@Getter
@Table(name="ORDERS")
public class Order {
	
	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Long id;
	
	private int orderAmount;
	
	@Embedded
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	

}
