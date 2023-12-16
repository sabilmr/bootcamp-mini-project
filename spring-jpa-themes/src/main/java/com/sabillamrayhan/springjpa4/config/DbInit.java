package com.sabillamrayhan.springjpa4.config;

import com.sabillamrayhan.springjpa4.entity.*;
import com.sabillamrayhan.springjpa4.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {
    private final CategoryRepo categoryRepo;
    private final AssessmentRepo assessmentRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;
    private final CountryRepo countryRepo;
    private final DistrictRepo districtRepo;
    private final SubDistrictRepo subDistrictRepo;
    private final ProvinceRepo provinceRepo;

    @Override
    public void run(String... args) throws Exception {
        log.info("Database init is called ...!");
        initCategory();
        //generate Assessment
        initAssessment();
        //generate role
        initRole();
        //generate user
        initUser();
        //generate country
        initCountry();
    }
    private void initRole(){
        if(roleRepo.count() > 0){
            return;
        }

        try {
            roleRepo.saveAll(List.of(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_SUPER_USER")
            ));
            log.info("Save role success..!");
        }catch (Exception e){
            log.error("Save role failed, error: {}", e.getMessage());
        }
    }

    private void initUser(){
        if(userRepo.count() > 0){
            return;
        }

        // admin
        RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN").orElse(null);
        if(adminRole != null){
            UserEntity admin = new UserEntity("Admin","User","admin@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(adminRole));
            try {
                userRepo.save(admin);
                log.info("Create admin role success..!");
            }catch (Exception e){
                log.error("Create admin role failed, Error: {}", e.getMessage());
            }
        }

        // user
        RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElse(null);
        if(adminRole != null){
            UserEntity user = new UserEntity("User","Role","user@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(userRole));
            try {
                userRepo.save(user);
                log.info("Create user role success..!");
            }catch (Exception e){
                log.error("Create user role failed, Error: {}", e.getMessage());
            }
        }

        // super user
        RoleEntity superUserRole = roleRepo.findByName("ROLE_SUPER_USER").orElse(null);
        if(superUserRole != null){
            UserEntity superUser = new UserEntity("Super","User","super.user@gmail.com", encoder.encode("P@ssW0rd32!"),List.of(superUserRole));
            try {
                userRepo.save(superUser);
                log.info("Create super user role success..!");
            }catch (Exception e){
                log.error("Create super user role failed, Error: {}", e.getMessage());
            }
        }
    }

    private void initAssessment(){
        if(assessmentRepo.count() > 0L){
            log.info("Assessment is exist..!");
            return;
        }

        CategoryEntity category = categoryRepo.findByName("CUSTOMER_ASSESSMENT").orElse(null);
        if(category == null){
            return;
        }
        AssessmentEntity profile = new AssessmentEntity("P0001","Profile", category);
        try{
            assessmentRepo.save(profile);
            log.info("Education save success");
        }catch (Exception e){
            log.error("Editucation save failed, error: {}", e.getMessage());
        }

        AssessmentEntity education = new AssessmentEntity("P0002","Pendidikan", profile, category);
        education.addOption(new AssessmentOptionEntity("SD",1.0, education));
        education.addOption(new AssessmentOptionEntity("SMP",1.0, education));
        education.addOption(new AssessmentOptionEntity("SMA",1.0, education));
        education.addOption(new AssessmentOptionEntity("D1",1.0, education));
        education.addOption(new AssessmentOptionEntity("D2",1.0, education));
        education.addOption(new AssessmentOptionEntity("D3",1.0, education));
        education.addOption(new AssessmentOptionEntity("D4",1.0, education));
        education.addOption(new AssessmentOptionEntity("S1",1.0, education));
        education.addOption(new AssessmentOptionEntity("S2",1.0, education));
        education.addOption(new AssessmentOptionEntity("S3",1.0, education));

        AssessmentEntity religion = new AssessmentEntity("P0003","Agama", profile, category);
        religion.addOption(new AssessmentOptionEntity("ISLAM",1.0, religion));
        religion.addOption(new AssessmentOptionEntity("KHATOLIK",1.0, religion));
        religion.addOption(new AssessmentOptionEntity("KRISTEN",1.0, religion));
        religion.addOption(new AssessmentOptionEntity("HINDU",1.0, religion));
        religion.addOption(new AssessmentOptionEntity("BUDHA",1.0, religion));
        religion.addOption(new AssessmentOptionEntity("KONG HU CHU",1.0, religion));

        AssessmentEntity gd = new AssessmentEntity("P0004","Golongan Darah", profile, category);
        gd.addOption(new AssessmentOptionEntity("A",1.0, gd));
        gd.addOption(new AssessmentOptionEntity("B",1.0, gd));
        gd.addOption(new AssessmentOptionEntity("AB",1.0, gd));
        gd.addOption(new AssessmentOptionEntity("O",1.0, gd));

        try{
            List<AssessmentEntity> assessments = Arrays.asList(education, religion, gd);
            this.assessmentRepo.saveAll(assessments);
            log.info("Save All assessment success..!");
        }catch (Exception e){
            log.error("Save All assessment failed, error: {}", e.getMessage());
        }
    }

    private void initCategory(){
        if(categoryRepo.count() > 0L){
            log.info("Category is exist..!");
            return;
        }

        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(new CategoryEntity(0L,"CUSTOMER_ASSESSMENT"));
        categories.add(new CategoryEntity(0L,"EMPLOYEE_ASSESSMENT"));
        categories.add(new CategoryEntity(0L,"PRODUCT_ASSESSMENT"));

        try{
            this.categoryRepo.saveAll(categories);
            log.info("Category save successfully");
        }catch (Exception e){
            log.error("Category save is failed, error: {}", e.getMessage());
        }
    }

    private void initCountry(){
        if (countryRepo.count() > 0L){
            return;
        }

        try {
            countryRepo.saveAll(Arrays.asList(
                    new CountryEntity(0L, "ID", "Indonesia"),
                    new CountryEntity(0L,"MY","Malaysia")
            ));
        }catch (Exception e){
            log.error("Save country failed, error: {}",e.getMessage());
        }

        if (provinceRepo.count() == 0L){
            CountryEntity indo = countryRepo.findByCode("ID").orElse(null);
            try {
                provinceRepo.saveAll(Arrays.asList(
                        new ProvinceEntity(indo,"P001","Aceh"),
                        new ProvinceEntity(indo,"P002","Sumatra Barat"),
                        new ProvinceEntity(indo,"P003","Sumatra Utara"),
                        new ProvinceEntity(indo,"P004","Sumatra Selatan"),
                        new ProvinceEntity(indo,"P005","Lampung"),
                        new ProvinceEntity(indo,"P006","Jakarta"),
                        new ProvinceEntity(indo,"P007","Jawa Barat"),
                        new ProvinceEntity(indo,"P008","Jawa Tengah"),
                        new ProvinceEntity(indo,"P009","Jawa Timur"),
                        new ProvinceEntity(indo,"P010","Kalimantan Barat")
                ));
            }catch (Exception e){
                log.error("Save province failed, error: {}",e.getMessage());
            }
        }
        if (districtRepo.count() == 0L){
            ProvinceEntity aceh = provinceRepo.findByCode("P001").orElse(null);
            List<DistrictEntity> acehDistrict = Arrays.asList(
                    new DistrictEntity(aceh, "AC001", "Aceh Distric 01"),
                    new DistrictEntity(aceh, "AC001", "Aceh Distric 02"),
                    new DistrictEntity(aceh, "AC001", "Aceh Distric 03")
            );

            ProvinceEntity jakarta = provinceRepo.findByCode("P006").orElse(null);
            List<DistrictEntity> jakartaDistrict = Arrays.asList(
                    new DistrictEntity(jakarta, "JKT001", "Jakarta utara"),
                    new DistrictEntity(jakarta, "JKT002", "Jakarta Barat"),
                    new DistrictEntity(jakarta, "JKT003", "Jakarta Timur"),
                    new DistrictEntity(jakarta, "JKT004", "Jakarta Selatan"),
                    new DistrictEntity(jakarta, "JKT005", "Jakarta Pusat")
            );

            List<DistrictEntity> districts = new ArrayList<>();
            districts.addAll(acehDistrict);
            districts.addAll(jakartaDistrict);

            try {
                districtRepo.saveAll(districts);
            }catch (Exception e){
                log.error("Save district failed, error: {}",e.getMessage());
            }
        }

        if (subDistrictRepo.count() == 0L){
            DistrictEntity jkt01 = districtRepo.findByCode("JKT001").orElse(null);
            DistrictEntity jkt02 = districtRepo.findByCode("JKT002").orElse(null);
            DistrictEntity jkt03 = districtRepo.findByCode("JKT003").orElse(null);

            List<SubDistrictEntity> subJkt01 = Arrays.asList(
                    new SubDistrictEntity(jkt01, "SUBJKT001","Sub District JKT 01"),
                    new SubDistrictEntity(jkt01, "SUBJKT002","Sub District JKT 02"),
                    new SubDistrictEntity(jkt01, "SUBJKT003","Sub District JKT 03"),
                    new SubDistrictEntity(jkt02, "SUBJKT004","Sub District JKT 04"),
                    new SubDistrictEntity(jkt02, "SUBJKT005","Sub District JKT 05"),
                    new SubDistrictEntity(jkt02, "SUBJKT006","Sub District JKT 06"),
                    new SubDistrictEntity(jkt03, "SUBJKT007","Sub District JKT 07"),
                    new SubDistrictEntity(jkt03, "SUBJKT008","Sub District JKT 08")
            );

            try {
                subDistrictRepo.saveAll(subJkt01);
            }catch (Exception e){
                log.error("Save sub district error: {}", e.getMessage());
            }
        }
    }
}
