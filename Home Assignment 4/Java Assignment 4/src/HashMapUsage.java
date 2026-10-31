import java.util.HashMap;

public class HashMapUsage {

    public static void main(String[] args) {


        HashMap<String, Integer> students = new HashMap<>();


        students.put("John", 85);
        students.put("Alice", 90);
        students.put("Bob", 78);


        System.out.println("Marks for Alice: " + students.get("Alice"));
    }
}