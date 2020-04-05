
import pl.apirog.sortersFrame.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


public class Main
{
    private static List<Class<? extends AbstractIntSorter>> getClasses(File directory)
    {
        List<Class<? extends AbstractIntSorter>> files = new ArrayList<Class<? extends AbstractIntSorter>>();
        File[] entries = directory.listFiles();
        Class superClass = AbstractIntSorter.class;

        for (File entry : entries)
        {
            if(entry.getName().endsWith(".class"))
            {
                try
                {

                        String name = entry.getName().replace(".class","");
                        Class clazz = Class.forName("pl.apirog.sorters."+name);
                        if(superClass.isAssignableFrom(clazz))
                        {
                            Class<? extends AbstractIntSorter> clazz2 = (Class<? extends AbstractIntSorter>) Class.forName("pl.apirog.sorters."+name);
                            files.add(clazz2);
                        }




                } catch (NoClassDefFoundError e)
                {

                } catch (ReflectiveOperationException f)
                {
                    f.printStackTrace();
                    System.err.println("Error");
                    System.exit(1);
                }

            }

        }
        return files;
    }
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

        File directory = new File("out/production/Cache-simulation/pl/apirog/sorters");
        List<Class<? extends AbstractIntSorter>> classList = getClasses(directory);



        for(Class clazz : classList)
        {

            System.out.println(clazz.getName());
            Class<? extends AbstractIntSorter> klasa;
            AbstractIntSorter sorter = null;

            List<IntElement> list = new ArrayList<>();
            List<IntElement> sorted;


            try {
                klasa = clazz;
                // Get the constructor
                Constructor<? extends AbstractIntSorter> cons;
                cons = klasa.getDeclaredConstructor();
                // Instantiate the set
                sorter = cons.newInstance();
            } catch (ReflectiveOperationException e) {
                System.err.println("Constructor not accessible");
                System.exit(1);
            }

            //---------Dzialanie klasy


            IntElement a = new IntElement(5, "a");
            IntElement b = new IntElement(18, "b");
            IntElement c = new IntElement(-21, "c");
            IntElement d = new IntElement(66, "d");
            IntElement e = new IntElement(-8, "e");


            list.add(a);
            list.add(b);
            list.add(c);
            list.add(d);
            list.add(e);

            displayINT(list);
            sorted = sorter.solve(list);
            System.out.println("Posortowane:");
            displayINT(sorted);

        }
    }
}
