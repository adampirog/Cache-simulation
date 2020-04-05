
import pl.apirog.cache.Cache;
import pl.apirog.sortersFrame.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Main
{
    public static List<Class<? extends AbstractIntSorter>> classList;

    private static List<Class<? extends AbstractIntSorter>> getClasses(File directory)
    {
        List<Class<? extends AbstractIntSorter>> files = new ArrayList<Class<? extends AbstractIntSorter>>();
        File[] entries = directory.listFiles();
        Class superClass = AbstractIntSorter.class;
        String pack = "pl.apirog.sorters.";

        for (File entry : entries)
        {
            if(entry.getName().endsWith(".class"))
            {
                try
                {
                        String name = entry.getName().replace(".class","");
                        Class clazz = Class.forName(pack+name);

                        if(superClass.isAssignableFrom(clazz))
                        {
                            Class<? extends AbstractIntSorter> clazz2 = (Class<? extends AbstractIntSorter>) Class.forName(pack+name);
                            files.add(clazz2);
                        }

                } catch (NoClassDefFoundError e)
                {

                } catch (ReflectiveOperationException f)
                {
                    f.printStackTrace();
                    return null;
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

        Cache cache = new Cache();
        SortingThread thr = new SortingThread(cache,0);
        SortingThread thr2 = new SortingThread(cache,1);
        thr.start();
        thr2.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thr.stop();
        thr2.stop();

        List<List<IntElement>> set = cache.valueList();
        Set<Long> keys = cache.keySet();

        System.out.println("Size: "+cache.size());

        for(Long longi : keys)
        {
            System.out.println(longi);
        }





        /*
        File directory = new File("out/production/Cache-simulation/pl/apirog/sorters");
        classList = getClasses(directory);





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

         */


    }


}
