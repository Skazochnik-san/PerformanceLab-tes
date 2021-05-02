import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int countTop = 0;
    private static int countSide = 0;
    private static int countInside = 0;
    private static int countOtside = 0;

    public static void main(String[] args) {

        if(args.length < 2){
            System.out.println("Введите агрументы!");
            return;
        }

        String coordShape = args[0];
        String coordPoint = args[1];
        System.out.println(conclusion(coordShape, coordPoint));
    }

    private static List scanAndList(String coord){
        List<String> array = new ArrayList<>();
        List<Float> arrayFloat = new ArrayList<>();

        try {
            Scanner file = new Scanner(new FileInputStream(coord));
            while (file.hasNext()){
                String s = file.nextLine();
                array.add(s.replace("\\n", ""));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String i: array) {
            for (String d: i.split(" ")){
                arrayFloat.add(Float.valueOf(d));
            }
        }
        return arrayFloat;
    }

    private static int checkTop(List<Float> listShape, List<Float> listPoint){
        for (int i = 0; i<listPoint.size(); i++){
            if(listShape.get(0).equals(listPoint.get(i)) && listShape.get(1).equals(listPoint.get(i+1))){
                countTop++;
            }else if(listShape.get(2).equals(listPoint.get(i)) && listShape.get(3).equals(listPoint.get(i+1))){
                countTop++;
            }else if(listShape.get(4).equals(listPoint.get(i)) && listShape.get(5).equals(listPoint.get(i+1))){
                countTop++;
            }else if(listShape.get(6).equals(listPoint.get(i)) && listShape.get(7).equals(listPoint.get(i+1))){
                countTop++;
            }
            i+=1;
        }
        return countTop;
    }

    private static int checkSide(List<Float> listShape, List<Float> listPoint){
        for (int i = 0; i<listPoint.size(); i++){
            if((listShape.get(0).equals(listPoint.get(i)))
                    && listShape.get(1)>=listPoint.get(i+1)
                    && listPoint.get(i+1)<=listShape.get(3)){
                countSide++;
            }else if((listShape.get(4).equals(listPoint.get(i)))
                    && listShape.get(5)>=listPoint.get(i+1)
                    && listPoint.get(i+1)<=listShape.get(7)){
                countSide++;
            }else if((listShape.get(3).equals(listPoint.get(i+1)))
                    && listShape.get(2)>=listPoint.get(i)
                    && listPoint.get(i)<=listShape.get(4)){
                countSide++;
            }else if((listShape.get(1).equals(listPoint.get(i+1)))
                    && listShape.get(0)>=listPoint.get(i)
                    && listPoint.get(i)<=listShape.get(6)){
                countSide++;
            }
            i+=1;
        }
        return countSide;
    }

    private static int checkInside(List<Float> listShape, List<Float> listPoint){
        for (int i = 0; i<listPoint.size(); i++){
            if(listShape.get(0)>=listPoint.get(i) && listPoint.get(i)<=listShape.get(6)
                    && listShape.get(1)>=listPoint.get(i+1) && listPoint.get(i+1)<=listShape.get(3)){
                countInside++;
            }else if (listShape.get(0)>=listPoint.get(i) && listPoint.get(i)<=listShape.get(6)
                    && listShape.get(7)>=listPoint.get(i+1) && listPoint.get(i+1)<=listShape.get(5)){
                countInside++;
            }else if (listShape.get(2)>=listPoint.get(i) && listPoint.get(i)<=listShape.get(4)
                    && listShape.get(1)>=listPoint.get(i+1) && listPoint.get(i+1)<=listShape.get(3)){
                countInside++;
            }else if (listShape.get(2)>=listPoint.get(i) && listPoint.get(i)<=listShape.get(4)
                    && listShape.get(7)>=listPoint.get(i+1) && listPoint.get(i+1)<=listShape.get(5)){
                countInside++;
            } else {
                countOtside++;
            }
            i+=1;
        }
        return countInside;
    }

    private static String conclusion(String coordShape, String coordPoint){
        List<Float> listShape = scanAndList(coordShape);
        List<Float> listPoint = scanAndList(coordPoint);

        checkTop(listShape,listPoint);
        checkSide(listShape,listPoint);
        checkInside(listShape,listPoint);

        String str = "";
        if(countTop>0) str +="0\n";
        if (countSide>0) str +="1\n";
        if (countInside>0) str +="2\n";
        if ((countOtside-countTop-countSide)>0) str +="3\n";

        return str;
    }
}