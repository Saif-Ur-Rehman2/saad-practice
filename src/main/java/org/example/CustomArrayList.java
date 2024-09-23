package org.example;

import java.util.Arrays;

public class CustomArrayList {
    Integer[] array = new Integer[10];

    public Boolean contains(Integer number){
        for(int i = 0; i < array.length;i++){
            if(array[i] == number){
                return true;
            }
        }
        return false;
    }

    public void add(Integer number){
        for(int i = 0; i < array.length; i++) {
            if (array[i] == null && i < array.length) {
                array[i] = number;
                break;
            } else {

                Integer[] tempArray = new Integer[array.length * 2];
                tempArray[i] = array[i];
                array = new Integer[(array.length * 2)];
                array[i] = tempArray[i];
                if (array[i] == null) {
                    array[i] = number;
                }
            }
            System.out.println(array[i]);
            System.out.println(array.length);
        }
    }

    public void addNumber(Integer number){
        if(array[array.length-1] == null){
            for(int i = 0; i < array.length; i++){
                if(array[i] == null){
                    array[i] = number;
                    break;
                }

            }
        }
        else{
            Integer[] tempArray = new Integer[array.length * 2];
//            array = new Integer[array.length * 2];
            for(int i = 0; i < array.length; i++){
                tempArray[i] = array[i];

            }
            tempArray[array.length] = number;
            array = tempArray;
        }

    }

    public void print(){
        System.out.println(Arrays.asList(array));
    }


    @Override
    public String toString() {
        return "CustomArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
