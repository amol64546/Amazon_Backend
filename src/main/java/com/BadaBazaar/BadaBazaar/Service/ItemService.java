package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Item;
import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;

import java.util.List;

public interface ItemService {

    public ItemResponseDto viewItem(int productId) throws Exception;


}
