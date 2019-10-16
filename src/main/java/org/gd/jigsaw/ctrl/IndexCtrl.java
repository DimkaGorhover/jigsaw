package org.gd.jigsaw.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @since 2019-10-16
 */
@RestController
class IndexCtrl {

    private final int port;

    IndexCtrl(@Value("${server.port}") int port) {
        this.port = port;
    }

    @SuppressWarnings("unused")
    @GetMapping(value = "/", produces = "text/plain")
    Flux<String> index() {
        return Flux.just("Hello World! on port:  " + port);
    }
}
