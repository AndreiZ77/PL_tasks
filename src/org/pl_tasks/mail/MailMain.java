/**
 * Задача: Реализовать набор классов, чтобы ниже приведенный код (в методе main()) работал
 *
 * @Andrey_Zenkov  hi_andy@mail.ru
 */

package org.pl_tasks.mail;

import java.util.*;
import java.util.function.*;

public class MailMain {

    interface MyGeneric<T>{
        String getFrom();
        String getTo();
        T getContent();
    }
    public static class MailMessage implements MyGeneric<String>{
        private String from, to, content;

        private MailMessage(String from, String to, String content){
            this.from = from;
            this.to = to;
            this.content = content;
        }
        @Override
        public String getFrom() { return from; }
        @Override
        public String getTo() { return to; }
        @Override
        public String getContent() { return content; }
    }

    public static class Salary implements MyGeneric<Integer>{
        private String from, to;
        private Integer content;

        private Salary(String from, String to, Integer content){
            this.from = from;
            this.to = to;
            this.content = content;
        }
        @Override
        public String getFrom() { return from; }
        @Override
        public String getTo() { return to; }
        @Override
        public Integer getContent() { return content; }
    }


    public static class MailService<T> extends HashMap implements Consumer<MyGeneric<T>> {
        public Map<String, List<T>> getMailBox() { return this; }

        @Override
        public void accept(MyGeneric<T> tMyGeneric) {
            String to = tMyGeneric.getTo();
            List mailbox = getMailBox().get(to);
            if (mailbox == null) {
                mailbox = new LinkedList<>();
                getMailBox().put(to, mailbox);
            }
            mailbox.add(tMyGeneric.<T>getContent());
        }
        @Override
        public List get(Object K) {
            if (containsKey(K)) {
                return (List) super.get(K);
            } else {
                List mailbox = new LinkedList<>();
                getMailBox().put((String)K, mailbox);
                return mailbox;
            }
        }
    }


//ниже приведенный код должен работать vvv

    public static void main(String[]args){

        String randomFrom="NameFrom"; //Любая случайная строка.
        String randomTo="NameTo"; //Любая случайная строка.
        int randomSalary=100; //Любое случайное число.

        MailMessage firstMessage = new MailMessage(
        "Robert Howard",
        "H.P. Lovecraft",
        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        //Проверим, что сообщение создалось как надо.
        assert firstMessage.getFrom().equals("Robert Howard"):"Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"):"Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"):"Wrong firstMessage content ending";

        MailMessage secondMessage=new MailMessage(
        "Jonathan Nolan",
        "Christopher Nolan",
        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage=new MailMessage(
        "Stephen Hawking",
        "Christopher Nolan",
        "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

        // Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

        // Получение "почтового ящика",
        // где по получателю можно получить список сообщений, которые были ему отправлены
        Map mailBox=mailService.getMailBox();


        //Проверка почтового ящика
        assert mailBox.get("H.P. Lovecraft").equals(
        Arrays.asList(
        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        )
        ):"wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
        Arrays.asList(
        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
        "Я так и не понял Интерстеллар."
        )
        ):"wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.emptyList()):"wrong mailService mailbox content (3)";

        // Создание списка из трех зарплат.
        Salary salary1=new Salary("Facebook","Mark Zuckerberg",1);
        Salary salary2=new Salary("FC Barcelona","Lionel Messi",Integer.MAX_VALUE);
        Salary salary3=new Salary(randomFrom,randomTo,randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService salaryService = new MailService <> ();

        // Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1,salary2,salary3).forEach(salaryService);

        //Проверка почтового ящика
        Map salaries=salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)):"wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)):"wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)):"wrong salaries mailbox content (3)";
    }
}