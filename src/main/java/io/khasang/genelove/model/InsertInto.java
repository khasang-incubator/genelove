package io.khasang.genelove.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class InsertInto {
    private JdbcTemplate jdbcTemplate;

    public InsertInto(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public InsertInto() {

    }

    private String insert() {
        try {
            jdbcTemplate.execute("INSERT INTO films (code, title, did, date_prod, kind, len) values ('1'," +
                    " 'Изгой-один: Звёздные войны', 'Гарет Эдвардс', '2016.01.01', 'фантастика','02:13')");
            return "Table created";
        } catch (Exception e) {
            return "Table creation failed: " + e;
        }
    }

    public String insertIntoStatus () {
        return insert();
    }
}
