import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Генерация последовательности компьютера и обьявление переменных бота
        int[] botSubs;
        botSubs = new int[20];
        final Random rand = new Random();
        int action;
        int index;
        int bRightIndex = 19; //Правый индекс числа последовательности игрока
        System.out.print("Моя последовательность: ");
        for (int i = 0; i < 20; i++) {
            botSubs[i] = rand.nextInt(10);
            System.out.print(botSubs[i] + " ");
        }
        System.out.println();

        //Запись последовательности игрока
        int[] playerSubs;
        playerSubs = new int[20];
        Scanner in = new Scanner(System.in);
        int actionChoice;
        int numIndex;
        int pRightIndex = 19; //Правый индекс числа последовательности компьютера
        int newNum;
        System.out.println("Введите свои 20 цифр: ");
        for (int i = 0; i < 20; i++) {
            int temp = in.nextInt();
            if (temp > 9 || temp < 0) {
                System.out.println("Не допускаются отрицательные числа и числа больше 9");
            }
            else {
                playerSubs[i] = temp;
            }
        }
        System.out.print("Ваша последовательность: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(playerSubs[i] + " ");
        }
        System.out.println();

        //Начало игры
        boolean playerWins = false;
        boolean botWins = false;
        boolean playerTurn = true;
        while (!playerWins && !botWins) {
            if (playerTurn) {
                //Ход игрока
                System.out.println("Сейчас ваш ход. Если хотите уменьшить какое-нибудь число оппонента, напишите 1,\n" +
                                   "затем порядковый номер числа, затем значение, меньшее этого числа.\n" +
                                   "Если хотите стереть какой-нибудь 0 и все числа правее него, напишите 0,\n" +
                                   "затем порядковый номер ноля");
                //Ввод действия
                actionChoice = in.nextInt();
                while (actionChoice != 1 && actionChoice != 0) {
                    System.out.println("При выборе действия допускаются только значения 0 или 1");
                    actionChoice = in.nextInt();
                }
                //Ввод индекса числа
                numIndex = in.nextInt();
                while (numIndex < 0 || numIndex > pRightIndex) {
                    System.out.println("При выборе порядкового номера допускаются только значения от 0 до номера правого существующего числа");
                    numIndex = in.nextInt();
                }
                //Уменьшение числа
                if (actionChoice == 1) {
                    newNum = in.nextInt();
                    while (newNum >= botSubs[numIndex] || newNum < 0) {
                        System.out.println("Не допускаются отрицательные числа и числа большие либо равные предыдущему значению");
                        newNum = in.nextInt();
                    }
                    botSubs[numIndex] = newNum;
                }
                //Стирание
                if (actionChoice == 0) {
                    while (botSubs[numIndex] != 0) {
                        System.out.println("Стирать можно только от нуля");
                        numIndex = in.nextInt();
                    }
                    for (int i = numIndex; i <= pRightIndex; i++) {
                        botSubs[i] = -1;
                    }
                    if (numIndex > 0) {
                        pRightIndex = numIndex - 1;
                    }
                    else {
                        System.out.println("Я победил!");
                        botWins = true;
                    }
                }
                //Вывод результата
                System.out.print("Моя изменённая последовательность: ");
                for (int i = 0; i < pRightIndex; i++) {
                    System.out.print(botSubs[i] + " ");
                }
                System.out.println();
                //Смена хода
                playerTurn = !playerTurn;
            }
            else {
                //Ход компьютера
                action = rand.nextInt(2);
                index = rand.nextInt(bRightIndex);
                if (action == 1) {
                    playerSubs[index] = rand.nextInt(playerSubs[index]);
                }
                if (action == 0) {
                    while (playerSubs[index] != 0) {
                        index = rand.nextInt(bRightIndex);
                    }
                    for (int i = index; i <= bRightIndex; i++) {
                        playerSubs[i] = -1;
                    }
                    if (index > 0) {
                        bRightIndex = index - 1;
                    }
                    else {
                        System.out.println("Вы победили!");
                        playerWins = true;
                    }
                }

                System.out.print("Ваша изменённая последовательность: ");
                for (int i = 0; i < bRightIndex; i++) {
                    System.out.print(playerSubs[i] + " ");
                }
                System.out.println();

                playerTurn = !playerTurn;
            }
        }
    }
}
