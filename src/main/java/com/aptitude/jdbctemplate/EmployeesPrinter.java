package com.aptitude.jdbctemplate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class EmployeesPrinter implements InitializingBean{

    @Autowired
    private JdbcTemplate jdbc; // will be instantiated based on database config in yaml

    @Autowired
    private NamedParameterJdbcTemplate  namedJdbc;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Employee> query = jdbc.query("SELECT * from employees", new BeanPropertyRowMapper<>(Employee.class));
        System.out.println(query);

        // quick query for one column
        jdbc.queryForList("SELECT name from employees", String.class).forEach(System.out::println);

        System.out.println("=============== min salary ================");
        int minSalary = 6100;
        jdbc.query("Select * from employees where salary >= ?",
                new Object[] {minSalary},
                new BeanPropertyRowMapper<>(Employee.class))
                .forEach(System.out::println);

        System.out.println("======= Named JDBC ===============");
        namedJdbc.query("SELECT * from employees where salary >= :minSalary",
                        new HashMap<String, Object>() { // anonimowa klasa
                            { // inicjalizator instancji
                                put("minSalary", minSalary);
                            }
                        },
                        new BeanPropertyRowMapper<>(Employee.class))
                        .forEach(System.out::println);

        // or instead of HashMap magic:

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedJdbc.update("insert into employees (name, position, salary) values (:name, :position, :salary)",
                new MapSqlParameterSource()
                .addValue("name", "testowy jan")
                .addValue("position", "dev")
                .addValue("salary", 1500),
                generatedKeyHolder);

        System.out.println("Inserted id : " + generatedKeyHolder.getKey());

        // Or even better with
        Employee newEmployee = new Employee();
        newEmployee.setName("Hania Nowa");
        newEmployee.setPosition("Accountant");
        newEmployee.setSalary(10000);

        namedJdbc.update("insert into employees (name, position, salary) values (:name, :position, :salary)",
                new BeanPropertySqlParameterSource(newEmployee));

        // Read again to verify
        jdbc.queryForList("SELECT name from employees", String.class).forEach(System.out::println);

    }

    // or use AutoValue library for generating class from method
    public static class Employee {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", position='" + position + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        private String position;
        private int salary;
    }
}
