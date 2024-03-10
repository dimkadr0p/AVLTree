package org.khachidze;


import org.khachidze.enums.Mode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        AVLTree avlTree = new AVLTree();

        List<String> inputStrings = Files.readAllLines(Paths.get("Path"));

        for (String str : inputStrings) {
            avlTree.insert(str);
        }

        System.out.println("первый узел в дереве:"  + avlTree.findFirst().data);

        System.out.println("последний узел в дереве:" + avlTree.findLast().data);

        List<String> greaterSet = avlTree.query("g", Mode.GREATER);
        System.out.println("Строки, больше 'g': " + greaterSet);

        List<String> lessSet = avlTree.query("g", Mode.LESS);
        System.out.println("Строки, меньше 'g': " + lessSet);

        List<String> equalSet = avlTree.query("abc", Mode.EQUAL);
        System.out.println("Строка, равная 'abc': " + equalSet);

        avlTree.display();

        for (String str: avlTree) {
            System.out.println(str);
        }

    }
}
