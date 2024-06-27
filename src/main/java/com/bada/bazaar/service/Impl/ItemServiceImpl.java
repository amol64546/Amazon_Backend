package com.bada.bazaar.service.Impl;

import com.bada.bazaar.model.Item;
import com.bada.bazaar.model.Product;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.ItemRepository;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.responseDto.ItemResponseDto;
import com.bada.bazaar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public ItemResponseDto viewItem(int productId) throws Exception{

        Product product;

        try{
            product = productRepository.findById(productId).get();
        }catch (Exception e){
            throw new Exception("Product is not available");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        itemRepository.save(item);

//        product.setItem(item);
//        productRepository.save(product);


        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .price(product.getPrice())
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .productCategory(product.getProductCategory())
                .build();

        return itemResponseDto;
    }


}
