import pl.apirog.cache.Cache;
import pl.apirog.sortersFrame.AbstractIntSorter;
import pl.apirog.sortersFrame.IntElement;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortingThread implements Runnable
{
    int ID;
    Thread thr;
    boolean isAlive = false;
    private String directory= "out/production/Cache-simulation/pl/apirog/sorters";
    //How big the list is
    private int listSize = 10;
    //How big list elements are
    private int upperBound = 1000;
    private Cache cache;

    public SortingThread(Cache cache, int ID)
    {
        this.cache = cache;
        this.ID=ID;
    }

    private synchronized void setAlive(boolean value)
    {
        isAlive = value;
    }


    public synchronized Class getRandomClass()
    {
        File directory = new File(this.directory);
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
                    e.printStackTrace();
                } catch (ReflectiveOperationException f)
                {
                    f.printStackTrace();
                }

            }

        }
        if(files.size()==0)
            return null;

        Random rand = new Random(); //instance of random class
        int int_random = rand.nextInt(files.size()-1);
        return files.get(int_random);
    }

    private List<IntElement> generate(long seed)
    {
        List<IntElement> result = new ArrayList<>();
        Random machine = new Random(seed);

        for(int i=0;i<listSize;i++)
        {
            IntElement element = new IntElement(machine.nextInt(upperBound), Character.toString((char)(65 + machine.nextInt(25))));
            result.add(element);
        }


        return result;
    }

    @Override
    public void run()
    {
        while(isAlive == true)
        {
            AbstractIntSorter sorter = null;
            Class<? extends AbstractIntSorter>  clazz=null;
            try {
                clazz = getRandomClass();
                Constructor<? extends AbstractIntSorter> cons;
                cons = clazz.getDeclaredConstructor();

                sorter = cons.newInstance();
            } catch (ReflectiveOperationException e) {
                System.err.println("Constructor not accessible");
                System.exit(1);
            }

            if(sorter!=null && clazz!=null)
            {

                Random machine = new Random();

                long seed = machine.nextInt(listSize);

                if(cache.cointains(seed)==false)
                {
                    synchronized (this)
                    {
                        System.out.println("Thread: "+ID+" sorts using: "+clazz.getName() +" "+ seed);
                    }

                    List<IntElement> list = generate(seed);

                    cache.add(seed,sorter.solve(list));
                }
                else
                {
                    synchronized (this)
                    {
                        System.out.println("Thread: "+ID+" hits "+ seed);
                    }
                }


            }

            synchronized (this)
            {
                System.out.println("Thread: "+ID+" done");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void start ()
    {
        if (thr == null)
        {
            setAlive(true);
            thr = new Thread (this, "Thread "+ID);
            thr.start ();
        }
    }
    public void stop()
    {
        if (thr != null)
        {
            try
            {
                setAlive(false);
                thr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
