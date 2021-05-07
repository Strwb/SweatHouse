package com.example.sweathouse.security.registration;

import com.example.sweathouse.database.appuser.AppUserRole;
import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.services.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

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
