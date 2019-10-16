package org.gd.jigsaw.utils;

import lombok.NonNull;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @since 2019-10-16
 */
public final class ReactUtils {

    private ReactUtils() { throw new UnsupportedOperationException(); }

    public static <T> Flux<T> asFlux(@NonNull Callable<List<? extends T>> callable) {
        if (callable == null)
            return Flux.error(new NullPointerException());
        return Flux.create(fluxSink -> {
            try {
                List<? extends T> call = callable.call();
                if (call != null)
                    call.forEach(fluxSink::next);
                fluxSink.complete();
            } catch (Exception e) {
                fluxSink.error(e);
            }
        });
    }
}
