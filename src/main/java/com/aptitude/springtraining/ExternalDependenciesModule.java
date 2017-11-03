package com.aptitude.springtraining;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Configuration
//@Component -> tez tutaj zadziała
public class ExternalDependenciesModule {

    @Bean // dostarcza implementacje Supplier<Long> w miejscach gdzie jest autowire czyli np konstruktorze Employee bedzi eto uzyte
    public Supplier<Long> timestampProvider()
    {
        return new SystemTimestampProvider();
    }

//    @Bean
//    @Primary
    public IEmployeeDao getEmployee(Supplier<Long> s){
        EmployeeDAO a =  new EmployeeDAO(s);
        a.setId(33);
        return a;
    }

    public static class SystemTimestampProvider implements Supplier<Long>
    {

        @Override
        public Long get() {
            return System.currentTimeMillis();
        }
    }
}
