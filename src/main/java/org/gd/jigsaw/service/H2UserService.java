package org.gd.jigsaw.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.gd.jigsaw.utils.ReactUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import reactor.core.publisher.Flux;

import static java.util.Objects.requireNonNull;

/**
 * @since 2019-10-16
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class H2UserService implements UserService {

    private final JdbcOperations jdbcTemplate;

    @lombok.Builder(builderClassName = "Builder", toBuilder = true)
    private H2UserService(@NonNull JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flux<User> getAll() {
        return ReactUtils.asFlux(() -> jdbcTemplate.query(
                "SELECT u.id, u.name FROM users u",
                UserImpl.ROW_MAPPER));
    }

    @Override
    public Flux<User> getById(int id) {
        return ReactUtils.asFlux(() -> jdbcTemplate.query(
                "SELECT u.id, u.name FROM users u WHERE id = ?",
                ps -> ps.setInt(1, id),
                UserImpl.ROW_MAPPER));
    }

    @Override
    public void add(@NonNull CreateUser user) {
        requireNonNull(user, "\"user\" cannot be null");
        jdbcTemplate.update("INSERT INTO users (name) values(?)", user.getName());
    }

    @RequiredArgsConstructor
    @ToString(doNotUseGetters = true)
    @EqualsAndHashCode(doNotUseGetters = true)
    private static class UserImpl implements User {

        private static final RowMapper<User> ROW_MAPPER = (rs, rowNum) ->
                new UserImpl(rs.getInt("id"), rs.getString("name"));

        private final int id;
        private final String name;

        @JsonProperty("id")
        @Override
        public int getId() { return id; }

        @JsonProperty("name")
        @Override
        public String getName() { return name; }
    }
}
