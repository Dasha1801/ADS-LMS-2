package by.it.group351051.belsky.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int lenOne = one.length();
        int lenTwo = two.length();

        // Создаем двумерный массив для хранения расстояний
        int[][] dp = new int[lenOne + 1][lenTwo + 1];

        // Заполняем первую строку и первый столбец
        for (int i = 0; i <= lenOne; i++) {
            dp[i][0] = i; // Расстояние от строки к пустой строке (удаление всех символов)
        }
        for (int j = 0; j <= lenTwo; j++) {
            dp[0][j] = j; // Расстояние от пустой строки к строке (вставка всех символов)
        }

        // Заполняем массив dp
        for (int i = 1; i <= lenOne; i++) {
            for (int j = 1; j <= lenTwo; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Если символы равны, берем значение диагонали
                } else {
                    // Рассчитываем минимальные операции: вставка, удаление, замена
                    int insert = dp[i][j - 1] + 1; // Вставка
                    int delete = dp[i - 1][j] + 1; // Удаление
                    int replace = dp[i - 1][j - 1] + 1; // Замена
                    dp[i][j] = Math.min(insert, Math.min(delete, replace)); // Минимум из трех
                }
            }
        }

        // Возвращаем расстояние редактирования
        return dp[lenOne][lenTwo];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}