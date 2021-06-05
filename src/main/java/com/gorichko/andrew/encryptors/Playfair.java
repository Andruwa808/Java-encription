package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Playfair {
    private Scanner in;

    public void HashPlayfair(){
        System.out.println("Метод шифрування Плейфейра");
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
                Playfair playfair = new Playfair();
                playfair.HashPlayfair();
                break;
            case 2:
                System.out.println("Дешифрування");
                cipherDecryption();
                Playfair playfair1 = new Playfair();
                playfair1.HashPlayfair();
                break;
            case 3:
                Main main = new Main();
                main.init();
                break;
            default:
                System.out.println("Помилка. Введений пункт меню не існує.");
                Playfair playfair2 = new Playfair();
                playfair2.HashPlayfair();
        }

    }

    private void cipherDecryption() {
        System.out.print("Шифроване повідомлення: ");
        String message = in.nextLine();
        message = message.replaceAll("\\s","");
        in.nextLine();

        System.out.print("Введіть ключ: ");
        int key = in.nextInt();
        in.nextLine();

        char[][] rail = new char[key][message.length()];

        for (int i = 0; i < key; i++){
            for (int j = 0; j < message.length(); j++) {
                rail[i][j] = '.';
            }
        }

        // testing rail
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < message.length(); j++) {
                System.out.print(rail[i][j]);
            }
            System.out.println();
        }

        int row = 0;
        int check = 0;
        for (int i = 0; i < message.length(); i++) {
            if (check == 0){
                rail[row][i] = message.charAt(i);
                row++;
                if (row == key){
                    check = 1;
                    row--;
                }
            } else if(check == 1){
                row--;
                rail[row][i] = message.charAt(i);
                if (row == 0){
                    check = 0;
                    row = 1;
                }
            }
        }

        int ordr = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < message.length(); j++) {
                String temp = rail[i][j] + "";
                if (temp.matches("\\.")){
                    continue;
                } else {
                    rail[i][j] = message.charAt(ordr);
                    ordr++;
                }
            }
        }

        System.out.println("Змінюємо порядок");
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < message.length(); j++) {
                System.out.print(rail[i][j]);
            }
            System.out.println();
        }

        String decrypText = "";
        check = 0;
        row = 0;
        for (int i = 0; i < message.length(); i++) {
            if (check == 0){
                decrypText += rail[row][i];
                row++;
                if(row == key){
                    check = 1;
                    row--;
                }
            } else if (check == 1){
                row--;
                decrypText += rail[row][i];
                if(row == 0){
                    check = 0;
                    row = 1;
                }
            }
        }
        System.out.println("Дешифроване повідомлення: " + decrypText);
    }

    private void cipherEncryption() {
        System.out.print("Введіть повідомлення: ");
        String message = in.nextLine();
        message = message.replaceAll("\\s","");
        in.nextLine();

        System.out.print("Введіть ключ: ");
        int key = in.nextInt();
        in.nextLine();

        char[][] rail = new char[key][message.length()];
        for (int i = 0; i < key; i++){
            for (int j = 0; j < message.length(); j++) {
                rail[i][j] = '.';
            }
        }

        int row = 0;
        int check = 0;
        for (int i = 0; i < message.length(); i++) {
            if (check == 0){
                rail[row][i] = message.charAt(i);
                row++;
                if (row == key){
                    check = 1;
                    row--;
                }
            } else if(check == 1){
                row--;
                rail[row][i] = message.charAt(i);
                if (row == 0){
                    check = 0;
                    row = 1;
                }
            }
        }

        String encrypText = "";
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < message.length(); j++) {
                encrypText += rail[i][j];
            }
        }

        encrypText = encrypText.replaceAll("\\.","");
        System.out.println("Шифроване повідомлення: " + encrypText);

        try (FileWriter writer = new FileWriter("cypher1.txt", false)) {
            writer.write(encrypText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}