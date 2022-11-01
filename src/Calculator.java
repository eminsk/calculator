import java.util.Scanner;
import java.util.TreeMap;

public class Calculator {
    public static String calc(String input) {
        converter converter = new converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int count=0;
        for (char element : input.toCharArray()){
            if (element == '+') count++;
            if (element == '-') count++;
            if (element == '*') count++;
            if (element == '/') count++;
        }
        if (count>1)
        {
            return("1");
        }
        input = input.replace(" ", ""); // Чтобы можно вводить выражение с пробелами - удаляем пробелы
        // Определяем арифметическое действие
        int actionIndex=-1;
        for (int c = 0; c < actions.length; c++) {
            if(input.contains(actions[c])){
                actionIndex = c;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            return("2");
        }
        //"2-4".split("-")-> {"2", "4"}
        String[] data = input.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int x1=0,y1=0;
            // определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman){
                // если римские, то конвертируем их в арабские
                x1 = converter.romanToInt(data[0]);
                y1 = converter.romanToInt(data[1]);
            }else{
                // если арабские, то конвертируем их из строки в число
                x1 = Integer.parseInt(data[0]);
                y1 = Integer.parseInt(data[1]);
            }
            if (x1<0) {return("3");}
            if (x1>9) {return("3");}
            if (y1<0) {return("3");}
            if (y1>9) {return("3");}
            // выполняем с числами арифметические действие
            int result=0;
            switch (actions[actionIndex]){
                case "+":
                    result = x1+y1;
                    break;
                case "-":
                    result = x1-y1;
                    break;
                case "*":
                    result = x1*y1;
                    break;
                case "/":
                    result = x1/y1;
                    break;
            }
            if(isRoman){
                //Если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            }else {
                //Если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        }else {
            return "4";
        }
        return input;
    }
    public static void main(String[] args) {
        //2+3
        //V-VII
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        exp = calc(exp);
        if (exp == "1") {
            System.out.println("формат математической операции не удовлетворяет заданию\n");
        }
        if (exp == "2") {
            System.out.println("Ошибка в выражении");
        }
        if (exp == "3") {
            System.out.println("Числа не должны быть меньше 0 и больше 9\n");
        }
        if (exp == "4") {
            System.out.println("Числа должны быть в одном формате");
        }

    }

//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
}