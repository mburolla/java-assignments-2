package com.xpanxion.java.assignments.student8;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.*;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Worker8 {

    public void ex1() {
        System.out.println("EXERCISE 1:");

        Map<Integer, String> departmentsMap = DataAccess.getDepartments().stream().collect(Collectors.toMap(Department::getId, Department::getName));

        var prodList = DataAccess.getProducts();

        var updatedProdList = prodList.stream().map(p -> {
            p.setDepartmentName(departmentsMap.get(p.getDepartmentId()));
            return p;
        }).toList();

        System.out.println(updatedProdList);
    }

    public void ex2() {
        System.out.println("\nEXERCISE 2:");
        var prodList = DataAccess.getProducts();

        var newProdList = prodList.stream().map(p -> {
            p.setDepartmentName("N/A");
            return p;
        }).toList();

        System.out.println(newProdList);
    }

    public void ex3() {
        System.out.println("\nEXERCISE 3:");
        var prodList = DataAccess.getProducts();
        Predicate<Product> prods10OrOver = p -> p.getPrice() >= 10.00;
        Predicate<Product> electronicProducts = p -> p.getDepartmentId() == 1;

        var electronicsPrice10AndOver = prodList.stream().filter(electronicProducts.and(prods10OrOver)).toList();
        System.out.println(electronicsPrice10AndOver);
    }

    public void ex4() {
        System.out.println("\nEXERCISE 4:");
        var prodList = DataAccess.getProducts();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Predicate<Product> foodProducts = p -> p.getDepartmentId() == 2;

        var foodItemsTotalCost = prodList.stream()
                .filter(foodProducts)
                .collect(Collectors.summingDouble(Product::getPrice));

        var foodItemsTotalCostInDollars = formatter.format(foodItemsTotalCost);
        System.out.println(foodItemsTotalCostInDollars);
    }

    public void ex5() {
        System.out.println("\nEXERCISE 5:");
        var peopleList = DataAccess.getPeople();
        Predicate<Person> people3OrLess = p -> p.getId() <= 3;

        var peopleUpdated = peopleList.stream()
                .filter(people3OrLess)
                .map(p -> {
            p.setSsn(p.getSsn().substring(p.getSsn().length() - 4));
            return p;
        }).toList();

        System.out.println(peopleUpdated);
    }

    public void ex6() {
        System.out.println("\nEXERCISE 6:");
        var catList = DataAccess.getCats();
        catList.sort(Comparator.comparing(Cat::getName));
        System.out.println(catList);
    }

    public void ex7 () {
        System.out.println("\nEXERCISE 7:");
        var wordList = DataAccess.getWords().split(" ");
        var wordMap = Arrays.stream(wordList).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        var sortedWordMap = new TreeMap<String, Long>(wordMap);
        sortedWordMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }

    public void ex8 () {
        System.out.println("\nEXERCISE 8:");
        var peopleList = DataAccess.getPeople();
        var peopleNulled = peopleList.stream()
                .map(p -> {
                    p.setLastName(null);
                    p.setAge(0);
                    p.setSsn(null);
                    return p;
                }).toList();
        System.out.println(peopleNulled);
    }

    public void ex9() {
        System.out.println("\nEXERCISE 9:");
        var prodList = DataAccess.getProducts();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Predicate<Product> electronics = p -> p.getDepartmentId() == 1;

        var totalCostWithTariff = prodList.stream()
                .filter(electronics)
                .map(p -> {
                    p.setPrice((float) (p.getPrice() + 2.0));
                    return p;
                }).collect(Collectors.summingDouble(Product::getPrice));

        var totalCostWithTariffInDollars = formatter.format(totalCostWithTariff);
        System.out.println(totalCostWithTariffInDollars);
    }

    public void ex10() {
        System.out.println("\nEXERCISE 10:");
        var peopleList = DataAccess.getPeople();
        var catList = DataAccess.getCats();
        var personCatList = peopleList.stream()
                .map(p ->
                    new PersonCat(
                        p.getId(),
                        p.getFirstName(),
                        catList.stream().filter(c -> c.getId() == p.getId()).toList()))
                .toList();
        System.out.println(personCatList);
    }
}
