package ru.stqa.selenium.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/negativeLoginIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();

        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> DpNegativePasswordIncorrect() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/negativePasswordIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();

        return userData.iterator();
    }

    //--- bad practice to keep test data inside a method
    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{
                "smth@test.com", "password", "There isn't an account for this email"
        });
        data.add(new Object[]{
                "smthtest", "psw2", "There isn't an account for this username"
        });
        data.add(new Object[]{
                "", "anypssword", "Missing email"
        });
        data.add(new Object[]{
                "12345@test.com", "", "There isn't an account for this email"
        });

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{
                    this.generateRandomName(), this.generateRandomPassword()
            });
        }

        return data.iterator();
    }

    //--- variant of dataProviderFirst
    @DataProvider
    public static Iterator<Object> dataProviderFourth() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/positiveNewEventTest.data")));

        List<Object> data = new ArrayList();
        String line = in.readLine();
        while (line != null) {
            data.add(line);
            line = in.readLine();
        }
        in.close();

        return data.iterator();
    }

    //--- variant of dataProviderThird
    @DataProvider
    public Iterator<Object> dataProviderFifth() {
        List<Object> data = new ArrayList();
        for (int i = 0; i < 3; ++i) {
            data.add(generateRandomCardTitle(2, 9)); // 2 to 9 characters exclusive
        }

        return data.iterator();
    }

    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {
        return "pass" + (new Random()).nextInt();
    }

    private Object generateRandomCardTitle(int minLength, int maxLength) {
        StringGenerator sg = new StringGenerator.StringGeneratorBuilder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .build();
        return sg.generateString(minLength, maxLength);
    }

}

