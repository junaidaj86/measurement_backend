package com.mjf.controller;

import com.mjf.entity.Shop;
import com.mjf.service.ShopService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/shop")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
  ShopService shopService;

  public AdminController(final ShopService shopService) {
    this.shopService = shopService;
  }

  @PostMapping()
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String createSHop(@RequestBody Shop shop){
    return shopService.createShop(shop);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<Shop> getAllShop(){
    return shopService.fetchShop();
  }
}
