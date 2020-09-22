package ru.academits.khruschev;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Konstantin", 22),
                new Person("Ivan", 24),
                new Person("Ivan", 6),
                new Person("Ivan", 45),
                new Person("Ivan", 18),
                new Person("Anna", 17),
                new Person("Petr", 28),
                new Person("Olga", 23),
                new Person("Olga", 50),
                new Person("Nikolay", 21),
                new Person("Oleg", 12),
                new Person("Oleg", 29),
                new Person("Natasha", 33),
                new Person("Nadejda", 38),
                new Person("Nadejda", 41),
                new Person("Nadejda", 15),
                new Person("Sergey", 22),
                new Person("Dasha", 22),
                new Person("Anton", 19)
        );

//        Stream<Person> personStream1 = persons.stream();
//
//        String uniqueNames = personStream1.map(Person :: getName)
//                .distinct()
//                .collect(Collectors.joining(", ", "Names: ", "."));
//
//        System.out.println(uniqueNames);
//
//        Stream<Person> personsStream2 = persons.stream();
//
//        Map<String, Double> map = personsStream2
//                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
//
//        System.out.println(map);

        Stream<Person> personsStream3 = persons.stream();

        personsStream3.filter(person -> person.getAge() >= 20)
                .filter(person -> person.getAge() < 45)
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .forEach(person -> System.out.println(person.getName()));
    }
}


















