package com.gorichko.andrew;

import com.gorichko.andrew.encryptors.Hill;
import com.gorichko.andrew.encryptors.Permutation;
import com.gorichko.andrew.encryptors.Playfair;
import com.gorichko.andrew.encryptors.Wiegener;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        init();
    }

    public static void init(){

        Playfair playfair = new Playfair();
        Hill hill = new Hill();
        Wiegener wiegener = new Wiegener();
        Permutation permutation = new Permutation();

        System.out.println("Привіт! Доступні методи шифрування: ");

        System.out.println(" 1. Шифрування за алгоритмом Плейфера.");
        System.out.println(" 2. Шифруванння методом Хілла.");
        System.out.println(" 3. Шифрування за допомогою таблиці Віженера.");
        System.out.println(" 4. Шифрування із застусуванням методу перестановки.");
        System.out.println(" 5. Вихід");
        System.out.println("");

        System.out.print("Введіть цифру, яким методом бажаєте скористатись: ");
        Scanner scanner = new Scanner(System.in);
        int metod = scanner.nextInt();

        switch (metod) {
            case 1:
                playfair.HashPlayfair();
                break;
            case 2:
                hill.HashHill();
                break;
            case 3:
                wiegener.HashWiegener();
                break;
            case 4:
                permutation.HashPermutation();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Помилка. Цифра була введена не правильно.");
                Main main = new Main();
                main.init();
                break;
        }
    }
}
