package com.xpanxion.java.assignments.student7;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.PersonCat;
import com.xpanxion.java.assignments.model.Product;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Worker7 {

    public void ex1() {
        var products = DataAccess.getProducts();
        var departments = DataAccess.getDepartments();

        Map<Integer, Department> departmentsMap = departments.stream().collect(Collectors.toMap(Department::getId, Function.identity()));

        var correctProducts = products.stream()
                .map(p -> {
                        p.setDepartmentName(departmentsMap.get(p.getDepartmentId()).getName());
                    return p;
                })
                .collect(Collectors.toList());
        System.out.println(correctProducts);
    }

    public void ex2() {

        var products = DataAccess.getProducts();
        var updateProducts = products.stream()
                .map(p -> {
                    p.setDepartmentName("N/A");
                    return p;
                })
                .collect(Collectors.toList());
        System.out.println(updateProducts);
    }

    public void ex3() {
        var products = DataAccess.getProducts();

        var filteredProducts = products.stream()
                .filter(product -> product.getDepartmentId()==1 && product.getPrice()>=10.00)
        .collect(Collectors.toList());
        System.out.println(filteredProducts);

    }

    public void ex4() {
        var products = DataAccess.getProducts();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        var foodTotals = products.stream()
                .filter(product -> product.getDepartmentId()==2)
                .mapToDouble(Product::getPrice).sum();

        System.out.println(formatter.format(foodTotals));
    }

    public void ex5() {
    var people = DataAccess.getPeople();

    var fixedSsn = people.stream()
            .filter(p -> p.getId()<=3)
            .map(p -> {
                var split = p.getSsn().split("-");
                p.setSsn(split[2]);
                return p;
            })
           .toList();
        System.out.println(fixedSsn);
    }

    public void ex6() {
        var cats = DataAccess.getCats();

        var catsSorted = cats.stream()
                .sorted(Comparator.comparing(Cat::getName))
                .toList();
        System.out.println(catsSorted);
    }

    public void ex7() {
        var words = DataAccess.getWords();
        var wordsMap = new HashMap<String, Integer>();
        var tokenizer = new StringTokenizer(words, " ");

        while (tokenizer.hasMoreElements()) {
            var token = tokenizer.nextToken();
            if (wordsMap.containsKey(token)) {
                wordsMap.put(token, wordsMap.get(token) +1);
            }
            else
                wordsMap.put(token, 1);
        }

        var sortedWordsMap = wordsMap.keySet()
                .stream()
                .sorted()
                .toList();

        var formattedWordsMap = sortedWordsMap.stream()
                .map(w -> (w + " = " + wordsMap.get(w)))
                .toList();

        for (String w : formattedWordsMap) {
            System.out.println(w);
        }
    }

    public void ex8() {
        var people = DataAccess.getPeople();

       var peopleNulled = people.stream()
                .map(w -> {
                    w.setSsn(null);
                    w.setLastName(null);
                    w.setAge(0);
                    return w;
                })
                .toList();

        System.out.println(peopleNulled);
    }

    public void ex9() {
    var products = DataAccess.getProducts();
    var format = NumberFormat.getCurrencyInstance();

        var totalPrice = products.stream()
                .filter(p -> p.getDepartmentId()==1)
                .map(p -> {
                   p.setPrice(p.getPrice() + 2.00F);
                   return p;
                })
                .mapToDouble(Product::getPrice).sum();

        System.out.println(format.format(totalPrice));
    }

    public void ex10() {
        var cats = DataAccess.getCats();
        var people = DataAccess.getPeople();

        var personCats = people.stream()
                .map(p -> {
                    var personCat = new PersonCat();
                    personCat.setId(p.getId());
                    personCat.setFirstName(p.getFirstName());
                    personCat.setCatList(cats.stream()
                            .filter(c -> c.getId()==p.getId())
                            .toList());
                    return personCat;
                })
                .toList();


        System.out.println(personCats);
    }

}
