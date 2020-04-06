
import pl.apirog.cache.Cache;
import pl.apirog.sortersFrame.*;

import java.io.File;
import java.util.*;


public class Main
{
    volatile public static List<Class<? extends AbstractIntSorter>> classList;

    private static synchronized List<Class<? extends AbstractIntSorter>> getClasses(File directory)
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
                   //System.out.println("Wrong class "+ entry.getName());
                } catch (ReflectiveOperationException f)
                {
                   // System.out.println("Wrong class "+ entry.getName());
                }

            }
            else if(entry.isDirectory())
            {
                files.addAll(getClasses(entry));
            }

        }
        return files;
    }

    public static synchronized Class getRandomClass()
    {
        if(classList.size()==0)
            return null;

        Random rand = new Random(); //instance of random class
        int int_random = rand.nextInt(classList.size());
        return classList.get(int_random);
    }

    public static void safePrint(Object string)
    {
        synchronized (System.out)
        {
            System.out.println(string);
        }
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
        //classList = getClasses(new File(System.getProperty("java.class.path")));
        int masterTime, threads, intervals;
        //report period in seconds
        int reportPeriod = 3;
        List<SortingThread> threadList = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Number of threads:");
        threads = in.nextInt();
        System.out.println("App work time (in seconds):");
        masterTime = in.nextInt();

        intervals = masterTime/reportPeriod;

        for(int i=0;i<threads;i++)
        {
            SortingThread thrd = new SortingThread(cache, i);
            threadList.add(thrd);
            thrd.start();
        }

        for(int i=0;i<intervals;i++)
        {
            try {
                Thread.sleep(reportPeriod*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            safePrint("Local miss percent: " + cache.localMissPercent());

            //refresh classes
            classList=getClasses(new File(System.getProperty("java.class.path")));
        }

        for(int i=0;i<threads;i++)
        {
            threadList.get(i).stop();
        }

        safePrint("Local miss percent: " + cache.localMissPercent());
        safePrint("Global miss percent: "+cache.globalMissPercent());


    }


}
