package com.example.sweathouse.security.registration;

import com.example.sweathouse.database.appuser.AppUserRole;
import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.security.registration.inter.RegistrationService;
import com.example.sweathouse.security.service.AppUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImplementation implements RegistrationService {

    private final AppUserService appUserService;

    public RegistrationServiceImplementation(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {
        appUserService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
