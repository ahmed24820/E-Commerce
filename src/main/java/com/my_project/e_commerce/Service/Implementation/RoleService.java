package com.my_project.e_commerce.Service.Implementation;

import com.my_project.e_commerce.Models.Role;
import com.my_project.e_commerce.Models.RoleEnum;
import com.my_project.e_commerce.Repos.RoleRepo;
import com.my_project.e_commerce.Repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleService implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepo repo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      this.loadRoles();
    }

    public void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> RoleDescriptionMap = Map.of(
                RoleEnum.USER, "Usual user for the application",
                RoleEnum.ADMIN, "The Administrator for application",
                RoleEnum.SUPER_ADMIN, "The SuperAdministrator for application"
        );
        Arrays.stream(roleNames).forEach((role) -> {
            Optional<Role> optionalRole = repo.findByName(role);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role RoleToCreate = new Role();
                RoleToCreate.setName(role);
                RoleToCreate.setDescription(RoleDescriptionMap.get(role));

                repo.save(RoleToCreate);
            });
        });
    }
}