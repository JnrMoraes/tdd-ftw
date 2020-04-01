package com.tdd.user.entities

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

class UserTemplates implements TemplateLoader {
    public static String VALID = "valid user"
    public static String CREATED_USER = "created user"
    public static String INVALID_USER = "invalid user"

    @Override
    void load() {
        Fixture.of(User.class).addTemplate(VALID, new Rule(){
            {
                add("name", "John")
                add("document", "73485234478")
                add("errors", new ArrayList())
            }
        })

        Fixture.of(User.class).addTemplate(CREATED_USER).inherits(VALID, new Rule(){
            {
                add("id", 1L)
            }
        })

        Fixture.of(User.class).addTemplate(INVALID_USER, new Rule(){
            {
                add("name", "")
            }
        })

    }
}
