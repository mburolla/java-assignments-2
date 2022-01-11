package com.xpanxion.java.assignments.student1;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Worker1 {

    public void ex1() {
        var products = DataAccess.getProducts();
        Map<Integer, Department> departmentMap = DataAccess.getDepartments().stream()
                .collect(Collectors.toMap(Department::getId, Function.identity()));

        products.forEach(p -> p.setDepartmentName(departmentMap.get(p.getDepartmentId()).getName()));
        System.out.println(products);
    }

    public void ex2() {
        var products = DataAccess.getProducts();

        products.forEach(p -> p.setDepartmentName("N/A"));
        System.out.println(products);
    }

    public void ex3() {
        var products = DataAccess.getProducts();

        var result = products.stream().filter(p -> p.getPrice() >= 10.0);
        System.out.println(result.collect(Collectors.toList()));
    }
}
