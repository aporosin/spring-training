package com.aptitude.springtraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Component // cos co da sie wstrzyknac do kontenera zaleznosci
//@Service - rozszerzenie COmponentu
//@Repository - podobne do komponenty
@Primary
public class EmployeeDAO implements IEmployeeDao{

    private final Supplier<Long> jdbcTemplate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    @Autowired
    public EmployeeDAO(Supplier<Long> template)
    {
        jdbcTemplate = template;
        id = 22;
    }

    public void sayHello() {
        System.out.println("Hello world form Employee " + id + " " + jdbcTemplate.get() );
    }

}
