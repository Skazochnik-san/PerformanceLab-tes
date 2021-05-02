import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите аргумент!");
            return;
        }
        result(args[0]);
    }

    private static void result(String files){
        List<Double> array = new ArrayList<>();

        try {
            Scanner file = new Scanner(new FileInputStream(files));
            while (file.hasNext()){
                array.add(file.nextDouble());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int size = array.size();

        Collections.sort(array);

        //формулу брала с интернета, побаялась использовать библиотеку, поэтому percentile != 8.20
        double percentile =  90/100.0*(size-1)+1;
        double median = size%2 == 0 ? ((array.get(size/2) + array.get(size/2 - 1)) /2) :
                array.get(size/2);
        double max = array.stream().reduce((x, y) -> Math.max(x, y)).get();
        double min = array.stream().reduce((x, y) -> Math.min(x, y)).get();
        double average = array.stream().reduce((x,y) -> x+y).get() / size;

        System.out.printf("%.2f%n", percentile, median, max, min, average);
        System.out.printf("%.2f%n", median);
        System.out.printf("%.2f%n", max);
        System.out.printf("%.2f%n", min);
        System.out.printf("%.2f%n", average);
    }
}