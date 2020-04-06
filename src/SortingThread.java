import pl.apirog.cache.Cache;
import pl.apirog.sortersFrame.AbstractIntSorter;
import pl.apirog.sortersFrame.IntElement;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortingThread implements Runnable
{
    int ID;
    Thread thr;
    volatile boolean isAlive = false;
    //How big the list is
    private int listSize = 100;
    //How big list elements are
    private int upperBound = 1000;
    //Sleep how long after each cycle
    private int sleepTime = 2000;
    private Cache cache;

    public SortingThread(Cache cache, int ID)
    {
        this.cache = cache;
        this.ID=ID;
    }

    private void setAlive(boolean value)
    {
        isAlive = value;
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
                clazz = Main.getRandomClass();
                Constructor<? extends AbstractIntSorter> cons;
                cons = clazz.getDeclaredConstructor();

                sorter = cons.newInstance();
            } catch (ReflectiveOperationException e)
            {
                //System.err.println("Constructor not accessible");
                //System.exit(1);
            }catch(NullPointerException f)
            {

            }

            if(sorter!=null && clazz!=null)
            {

                Random machine = new Random();

                long seed = machine.nextInt(listSize);

                if(cache.contains(seed)==false)
                {
                    Main.safePrint("Thread: "+ID+" sorts using: "+clazz.getName() +" seed: "+ seed);
                    List<IntElement> list = generate(seed);
                    cache.add(seed,sorter.solve(list));
                }
                else
                {
                        Main.safePrint("Thread: "+ID+" hits seed: "+ seed);
                }


            }
            else
            {
                Main.safePrint("No accessible sorter");
            }

            try {
                Thread.sleep(sleepTime);
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
