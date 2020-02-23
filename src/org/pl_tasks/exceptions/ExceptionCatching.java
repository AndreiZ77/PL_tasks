/**
 * Задача: Необходимо описать метод main, где вызывать метод catchMe(), перехватить следующие исключения:
 * NullPointerException и FileNotFoundException перехватить, но ArithmeticException и URISyntaxException не перехватывать!
 *
 * @Andrey_Zenkov  hi_andy@mail.ru
 */

package org.pl_tasks.exceptions;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ExceptionCatching {

    private static int indexException = 1; //укажите номер выбрасываемого исключения от 1 до 4 для тестирования метода

    public static void main(String[] args) throws Exception {
        try {
            catchMe(indexException);
        } catch (NullPointerException e) {
            System.out.println("I caught NullPointerException!");
        } catch (FileNotFoundException e) {
            System.out.println("I caught FileNotFoundException!");
        }
    }

    public static void catchMe(int indexException) throws NullPointerException, ArithmeticException, FileNotFoundException, URISyntaxException {
        if (indexException == 1) throw new NullPointerException();
        if (indexException == 2) throw new ArithmeticException();
        if (indexException == 3) throw new FileNotFoundException();
        if (indexException == 4) throw new URISyntaxException("","");
    }

}
