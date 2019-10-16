package org.gd.jigsaw.service;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import reactor.core.publisher.Flux;

/**
 * @since 2019-10-16
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class TimedUserService implements UserService {

    private final UserService userService;

    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    private TimedUserService(@NonNull UserService userService) {
        this.userService = userService;
    }

    @Override
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @Override
    public Flux<User> getById(int id) {
        return userService.getById(id);
    }

    @Override
    public void add(CreateUser user) {
        userService.add(user);
    }
}
