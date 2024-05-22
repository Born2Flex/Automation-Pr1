package com.project;

import java.security.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Main main = new Main();
        String message = "Hello world";
        main.testMessageDigest(message);
        main.testSecureRandom(message);

        System.out.println("\nCorrect implemention of hashCode()");
        Person person = new Person("John", 22);
        Person person2 = new Person("Paul", 33);
        Person person3 = new Person("John", 22);
        System.out.println(person + " hashCode: " + person.hashCode());
        System.out.println(person2 + " hashCode: " + person2.hashCode());
        System.out.println(person3 + " hashCode: " + person3.hashCode());

        System.out.println("person1 equals person2: " + person.equals(person2));
        System.out.println("person1 equals person3: " +person.equals(person3));
        Map<Person, Integer> map = new HashMap<>();
        map.put(person, 1);
        map.put(person3, 3);
        map.put(person2, 2);
        System.out.println("Map entries:");
        for (Map.Entry<Person, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("Accessing value by key: " + map.get(new Person("John", 22)));

        System.out.println("\nIncorrect implementation of hashCode() and equals()");
        Car car1 = new Car("Tesla", "Red", LocalDate.of(2020, 1, 1));
        Car car2 = new Car("BMW", "Black", LocalDate.of(2019, 1, 1));
        Car car3 = new Car("Tesla", "Red", LocalDate.of(2020, 1, 1));
        System.out.println(car1 + " hashCode: " + car1.hashCode());
        System.out.println(car2 + " hashCode: " + car2.hashCode());
        System.out.println(car3 + " hashCode: " + car3.hashCode());

        System.out.println("car1 equals car2: " + car1.equals(car2));
        System.out.println("car1 equals car3: " + car1.equals(car3));
        Map<Car, Integer> carMap = new HashMap<>();
        carMap.put(car1, 1);
        carMap.put(car3, 3);
        carMap.put(car2, 2);
        System.out.println("Map entries:");
        for (Map.Entry<Car, Integer> entry : carMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("Accessing value by key: " + carMap.get(new Car("Tesla", "Red", LocalDate.of(2020, 1, 1))));
    }

    private void testSecureRandom(String message) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        hashSecureRandom(secureRandom, message);
        secureRandom = SecureRandom.getInstance("DRBG");
        hashSecureRandom(secureRandom, message);
        secureRandom = SecureRandom.getInstance("Windows-PRNG");
        hashSecureRandom(secureRandom, message);
    }

    private void testMessageDigest(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        hashMessageDigest(md, message);
        md = MessageDigest.getInstance("SHA-1");
        hashMessageDigest(md, message);
        md = MessageDigest.getInstance("SHA-256");
        hashMessageDigest(md, message);
    }

    public void hashSecureRandom(SecureRandom secureRandom, String message) throws NoSuchAlgorithmException {
        secureRandom.setSeed(message.getBytes());
        byte[] result = new byte[16];
        secureRandom.nextBytes(result);
        System.out.println(bytesToHex(result));
    }

    public void hashMessageDigest(MessageDigest md, String message) {
        md.update(message.getBytes());
        System.out.println(bytesToHex(md.digest()));
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder out = new StringBuilder();
        for (byte b : bytes) {
            out.append(String.format("%02X", b));
        }
        return out.toString();
    }
}