package com.xinwu.datastructure.chapter5.quadraticProbe;

public class QuadraticProbingHashTableApp {
    public static void main(String[] args) {
        QuadraticProbingHashTable<String> hashTable = new QuadraticProbingHashTable<>();
        hashTable.insert("lebron");
        hashTable.insert("wade");
        hashTable.insert("paul");
    }
}