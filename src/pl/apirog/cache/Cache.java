package pl.apirog.cache;

import pl.apirog.sortersFrame.IntElement;

import java.util.List;
import java.util.Set;

public class Cache
{
    private SoftHashMap map = new SoftHashMap();

    private int missGlobal = 0;
    private int missLocal= 0;

    private int referenceGlobal= 0;
    private int referenceLocal= 0;

    public Cache()
    {
        map = new SoftHashMap();
        missGlobal = 0;
        missLocal=0;
        referenceGlobal = 0;
        referenceLocal = 0;
    }

    private synchronized void hit()
    {
        referenceLocal++;
        referenceGlobal++;
    }

    private synchronized void miss()
    {
        missGlobal++;
        missLocal++;
        referenceGlobal++;
        referenceLocal++;
    }

    public synchronized void resetLocal()
    {
        missLocal=0;
        referenceLocal = 0;
    }

    public synchronized boolean cointains( Object key)
    {
        boolean result = map.contains(key);

        if(result==true)
            hit();
        else
            miss();

        return result;
    }


    public synchronized boolean add(Long seed, List<IntElement> list)
    {
        if(map.get(seed)==null)
        {
            miss();
            map.put(seed,list);
            return true;
        }
        else
        {
            hit();
            return false;
        }

    }

    public synchronized void remove(Long seed)
    {
        map.remove(seed);
    }

    public synchronized List<IntElement> get(Long seed)
    {
        List<IntElement> result = (List<IntElement>) map.get(seed);

        return result;
    }

    public synchronized int size()
    {
        return map.size();
    }

    public synchronized float localMissPercent()
    {
        return ((missLocal/referenceLocal) *100);
    }

    public synchronized float globalMissPercent()
    {
        return ((missGlobal/referenceGlobal) * 100);
    }

    public synchronized Set<Long> keySet()
    {
        return  map.keySet();
    }

    public synchronized List<List<IntElement>> valueList()
    {
        return  map.valueList();
    }
}
