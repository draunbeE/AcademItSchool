package ru.academits.khruschev;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

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

//        Stream<Person> personsStream3 = persons.stream();
//
//        personsStream3.filter(person -> person.getAge() >= 20)
//                .filter(person -> person.getAge() < 45)
//                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
//                .forEach(person -> System.out.println(person.getName()));

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Type numbers amount to calculate and prints its squares: ");
//        final int numbersAmount = scanner.nextInt();
//
//        IntStream numbersSquare = IntStream.iterate(0, number -> number < numbersAmount, number -> ++number)
//                .map(number -> number * number);
//        numbersSquare.forEach(System.out::println);

        System.out.println("Type fibonacci numbers amount you want to get: ");
        final int fibonacciNumbersAmount = scanner.nextInt();
        AtomicInteger firstFibonacciNumber = new AtomicInteger();
        AtomicInteger secondFibonacciNumber = new AtomicInteger(1);
        AtomicInteger currentFibonacciNumber = new AtomicInteger();

        IntStream fibonacciNumbers = IntStream
                .iterate(1, fibonacciNumberIndex -> fibonacciNumberIndex <= fibonacciNumbersAmount,
                        fibonacciNumberIndex -> {
                            if (fibonacciNumberIndex == 1) {
                                currentFibonacciNumber.set(1);
                                return ++fibonacciNumberIndex;
                            }

                            currentFibonacciNumber.set(firstFibonacciNumber.get() + secondFibonacciNumber.get());
                            firstFibonacciNumber.set(secondFibonacciNumber.get());
                            secondFibonacciNumber.set(currentFibonacciNumber.get());

                            return ++fibonacciNumberIndex;
                        });
        fibonacciNumbers.forEach(fibonacciNumberIndex -> System.out.println(currentFibonacciNumber));
    }
}


















