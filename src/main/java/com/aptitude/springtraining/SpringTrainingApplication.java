package com.aptitude.springtraining;

import com.aptitude.springtraining.annotations.FakeComponent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTrainingApplication implements InitializingBean{

	@Autowired // will create instance of EMployee automatically
    //@FakeComponent
	private IEmployeeDao employee;

	public static void main(String[] args) {
		SpringApplication.run(SpringTrainingApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// called when bean is created and depenedencies are created (autowired done)
		employee.sayHello();
	}
}
