package com.BadaBazaar.BadaBazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.model.Item;
import com.BadaBazaar.BadaBazaar.model.Product;
import com.BadaBazaar.BadaBazaar.repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.repository.ItemRepository;
import com.BadaBazaar.BadaBazaar.repository.ProductRepository;
import com.BadaBazaar.BadaBazaar.responseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {

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
