package com.mjf.service;

import com.mjf.entity.Shop;
import com.mjf.repository.ShopRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService{
  ShopRepository shopRepository;

  public ShopServiceImpl(final ShopRepository shopRepository) {
    this.shopRepository = shopRepository;
  }

  @Override
  public String createShop(final Shop shop) {
    shopRepository.save(shop);
    return "Shop created Successfully";
  }

  @Override
  public List<Shop> fetchShop() {
    return shopRepository.findAll();
  }

  @Override
  public Optional<Shop> findShopById(final Long id) {
    return shopRepository.findById(id);
  }
}
