package pl.apirog.cache;

import pl.apirog.sortersFrame.IntElement;

import java.util.List;
import java.util.Set;

public class Cache
{
    private SoftHashMap map = new SoftHashMap();

    volatile private int missGlobal = 0;
    volatile private int missLocal= 0;

    volatile private int referenceGlobal= 0;
    volatile private int referenceLocal= 0;

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

    public synchronized boolean contains( Object key)
    {
        boolean result = map.contains(key);

        if(result)
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
        float result = (((float)missLocal/referenceLocal) * 100);
        resetLocal();
        return result;
    }

    public synchronized float globalMissPercent()
    {
        return (((float)missGlobal/referenceGlobal) *100);
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
