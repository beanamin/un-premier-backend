package cal.info;

import java.util.*;
import java.util.stream.Collectors;

public class test {

    private static Map<String, Set<String>> etudiantsParProg = new HashMap<>();
    public static void main(String[] args) {
        Set<String> stringSet1 = new HashSet<>();
        Set<String> stringSet2 = new HashSet<>();
        Set<String> stringSet3 = new HashSet<>();
        Set<String> stringSet4 = new HashSet<>();
        Set<String> stringSet5 = new HashSet<>();

        stringSet2.add("heehoo");
        stringSet2.add("haa");
        etudiantsParProg.put("zustice", stringSet2);

        stringSet1.add("heehoo");
        etudiantsParProg.put("aechno", stringSet1);

        stringSet4.add("heehoo");
        stringSet4.add("haa");
        stringSet4.add("odod");
        stringSet4.add("dasads");
        etudiantsParProg.put("icky", stringSet4);

        stringSet5.add("heehoo");
        stringSet5.add("haa");
        stringSet5.add("odod");
        stringSet5.add("dasads");
        stringSet5.add("nnnnnnnnnnn");
        etudiantsParProg.put("lllllll", stringSet5);

        stringSet3.add("heehoo");
        stringSet3.add("haa");
        stringSet3.add("odod");
        etudiantsParProg.put("martau", stringSet3);

        System.out.println(etudiantsParProg.keySet());

        System.out.println(getvalues());
    }


    public static List<String> getvalues(){
        List<String> strings = new ArrayList<>();
        Map<String, Integer> etudiantsParProgLength = new HashMap<>();

        for (String key : etudiantsParProg.keySet()){
            etudiantsParProgLength.put(key, etudiantsParProg.get(key).size());
        }

        etudiantsParProgLength.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(k -> strings.add(k.getKey()));


        return strings;
    }
}
