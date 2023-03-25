package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;

public interface ItemService {

    public ItemResponseDto viewItem(int productId) throws Exception;
}
