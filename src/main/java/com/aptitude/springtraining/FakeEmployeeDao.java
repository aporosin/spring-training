package com.aptitude.springtraining;

import com.aptitude.springtraining.annotations.FakeComponent;
import org.springframework.stereotype.Repository;

@Repository
@FakeComponent
public class FakeEmployeeDao implements IEmployeeDao {
    @Override
    public void sayHello() {
        System.out.println("Hello from Fake EMployee");
    }
}
