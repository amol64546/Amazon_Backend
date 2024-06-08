package com.BadaBazaar.BadaBazaar.util;

import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
import com.BadaBazaar.BadaBazaar.model.Seller;
import com.BadaBazaar.BadaBazaar.repository.SellerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SellerCacheUtil {
  private final SellerRepository sellerRepository;

  @Caching(evict = {@CacheEvict(value = "retrieveAllSellers", allEntries = true)},
    put = {@CachePut(cacheNames = "seller", key = "#seller.sellerId")})
  public Seller saveSeller(Seller seller) {
    log.info("-----Persisting seller for id: {}-----", seller.getSellerId());
    sellerRepository.save(seller);
    return seller;
  }

  @Cacheable(cacheNames = "seller", key = "#id")
  public Seller getSeller(Integer id) {
    log.info("-----Fetching seller from DB for id: {}-----", id);
    Optional<Seller> seller = sellerRepository.findById(id);
    if (seller.isEmpty()) {
      throw new SellerNotFoundException("Seller not found for id: " + id);
    }
    return seller.get();
  }

  @Caching(evict = {@CacheEvict(value = "retrieveAllSellers", allEntries = true),
    @CacheEvict(value = "seller", key = "#id")})
  public void deleteSeller(Integer id) {
    log.info("----- Evicting seller for id: {} -----", id);
    sellerRepository.deleteById(id);
  }

  @Cacheable(value = "retrieveAllSellers")
  public List<Seller> getAllSellers() {
    log.info("------Retrieve all Workflows from the DB-----");
    return sellerRepository.findAll();
  }



}

