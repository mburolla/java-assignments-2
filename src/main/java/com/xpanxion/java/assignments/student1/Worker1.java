package com.xpanxion.java.assignments.student1;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Product;

import javax.xml.crypto.Data;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    public void ex4() {
        var products = DataAccess.getProducts();
        Optional<Float> total;
        NumberFormat fomatter = NumberFormat.getCurrencyInstance();
        String moneyString;

        Map<Integer, Department> departmentMap = DataAccess.getDepartments().stream()
                .collect(Collectors.toMap(Department::getId, Function.identity()));

        total = products.stream()
                .peek(product -> product.setDepartmentName(departmentMap.get(product.getDepartmentId()).getName()))
                .filter(p -> Objects.equals(p.getDepartmentName(), "Food"))
                .map(Product::getPrice)
                .reduce(Float::sum);

        moneyString = fomatter.format(total.get());
        System.out.println(moneyString);
    }
}
