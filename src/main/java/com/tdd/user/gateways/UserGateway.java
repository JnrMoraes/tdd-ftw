package com.tdd.user.gateways;

import com.tdd.user.entities.User;

public interface UserGateway {
    User createUser(final User user);
}
