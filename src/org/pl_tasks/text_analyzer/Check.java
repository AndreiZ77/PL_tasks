/**
 * Необходимо реализовать иерархию классов.
 * Предположим, мы хотим анализироваьть текст, помечая его опрделенными метками. Метки заданы перечислением:
 * enum Label {SPAM, NEGATIVE_TEXT, TOO_LONG, OK}
 *
 * Общий интерфейс анализа текста:
 * interface TextAnalyzer { Label processText(String text); }
 *
 * Необходимо реализовать 3 класса, которые реализуют этот интерфейс: SpamAnalyzer, NegativeTextAnalyzer и TooLongTextAnalyzer
 * и абстрактный класс AbstractKeywordAnalyzer
 * AbstractKeywordAnalyzer должен иметь 2 абстрактных метода: getKeywords(), который возвращает набор ключевых слов для анализа
 * и getLabel(), который возвращает метку, в случае, если р-тат анализа положительный. Так же на основе этих методов в этом
 * классе должна быть реализован метод processText() и не должен быть реализован в наследниках.
 * Класс SpamAnalyzer должен наследоваться от KeywordAnalyzer, в нем должен быть конструктор, принимающий массив строк. Если
 * одна из этих строк присутствует в проверямом тексте, возвращаем Label.SPAM.
 * Класс NegativeTextAnalyzer должен наследоваться от KeywordAnalyzer и пусть анализирует тект на наличие одного из 3х
 * смайликов: ":(", "=(", ":|" и возвращает Label.NEGATIVE_TEXT, если р-тат положительный.
 *
 * Класс TooLongTextAnalyzer в конструкторе должен принимать int и если длина анализируемого текста больше этого значения,
 * метод анализа должен возвращать label.TOO_LONG. Во всех остальных случаях должен возвращаться Label.OK
 *
 * Реализовать статический метод для проверки текста с 2-мя параметрами: TextAnalyzer[] - массив экземпляров классов для
 * проверки текста, String - проверяемый текст, возвращать должен Label - первую не-OK метку в порядке набора анализаторов текста,
 * или OK, если все анализаторы вернули OK.
 *
 * @Andrey_Zenkov hi_andy@mail.ru
 */

package org.pl_tasks.text_analyzer;

public class Check {

    public static void main(String[] args) {
        test();
    }

    static void test() {
        TextAnalyzer[] analyzer = {
                new SpamAnalyzer(new String[] {"x_word1", "x_word2", "x_word3"}),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(40)};
        for (String comment : new String[] {
                "This is not an article, but complete x_word1!",
                "It's very bad! =(",
                "I have so much to tell you friends. This story began a long time ago...",
                "This is a cool post..."})
            System.out.println(checkLabels(analyzer, comment));
    }

    static Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label answer = analyzer.processText(text);
            if (answer != Label.OK) { return answer; }
        }
        return Label.OK;
    }

}
