package com.pinyougou.service.ship.impl;


import org.springframework.stereotype.Service;

import com.pinyougou.service.ship.ShipService;


@Service
public class ShipServiceImpl implements ShipService{

	@Override
	public String saveShipOrder(Long orderId) {
		return "return msg";
	}

	
}
