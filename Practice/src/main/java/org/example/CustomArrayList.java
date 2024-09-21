package org.example;

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




}
