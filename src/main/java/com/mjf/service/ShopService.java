package com.mjf.service;

import com.mjf.entity.Shop;
import java.util.List;
import java.util.Optional;

public interface ShopService {

  public String createShop(Shop shop);
  public List<Shop> fetchShop();
  public Optional<Shop> findShopById(int id);
}
