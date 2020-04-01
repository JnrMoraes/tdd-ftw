package com.tdd.usecase

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.tdd.user.entities.User
import com.tdd.user.entities.UserTemplates
import com.tdd.user.gateways.UserGateway
import com.tdd.user.gateways.mongo.UserGatewayMongoImpl
import com.tdd.user.usecase.CreateUser
import spock.lang.Specification

class CreateUserSpec extends Specification {

    def setup() {
        FixtureFactoryLoader.loadTemplates("com.tdd")
    }

    UserGateway userGateway = Mock(UserGatewayMongoImpl)
    CreateUser createUser = new CreateUser(userGateway)

    def "Create user successfully"() {
        given: "a valid user"
        User user = Fixture.from(User.class).gimme(UserTemplates.VALID)

        when: "I call the method"
        User result = createUser.execute(user)

        then: "user gateway must be called"
        1 * userGateway.createUser(_ as User) >> {
            User userArg ->
                assert userArg == user
                return Fixture.from(User.class).gimme(UserTemplates.CREATED_USER)
        }

        and: "user must be created"
        result.id != null
    }

    def "Received some errors on create user"() {
        given: "invalid user"
        User user = Fixture.from(User.class).gimme(UserTemplates.INVALID_USER)

        when: "I call the method"
        User result = createUser.execute(user)

        then: "user gateway cannot be called"
        0 * userGateway.createUser(_ as User)

        and: "the errors message must be ok"
        !result.errors.isEmpty()
        result.errors.size() == 2
        result.errors[0].message == "name cannot be empty"
        result.errors[1].message == "document cannot be empty"
    }
}
