import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Введите аргумент!");
            return;
        }

        String path = args[0];
        System.out.println(pathArray(path));
    }

    private static List scanAndList(String file){
        List<Double> array = new ArrayList<>();
        try {
            Scanner files = new Scanner(new FileInputStream(file));
            while (files.hasNext()){
                String s = files.nextLine();
                s = s.replace("\\n", "");
                array.add(Double.valueOf(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }

    private static int sumList(List a1,List a2,List a3,List a4,List a5){
        List<Double> sumArrays = new ArrayList<>();
        for (int i = 0; i< a1.size(); i++){
            sumArrays.add((double) a1.get(i) + (double)a2.get(i) + (double)a3.get(i) + (double)a4.get(i) + (double)a5.get(i));
        }
        double max = sumArrays.stream().reduce((x, y) -> Math.max(x, y)).get();

        return sumArrays.indexOf(max)+1;
    }

    private static int pathArray(String path){
        String[] namePath = {"\\Cash1.txt", "\\Cash2.txt", "\\Cash3.txt", "\\Cash4.txt", "\\Cash5.txt"};

        List<Double> a1 = scanAndList(path+namePath[0]);
        List<Double> a2 = scanAndList(path+namePath[1]);
        List<Double> a3 = scanAndList(path+namePath[2]);
        List<Double> a4 = scanAndList(path+namePath[3]);
        List<Double> a5 = scanAndList(path+namePath[4]);

        return sumList(a1, a2, a3, a4, a5);
    }
}