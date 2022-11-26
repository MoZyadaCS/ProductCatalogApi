package com.fawry.assignment.productcatalog.service;

import com.fawry.assignment.productcatalog.model.Login;
import com.fawry.assignment.productcatalog.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login addLogin(Login login) {
        return this.loginRepository.save(login);
    }
}
