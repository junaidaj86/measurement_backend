package com.mjf.controller;

import com.mjf.config.UserInfoUserDetails;
import com.mjf.dto.MessageResponse;
import com.mjf.dto.OrderDTO;
import com.mjf.dto.OrderResponse;
import com.mjf.entity.Customer;
import com.mjf.entity.Order;
import com.mjf.entity.Pant;
import com.mjf.entity.Shirt;
import com.mjf.entity.Shop;
import com.mjf.entity.UserInfo;
import com.mjf.repository.CustomerRepository;
import com.mjf.repository.OrderRepository;
import com.mjf.repository.PantRepository;
import com.mjf.repository.ShirtRepository;
import com.mjf.repository.ShopRepository;
import com.mjf.repository.UserInfoRepository;
import com.sun.security.auth.UserPrincipal;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {

  CustomerRepository customerRepository;
  ShirtRepository shirtRepository;
  PantRepository pantRepository;
  OrderRepository orderRepository;

  UserInfoRepository userInfoRepository;

  ShopRepository shopRepository;



  public OrderController(CustomerRepository customerRepository, ShirtRepository shirtRepository,
      PantRepository pantRepository, OrderRepository orderRepository,
      UserInfoRepository userInfoRepository, ShopRepository shopRepository) {
    this.customerRepository = customerRepository;
    this.shirtRepository = shirtRepository;
    this.pantRepository = pantRepository;
    this.orderRepository = orderRepository;
    this.userInfoRepository = userInfoRepository;
    this.shopRepository = shopRepository;
  }



  @GetMapping("/user/order")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public ResponseEntity<List<OrderResponse>> getAllOrders(Authentication authentication) {
    UserInfoUserDetails currentUser = (UserInfoUserDetails) authentication.getPrincipal();
    List<OrderResponse> orderResponses;
    if((currentUser.getRole()).equals("ROLE_USER")){
      orderResponses = orderRepository.findAllOrderDetailsForShop(currentUser.getShopId());
    }else{
      orderResponses = orderRepository.findAllOrderDetails();
    }




    return ResponseEntity.ok(orderResponses);
  }

  @PostMapping("/user/order")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderDTO orderDto, Authentication authentication) {
    try {
      UserInfoUserDetails currentUser = (UserInfoUserDetails) authentication.getPrincipal();
      Optional<Shop> shop = null;
      if(currentUser.getShopId() != null){
        shop = shopRepository.findById(currentUser.getShopId());
      }
      Customer customer = null;
      if (!customerRepository.existsByPhone(orderDto.getCustomer().getPhone())) {
        // Create a new customer if the phone number doesn't exist
        customer = orderDto.getCustomer();
        customerRepository.save(customer);
      } else {
        // Retrieve the existing customer by phone number
        customer = customerRepository.findByPhone(orderDto.getCustomer().getPhone());
      }

      // Associate the shirt with the customer
      Shirt shirt = shirtRepository.save(orderDto.getShirt());
      Pant pant = pantRepository.save(orderDto.getPant());

      // Set the order creation and delivery dates
      LocalDate createDate = LocalDate.now();
      //LocalDate deliverDate = createDate.plusDays(7);
      LocalDate deliverDate = null;

      // Create the order
      Order order = new Order(generateOrderNumber(shop.get().getName()),shirt, pant, customer,
                              shop.get(), createDate, deliverDate,
                              "Not-ASSIGNED");
      orderRepository.save(order);

    } catch (Exception ex) {

    }
    return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
  }

  @PutMapping("/user/order")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public ResponseEntity<List<OrderResponse>> assignOrder(@RequestParam("orderid") Long orderid,
      Authentication authentication) {
        Optional<Order> order = orderRepository.findById(orderid);
        if(order.isPresent()){
          Order updateOrder = order.get();
          updateOrder.setOrderStatus("Assigned");
          LocalDate deliverDate = LocalDate.now().plusDays(7);
          updateOrder.setDeliverDate(deliverDate);
          orderRepository.save(updateOrder);
        }

    return getAllOrders(authentication);
  }

  private String generateOrderNumber(String shopName) {
    // Generate a random 7-digit number
    int randomNumber = (int) (Math.random() * 9000000) + 1000000;

    // Combine shop name and random number
    String orderNumber = shopName +"_"+ randomNumber;

    return orderNumber;
  }

}
