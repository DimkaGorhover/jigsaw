package org.gd.jigsaw.ctrl;

import lombok.RequiredArgsConstructor;
import org.gd.jigsaw.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @since 2019-10-16
 */
@RestController
@RequiredArgsConstructor
class UserCtrl {

    private final UserService userService;

    @GetMapping("/users")
    Flux<UserService.User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    Flux<UserService.User> getUserById(@PathVariable("id") int id) {
        return userService.getById(id);
    }
}
