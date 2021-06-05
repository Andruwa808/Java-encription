package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Permutation {
    private Scanner in;

    public void HashPermutation(){
        System.out.println("Шифрування методом перестановки");
        System.out.println(" 1. Шифрування");
        System.out.println(" 2. Дешифрування");
        System.out.println(" 3. Повернутись в меню");
        System.out.println("");
        System.out.print("Введіть цифру: ");

        in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();

        switch (choice){
            case 1:
                System.out.println("Шифрування");
                cipherEncryption();
                Permutation permutation = new Permutation();
                permutation.HashPermutation();
                break;
            case 2:
                System.out.println("Дешифрування");
                cipherDecryption();
                Permutation permutation1 = new Permutation();
                permutation1.HashPermutation();
                break;
            case 3:
                Main main = new Main();
                main.init();
                break;
            default:
                System.out.println("Помилка. Введений пункт меню не існує.");
                Permutation permutation2 = new Permutation();
                permutation2.HashPermutation();
        }
    }

    private void cipherDecryption() {
        String alpa = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";;
        String reverseAlpa = "";

        for (int i = alpa.length()-1; i > -1; i--) {
            reverseAlpa += alpa.charAt(i);
        }

        System.out.print("Введіть шифроване повідомлення: ");
        String message = in.nextLine();
        in.nextLine();

        message = message.toUpperCase();

        String dencryText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32){
                dencryText += " ";
            } else {
                int count = 0;
                for (int j = 0; j < reverseAlpa.length(); j++) {
                    if (message.charAt(i) == reverseAlpa.charAt(j)){
                        dencryText += alpa.charAt(j);
                        break;
                    }
                }
            }
        }

        System.out.println("Дешифоване повідомлення: " + dencryText);
    }

    private void cipherEncryption() {
        String alpa = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
        String reverseAlpa = "";
        // reversing alphabets
        for (int i = alpa.length() - 1; i > -1; i--) {
            reverseAlpa += alpa.charAt(i);
        }

        System.out.print("Введіть повідомлення: ");
        String message = in.nextLine();
        in.nextLine();

        message = message.toUpperCase();

        String encryText = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == (char) 32) {
                encryText += " ";
            } else {
                int count = 0;
                for (int j = 0; j < alpa.length(); j++) {
                    if (message.charAt(i) == alpa.charAt(j)) {
                        encryText += reverseAlpa.charAt(j);
                        break;
                    }
                }
            }
        }

        System.out.println("Шифроване повідомлення: " + encryText);
        try (FileWriter writer = new FileWriter("cypher4.txt", false)) {
            writer.write(encryText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
