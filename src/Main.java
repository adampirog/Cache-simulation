
import pl.apirog.sortersFrame.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main
{
    static void display (List<IElement> list)
    {
        int n =list.size();

        for (IElement element : list )
        {

            System.out.println(element.getValue() + element.getName());
        }
    }

    static void displayINT (List<IntElement> list)
    {
        int n = list.size();

        for (IElement element : list)
        {

            System.out.println(element);
        }
    }

    public static void main(String[] args)
    {
        Class<? extends AbstractFloatSorter> klasa;
        AbstractFloatSorter sorter = null;

        List<IElement> list = new ArrayList<>();
        List<IElement> sorted;


        try {
            klasa = (Class<? extends AbstractFloatSorter>)  // Unchecked cast!
                    Class.forName("pl.apirog.sorters.QuickSorter");
            // Get the constructor
            Constructor<? extends AbstractFloatSorter> cons;
            cons = klasa.getDeclaredConstructor();
            // Instantiate the set
            sorter = cons.newInstance();
        } catch (ReflectiveOperationException e) {
            System.err.println("Constructor not accessible");
            System.exit(1);
        }

        //---------Dzialanie klasy



        IElement a = new FloatElement(5,"a");
        IElement b = new FloatElement(18,"b");
        IElement c = new IntElement(-21, "c");
        IElement d = new FloatElement(66,"d");
        IElement e = new IntElement(-8,"e");



        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        display(list);
        sorted = sorter.solve2(list);
        System.out.println("Posortowane:");
        display(sorted);
    }
}
