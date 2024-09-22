package com.bada.bazaar.service.Impl;

import com.bada.bazaar.converter.ItemConverter;
import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Item;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.ItemRepository;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.service.ItemService;
import com.bada.bazaar.util.CommonServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bada.bazaar.enums.Role.CUSTOMER;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {


  private final ProductRepository productRepository;
  private final ItemRepository itemRepository;
  private final ItemConverter itemConverter;
  private final CustomerRepository customerRepository;

  @Override
  public ItemResponseDto viewItem(int productId, HttpServletRequest request) {
    Optional<Product> product = productRepository.findById(productId);
    if(product.isEmpty()) {
      throw new ApiException(ErrorConstants.PRODUCT_NOT_FOUND);
    }
    Item item = Item.builder()
      .quantity(0)
      .productId(productId)
      .build();
    item = itemRepository.save(item);

    CommonServices.UserInfo userInfo = CommonServices.getUserInfo(request);
    if(userInfo.role().equals(CUSTOMER.getValue())){
      Customer customer = customerRepository.findById(Integer.valueOf(userInfo.userId())).get();
      customer.getBrowsingHistoryIds().add(productId);
    }

    return itemConverter.itemToItemResponseDto(item);
  }
}
