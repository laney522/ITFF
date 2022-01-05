package com.kh.spring.goods.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String orderNo;
	private String memberId;
	private int totalPrice;
	private Date orderDate;
}
