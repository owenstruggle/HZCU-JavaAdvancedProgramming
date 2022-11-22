package question02;

import java.util.ArrayList;

/**
 * @author Owem
 * @date 2022/9/13 15:57
 * @description TODO
 **/
public class SortUtils {
    public static void sortID(ArrayList<Person> args) {
        for (int i = 0; i < args.size(); i++) {
            for (int j = i; j < args.size(); j++) {
                if (args.get(i).compareTo(args.get(j)) < 0) {
                    Person temp = (Person) args.get(i).clone();
                    args.set(i, args.get(j));
                    args.set(j, temp);
                }
            }
        }
        for(Object l : args) {
            System.out.println(l);
        }
    }
}
