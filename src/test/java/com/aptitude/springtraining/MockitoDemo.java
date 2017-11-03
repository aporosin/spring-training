package com.aptitude.springtraining;

import com.aptitude.jdbctemplate.EmployeesPrinter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class) -> wypelni nam @Mock i @Captor jesli nei tutaj ma spring runnera
// w innym pryzpadku uzyc @Before (bo nie mozna miec dwoch @RunWith
public class MockitoDemo {

    @Mock
    private EmployeesPrinter.Employee mockObject;

    @Captor
    private ArgumentCaptor<String> argumentCaptor;

    // alternative for RunWith
    @Before
    public void setup()
    {
         // wypelni pola @Mock i @Captor w klasie
        // czyli zrobi pole: EmployeesPrinter.Employee mockObject = mock(Employee.class)
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMocks(){

        //given
        HashMap<EmployeesPrinter.Employee, EmployeesPrinter.Employee> hashMap = new HashMap<>();
        EmployeesPrinter.Employee employee = mock(EmployeesPrinter.Employee.class);
        // or alternative:
        EmployeesPrinter.Employee employee1 = spy(new EmployeesPrinter.Employee()); // and other verifications work as well

        // when
        //hashMap.put(employee, employee);
        employee.setName("Ada");

        //then
        Mockito.
        verify(employee).setName(any(String.class));
        //verify(employee, Mockito.times(2)).setName(any(String.class));
        //verify(employee, Mockito.times(1)).hashCode();

        // configure return value
        when(employee.getName()).thenReturn("Ada");

        // configure parameters
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(employee).setName(argumentCaptor.capture());

        Assert.assertEquals(argumentCaptor.getValue(), "Ada");

    }
}
