/**
 * @since 2019-10-15
 */
module jigsaw.main {

    requires java.sql;
    requires java.base;
    requires java.annotation;

    requires jackson.annotations;

    requires static lombok;

    requires reactor.core;
    requires org.reactivestreams;

    requires spring.boot;
    requires spring.context;
    requires spring.web;
    requires spring.webflux;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.boot.autoconfigure;

    requires com.h2database;
    requires slf4j.api;

    exports org.gd.jigsaw;
}