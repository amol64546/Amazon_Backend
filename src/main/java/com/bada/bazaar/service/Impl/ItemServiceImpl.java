package com.bada.bazaar.service.Impl;

import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {


  @Override
  public ItemResponseDto viewItem(int productId) {
    return null;
  }
}
