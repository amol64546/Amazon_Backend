package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.entity.Item;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;

public interface CartService {

  ModelMap addToCart(OrderRequestDto orderRequestDto, HttpServletRequest request);

  ModelMap checkout(Integer customerId, Integer cardId, HttpServletRequest request);

  Page<Item> viewItems(Integer customerId, Pageable pageable, HttpServletRequest request);
}
