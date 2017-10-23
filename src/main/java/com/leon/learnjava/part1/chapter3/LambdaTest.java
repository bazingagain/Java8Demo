package com.leon.learnjava.part1.chapter3;

import com.leon.learnjava.part1.chapter3.functionInface.BufferedReaderProcessor;
import com.leon.learnjava.part1.chapter3.functionInface.TriFunction;
import com.leon.learnjava.part1.common.Apple;
import com.leon.learnjava.part1.common.Color;
import com.leon.learnjava.part1.common.Fruit;
import com.leon.learnjava.part1.common.Orange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.*;

/**
 * Created on 23/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class LambdaTest {

    static Map<String, Function<Integer, Fruit>> fruitMap = new HashMap<>();

    public static void main(String[] args) {
        try {
//            String oneLine = processFile((br) -> br.readLine());
            String oneLine = processFile(BufferedReader::readLine); //方法引用
            String twoLine = processFile((br) -> br.readLine() + br.readLine());
            System.out.println(oneLine);
            System.out.println(twoLine);
            forEach(Arrays.asList(1, 2, 4 ,6), System.out::println);
//            forEach(Arrays.asList(1, 2, 4 ,6), (i) -> System.out.println(i));
            List<Integer> list = map(Arrays.asList("lambda", "Java8"), String::length);
//            List<Integer> list = map(Arrays.asList("lambda", "Java8"), (s) -> s.length());
            List<String> upperStrs = map(Arrays.asList("lambda", "Java8"), String::toUpperCase);
            forEach(list, System.out::println);
            forEach(upperStrs, System.out::println);

            List<String> str = Arrays.asList("a", "b", "C", "A", "B");
            str.sort(String::compareToIgnoreCase);
//            str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
            forEach(str, System.out::println);

            Supplier<Apple> c1 = Apple::new;  //利用构造函数引用指向Apple的默认构造函数
            Apple a1 = c1.get();
            Function<Integer, Apple> d1 = Apple::new;
            Apple d2 = d1.apply(100);  //Function函数传值
            System.out.println(d2.getWeight());

            List<Integer> weights = Arrays.asList(7, 3, 4, 10);
            List<Apple> apples = map(weights, Apple::new);
            forEach(apples, (apple -> System.out.println(apple.getWeight())));

            BiFunction<Integer, String, Apple> c3 = Apple::new;
            Apple d3 = c3.apply(200, "green");

            //针对3个参数的情况,自定义FunctionInterface
            TriFunction<Integer, Integer, Integer, Color> tri = Color::new;
            Color color = tri.applay(50, 34, 20);
            System.out.println(color.getX());

            List<Apple> inventory = Arrays.asList(new Apple(100, "red"), new Apple(40, "green"), new Apple(90, "red"));
            inventory.sort(Comparator.comparing(Apple::getWeight)); //一步达到

            /**
             * 组合
             */
            inventory.sort(Comparator.comparing(Apple::getWeight).reversed()); //比较器组合
            inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));//比较器连接
            Predicate<Apple> redApple = a -> "red".equals(a.getColor());
            Predicate<Apple> notRed = redApple.negate(); //谓词复合
            Predicate<Apple> redAndHeavy = redApple.and((a) -> a.getWeight() > 100);
            //从左至又匹配原则 a.or(b).and(c) 为 (a||b) && c
            Predicate<Apple> redAndHeavyOrGreen = redApple.and(a -> a.getWeight() > 100).or(a -> "green".equals(a.getColor()));

            Function<Integer, Integer> f = x -> x + 1;
            Function<Integer, Integer> g = x -> x * 2;
            Function<Integer, Integer> h = f.andThen(g); //h = g(f(x))
            int result = h.apply(1);
            System.out.println("g(f(x)) = " + result);

            Function<Integer, Integer> s = f.compose(g); //s = f(g(x))
            System.out.println("f(g(x)) = " + s.apply(1));

            double area = intergate(x -> x + 10, 3, 22);
            System.out.println("area : " + area);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        fruitMap.put("apple", Apple::new);
        fruitMap.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return fruitMap.get(fruit.toLowerCase()).apply(weight);
    }

    /**
     * (T) -> String
     * @param processor
     * @return
     * @throws IOException
     */
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }
    }

    /**
     * (T) -> void
     * @param list
     * @param c
     * @param <T>
     */
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    /**
     * (T,R) -> R
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<R>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static Double intergate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2;
    }



}
