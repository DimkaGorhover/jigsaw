package org.gd.jigsaw.service;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * @since 2019-10-16
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class LoggedUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(LoggedUserService.class);

    private final UserService userService;

    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    private LoggedUserService(@NonNull UserService userService) {
        this.userService = userService;
    }

    @Override
    public Flux<User> getAll() {
        return userService.getAll()
                .doOnSubscribe(subscription -> LOG.debug("[getAll]: Subscribe"))
                .doOnComplete(() -> LOG.debug("[getAll]: Complete"));
    }

    @Override
    public Flux<User> getById(int id) {
        return userService.getById(id)
                .doOnSubscribe(subscription -> LOG.debug("[getById]: Subscribe"))
                .doOnComplete(() -> LOG.debug("[getById]: Complete"));
    }

    @Override
    public void add(CreateUser user) {
        LOG.debug("[add]: Subscribe");
        userService.add(user);
        LOG.debug("[add]: Complete");
    }
}
