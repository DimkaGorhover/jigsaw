package org.gd.jigsaw.service;

import reactor.core.publisher.Flux;

/**
 * @since 2019-10-16
 */
public interface UserService {

    Flux<User> getAll();

    Flux<User> getById(int id);

    void add(CreateUser user);

    interface CreateUser {

        String getName();
    }

    interface User extends CreateUser {

        int getId();
    }
}
