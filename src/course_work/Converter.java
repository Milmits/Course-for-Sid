package course_work;

/**
 * Класс для выполнения конвертации чисел между различными системами счисления.
 *
 * @author Милан Кучук Михайлович
 * @version 1.0
 */
public class Converter {
    /**
     * Выполняет конвертацию числа из одной системы счисления в другую.
     *
     * @param number Строка, представляющая число в исходной системе счисления.
     * @param fromBase Основание исходной системы счисления.
     * @param toBase Основание целевой системы счисления.
     * @return Строка, представляющая число в целевой системе счисления.
     */
    // Метод для перевода числа из одной системы счисления в другую
    public static String convert(String number, int fromBase, int toBase) throws NumberFormatException {
        // Парсим введенное число в десятичную систему
        int decimalValue = Integer.parseInt(number, fromBase);
        // Возвращаем результат перевода из десятичной системы в целевую
        return Integer.toString(decimalValue, toBase).toUpperCase();
    }
}
