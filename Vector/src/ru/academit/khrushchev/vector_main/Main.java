package ru.academit.khrushchev.vector_main;

import ru.academit.khrushchev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2});
        Vector vector4 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2});

        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector2);

        System.out.println(vector3.addVector(vector4));
        System.out.println(vector4.addVector(vector3));
        System.out.println(Vector.addVectors(vector3, vector4));
        System.out.println(Vector.addVectors(vector4, vector3));

        System.out.println(vector3.subtractVector(vector4));
        System.out.println(vector4.subtractVector(vector3));
        System.out.println(Vector.subtractVectors(vector3, vector4));
        System.out.println(Vector.subtractVectors(vector4, vector3));

//        System.out.println(vector4.reverseVector());
//        System.out.println(vector4.reverseVector());

        System.out.println(vector3.getLength());

        System.out.println(vector3.equals(vector4));
        System.out.println(vector4.equals(vector3));

        System.out.println(vector3.getComponent(2));
        vector3.setComponent(5.2, 2);
        System.out.println(vector3.getComponent(2));

        System.out.println(Vector.getVectorsScalarMultiplication(vector3, vector4));
        System.out.println(Vector.getVectorsScalarMultiplication(vector4, vector3));

//        System.out.println(vector3.multiplyOnScalar(2));
    }
}

//        1. ru.academit.khrushchev.vector.vector - лучше сократить имя пакета до ru.academit.khrushchev.vector
//        А класс Main можно оставить во вложенном пакете: ru.academit.khrushchev.vector.main
//        Или можно сделать невложенный пакет: ru.academit.khrushchev.vector_main
//
//        2. n:
//        - неинформативное имя
//        - это поле не нужно, т.к. размер вектора - это просто длина массива
//
//        3. При бросании исключений в тексте исключения лучше указывать значения аргументов, вызвавшие исключение,
//        это упростит отладку кода
//
//        4. Vector(double[] components):
//        - если длина входного массива 0, нужно кидать исключение
//        - сейчас в новый вектор присваивается ссылка на внешний массив.
//        Поэтому если изменить этот массив снаружи, то изменится и вектор.
//        Так быть не должно, нужно создавать копию массива
//
//        5. Во многих именах методов нужно убрать слово Vector, т.к. это и так класс Vector
//
//        6. addVector(Vector vector), subtractVector(Vector vector):
//        - vector.getSize() > getSize() - внутри класса лучше не использовать геттеры, а напрямую обращаться к длине массивов
//        - в текущей логике работы переменная resultComponents не нужна, можно обращаться напрямую к resultVector.components
//        - исходным вектором, из которого вычитается/прибавляется другой вектор, всегда должен быть тот вектор, от которого
//        вызывается метод
//        - эти нестатические методы должны не возвращать новый вектор, а изменять текущий вектор
//
//        7. subtractVector(Vector vector):
//        http://joxi.ru/12M8YKaH0PjNEm - эта логика неверна.
//        В результате сейчас получаются неверные значения элементов результирующего вектора:
//        {1, 3} - {2, 4, 6, 8} = {1, 1, -6, -8}, а должно быть: {-1, -1, -6, -8}
//        {1} - {2, 4, 6, 8} = {1, -4, -6, -8}, а должно быть: {-1, -4, -6, -8}
//
//        8. multiplyOnScalar:
//        - правильнее будет multiplyByScalar
//        - этот метод должен не возвращать новый вектор, а изменять текущий вектор
//        - в текущей логике переменная resultVectorComponents не нужна, т.к. проще обращаться напрямую к resultVector.components
//
//        9. reverseVector:
//        - этот метод должен не возвращать новый вектор, а изменять текущий вектор
//        - нужно использовать метод умножения на скаляр, чтобы не дублировать код
//
//        10. getLength:
//        - scalarComposition - лучше назвать sum, т.к. здесь только один вектор
//
//        11. Статические addVectors, subtractVectors:
//        - нужно использовать нестатические методы
//        - имена должны означать не "прибавить/вычесть вектор", а "получить сумму/разность векторов"
//        - нужно вычитать из первого вектора второй вектор
//
//        12. Статический subtractVectors - неверные значения элементов результирующего вектора:
//        {1} - {2} = {1}, а должно быть: {-1}
//        {1, 3} - {2, 4} = {1, 1}, а должно быть: {-1, -1}
//        {1, 3, 5, 7} - {2, 4, 6, 8} = {1, 1, 1, 1}, а должно быть: {-1, -1, -1, -1}
//        {1, 3} - {2, 4, 6, 8} = {1, 1, -6, -8}, а должно быть: {-1, -1, -6, -8}
//        {1} - {2, 4, 6, 8} = {1, -4, -6, -8}, а должно быть: {-1, -4, -6, -8}
//
//        13. getVectorsScalarMultiplication - по смыслу вместо "получить скалярное умножение" должно быть
//        "получить скалярное произведение", т.е. результат умножения
//
//        14. equals:
//        - obj.getClass() != this.getClass() - обращение через this используют для разрешения конфликтов имен, а здесь его нет
