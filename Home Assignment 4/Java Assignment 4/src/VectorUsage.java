import java.util.Vector;

public class VectorUsage {

    public static void main(String[] args) {


        Vector<Integer> numbers = new Vector<>();


        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("Original Vector: " + numbers);


        numbers.remove(2);


        System.out.println("Updated Vector: " + numbers);
    }
}