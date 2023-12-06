package com.java.features.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class City {
    private String name;
    private double temprerature;

    public City(String nm,double temp){
        name=nm;
        temprerature=temp;
    }

    public double getTemprerature() {
        return temprerature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemprerature(double temprerature) {
        this.temprerature = temprerature;
    }
    @Override
    public int hashCode(){
        return Objects.hash(name, temprerature);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj ==this) return  true;
        return obj instanceof City &&
               this.temprerature == ((City) obj).getTemprerature() ;

    }

    public  static List<City> prepareCities(){
        List<City> ls = List.of(
        new City("New Delhi", 33.5), new City("Mexico", 14),
        new City("New York", 13), new City("Dubai", 43),
        new City("London", 15), new City("Alaska", 1),
        new City("Kolkata", 30),
        new City("Sydney", 11), new City("Mexico", 14),
        new City("Dubai", 43));
        List<City> cities = new ArrayList<>(ls);
        return cities;
    }
    public static Supplier<Stream<City>> getCityStreams(){
        MySupplier<Stream<City>> streamSup = ()->Stream.of(
                new City("New Delhi", 33.5), new City("Mexico", 14),
                new City("New York", 13), new City("Dubai", 43),
                new City("London", 15), new City("Alaska", 1),
                new City("Kolkata", 30),
                new City("Sydney", 11), new City("Mexico", 14),
                new City("Dubai", 43));
        return streamSup;
    }

}
