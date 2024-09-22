package com.bada.bazaar.service;

import com.bada.bazaar.dto.response.ItemResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ItemService {


  ItemResponseDto viewItem(int productId, HttpServletRequest request);
}
