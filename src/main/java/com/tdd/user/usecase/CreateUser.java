package com.tdd.user.usecase;

import com.tdd.user.entities.User;
import com.tdd.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUser {

    private final UserGateway userGateway;

    public User execute(final User user) {

        return userGateway.createUser(user);

    }

}
