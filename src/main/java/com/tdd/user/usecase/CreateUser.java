package com.tdd.user.usecase;

import com.tdd.user.entities.Error;
import com.tdd.user.entities.User;
import com.tdd.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateUser {

    private final UserGateway userGateway;

    public User execute(final User user) {
         final List<Error> errorList = new ArrayList<>();

         if (StringUtils.isBlank(user.getName())) {
             errorList.add(buildError("name cannot be empty"));
         }

         if (StringUtils.isBlank(user.getDocument())) {
             errorList.add(buildError("document cannot be empty"));
         }

         if (errorList.isEmpty()) {
            return userGateway.createUser(user);
         } else {
             user.setErrors(errorList);
             return user;
         }
    }

    private Error buildError(String message) {
        return new Error(message);
    }
}
