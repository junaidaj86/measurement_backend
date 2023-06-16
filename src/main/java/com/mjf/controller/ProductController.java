package com.mjf.controller;

import com.mjf.config.UserInfoUserDetails;
import com.mjf.dto.AuthRequest;
import com.mjf.dto.CreateUserRequest;
import com.mjf.dto.JwtResponse;
import com.mjf.dto.Product;
import com.mjf.entity.Shop;
import com.mjf.entity.UserInfo;
import com.mjf.repository.CustomerRepository;
import com.mjf.repository.OrderRepository;
import com.mjf.repository.PantRepository;
import com.mjf.repository.ShirtRepository;
import com.mjf.service.JwtService;
import com.mjf.service.ProductService;
import com.mjf.service.ShopService;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ShopService shopService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
  CustomerRepository customerRepository;
    @Autowired
  ShirtRepository shirtRepository;
    @Autowired
  PantRepository pantRepository;
    @Autowired
  OrderRepository orderRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody CreateUserRequest cur) {
      UserInfo ui = new UserInfo();
      ui.setEmail(cur.getEmail());
      ui.setName(cur.getName());
      ui.setRoles(cur.getRoles());
      ui.setPassword(cur.getPassword());
      Optional<Shop> shop = shopService.findShopById( cur.getShopId());
      if(shop.isPresent()){
        ui.setShop(shop.get());
      }
        return service.addUser(ui);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
          UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
          List<String> roles = userDetails.getAuthorities().stream()
                                          .map(item -> item.getAuthority())
                                          .collect(Collectors.toList());
          String jwt = jwtService.generateToken(authRequest.getUsername());
          return ResponseEntity.ok(new JwtResponse(jwt,
                                                   1L,
                                                   userDetails.getUsername(),
                                                   "",
                                                   roles));

        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


}
