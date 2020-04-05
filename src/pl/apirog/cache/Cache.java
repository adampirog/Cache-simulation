package pl.apirog.cache;

import pl.apirog.sortersFrame.IntElement;

import java.util.List;

public class Cache
{
    SoftHashMap map = new SoftHashMap();

    int missGlobal = 0;
    int missLocal= 0;

    int referenceGlobal= 0;
    int referenceLocal= 0;

    public Cache()
    {
        map = new SoftHashMap();
        missGlobal = 0;
        missLocal=0;
        referenceGlobal = 0;
        referenceLocal = 0;
    }

    public synchronized void hit()
    {
        referenceLocal++;
        referenceGlobal++;
    }

    public synchronized void miss()
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

    //returns true if record already exists in cache
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
}
