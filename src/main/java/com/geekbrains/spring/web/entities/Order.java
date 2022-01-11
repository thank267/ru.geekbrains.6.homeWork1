package com.geekbrains.spring.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "total_price")
	private int total;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, updatable=false)
	private User user;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
	private List<OrderItem> orderItems;


}
