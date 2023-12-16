package com.bootcamp.northwind.config;

import com.bootcamp.northwind.model.entity.LookUpEntity;
import com.bootcamp.northwind.model.entity.RoleEntity;
import com.bootcamp.northwind.model.entity.UserEntity;
import com.bootcamp.northwind.repository.RoleRepo;
import com.bootcamp.northwind.repository.UserRepo;
import com.bootcamp.northwind.service.LookUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;
   private final LookUpService lookUpService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Database init is called ...!");
        //generate role
        initRole();
        //generate user
        initUser();

        initLookUp();



    }

    private void initLookUp() {
       if (lookUpService.getByGroups("CATEGORY").isEmpty()){
           lookUpService.saveAll(Arrays.asList(
                   new LookUpEntity("CATEGORY", "MAKANAN", "Makanan", 1),
                   new LookUpEntity("CATEGORY", "MINUMAN", "Minuman", 2),
                   new LookUpEntity("CATEGORY", "SNACK", "Snack", 3),
                   new LookUpEntity("CATEGORY", "ELEKTRONIK", "Elektronik", 4),
                   new LookUpEntity("CATEGORY", "AKSESORIS", "Aksesoris", 5)
           ));
       }
    }

       private void initRole() {
        if (roleRepo.count() > 0) {
            return;
        }

        try {
            roleRepo.saveAll(List.of(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_SUPER_USER")
            ));
            log.info("Save role success..!");
        } catch (Exception e) {
            log.error("Save role failed, error: {}", e.getMessage());
        }
    }

    private void initUser() {
        if (userRepo.count() > 0) {
            return;
        }

        // admin
        RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN").orElse(null);
        if (adminRole != null) {
            UserEntity admin = new UserEntity("Admin", "User", "admin@gmail.com", encoder.encode("P@ssW0rd32!"), List.of(adminRole));
            try {
                userRepo.save(admin);
                log.info("Create admin role success..!");
            } catch (Exception e) {
                log.error("Create admin role failed, Error: {}", e.getMessage());
            }
        }

        // user
        RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElse(null);
        if (adminRole != null) {
            UserEntity user = new UserEntity("User", "Role", "user@gmail.com", encoder.encode("P@ssW0rd32!"), List.of(userRole));
            try {
                userRepo.save(user);
                log.info("Create user role success..!");
            } catch (Exception e) {
                log.error("Create user role failed, Error: {}", e.getMessage());
            }
        }

        // super user
        RoleEntity superUserRole = roleRepo.findByName("ROLE_SUPER_USER").orElse(null);
        if (superUserRole != null) {
            UserEntity superUser = new UserEntity("Super", "User", "super.user@gmail.com", encoder.encode("P@ssW0rd32!"), List.of(superUserRole));
            try {
                userRepo.save(superUser);
                log.info("Create super user role success..!");
            } catch (Exception e) {
                log.error("Create super user role failed, Error: {}", e.getMessage());
            }
        }
    }
}
