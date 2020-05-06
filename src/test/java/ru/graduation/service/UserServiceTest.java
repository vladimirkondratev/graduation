package ru.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@RunWith(SpringRunner.class)
class UserServiceTest {

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getByEmail() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }
}