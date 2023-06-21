package com.mjf.config;

import com.mjf.entity.Shop;
import com.mjf.entity.UserInfo;
import com.mjf.repository.UserInfoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements ApplicationRunner {

  private  UserInfoRepository userInfoRepository; // Replace with your repository interface

  @Autowired
  public DBInitializer(UserInfoRepository userInfoRepository) {
    this.userInfoRepository = userInfoRepository;
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    UserInfo ui = new UserInfo();
    ui.setEmail("admin@gmail.com");
    ui.setName("admin");
    ui.setRoles("ROLE_ADMIN");
    ui.setPassword("$2a$12$B4C.rNQNMt8EmGpqvf74SuE49xS14q9cECX9g6G2oT0bH29ZxQCfC");
    userInfoRepository.save(ui);
  }
}
