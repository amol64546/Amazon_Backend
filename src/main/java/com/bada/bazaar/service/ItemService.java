package com.bada.bazaar.service;

import com.bada.bazaar.responseDto.ItemResponseDto;

public interface ItemService {

    public ItemResponseDto viewItem(int productId) throws Exception;


}
