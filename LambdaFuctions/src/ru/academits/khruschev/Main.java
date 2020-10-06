package ru.academits.khruschev;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
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

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueNamesList);

        String uniqueNamesString = persons.stream().map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Names: ", "."));

        System.out.println(uniqueNamesString);

        double youngerThan18PersonsAverageAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println(youngerThan18PersonsAverageAge);

        Map<String, Double> nameAverageAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println(nameAverageAge);

        persons.stream().filter(person -> person.getAge() >= 20 && person.getAge() < 45)
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .map(Person::getName)
                .forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type numbers amount to calculate and prints its squares: ");
        final int numbersAmount = scanner.nextInt();

        IntStream numbersSquare = IntStream.iterate(0, number -> number < numbersAmount, number -> number + 1)
                .map(number -> number * number);
        numbersSquare.forEach(System.out::println);

        System.out.println("Type fibonacci numbers amount you want to get: ");
        final int fibonacciNumbersAmount = scanner.nextInt();

        int[] twoFibonacciNumbers = new int[]{0, 1};
        boolean[] isThirdNumber = new boolean[]{true};

        IntStream fibonacciNumbers2 = IntStream
                .iterate(0, currentFibonacciNumber -> {
                    if (twoFibonacciNumbers[0] == 1 && twoFibonacciNumbers[1] == 1 && isThirdNumber[0]) {
                        isThirdNumber[0] = false;
                        return 1;
                    }

                    currentFibonacciNumber = twoFibonacciNumbers[0] + twoFibonacciNumbers[1];
                    twoFibonacciNumbers[0] = twoFibonacciNumbers[1];
                    twoFibonacciNumbers[1] = currentFibonacciNumber;

                    return currentFibonacciNumber;
                });

        fibonacciNumbers2.limit(fibonacciNumbersAmount).forEach(System.out::println);
    }
}


















