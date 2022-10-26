package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение. Например 6+2; X-III");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        String[] actions = {"/", "+", "*", "-"};
        String[] actions2 = new String[]{"/", "\\+", "\\*", "-"};
        int index = -1;
        int i = 0;
        while (index < 0 && i < 4) {
            index = data.indexOf(actions[i]);
            i++;
        }

        if (index == -1) {
            System.out.println("Неверное выражение");
            return;
        }
        String separation = actions2[i - 1];
        String[] subStr;
        subStr = data.split(separation);

        int index2 = -1;
        i = 0;
        while (index2 < 0 && i < 4) {
            index2 = subStr[1].indexOf(actions[i]);
            i++;
        }
        if (index2 != -1 | subStr.length > 2) {
            System.out.print("Неверно: должно быть два операнда и один оператор (+, -, /, *), например 5+7");
            return;
        }

        char action;
        action = data.charAt(index);

        int A, B;

        boolean[] h = new boolean[subStr[0].length()];
        boolean[] h1 = new boolean[subStr[1].length()];
        for (int p = 0; p < subStr[0].length(); p++) {
            h[p] = Character.isDigit(subStr[0].charAt(p));
        }
        for (int p = 0; p < subStr[1].length(); p++) {
            h1[p] = Character.isDigit(subStr[1].charAt(p));
        }
        int R = 0;
        try {
            if (checkArray(h)&&checkArray(h1)){
                A = Integer.parseInt(subStr[0]);
                B = Integer.parseInt(subStr[1]);
            } else {
                A = Roman.valueOf(subStr[0]).toInt();
                B = Roman.valueOf(subStr[1]).toInt();
                R = 1;
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Неправильный формат чисел");
            return;
        }

        if ( A < 1 | A > 10 | B < 1 | B > 10){
            System.out.println("Число FAILED. Вы вышли за диопазон чисел");
            return;
        };

        int result = 0;
        switch (action) {
            case ('/'):
                result = A / B;
                break;
            case ('*'):
                result = A * B;
                break;
            case ('+'):
                result = A + B;
                break;
            case ('-'):
                result = A - B;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        Roman result2 = null;
        if( R == 1) {
            result2 = getByValue(result);
            System.out.println(result2);
        }
        else System.out.println(result);
    }

    public static boolean checkArray(boolean[] checked) {
        for (boolean b : checked) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public enum Roman {
        I(1),II(2),III(3),IV(4), V(5),VI(6),VII(7),VIII(8),IX(9), X(10),
        XI(11),XII(12),XIII(13),XIV(14),XV(15),XVI(16),XVII(17),XVIII(18),XIX(19),XX(20), XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28),
                XXIX(29), XXX(30), XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
                XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50), LI(51), LII(52), LIII(53), LIV(54),
                LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60), LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68),
                LXIX(69), LXX(70), LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
                LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90), XCI(91),
                XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);

        private final int value;

        private Roman(int value) {
            this.value = value;
        }

        public int toInt() {
            return value;
        }

        public int getValue() {
            return value;
        }
    }
    public static Roman getByValue(int value) {
        for (Roman rn : Roman.values()) {
            if (rn.getValue() == value)
                return rn;
        }
        throw new IllegalArgumentException("Не римское число" + value);
    }
}