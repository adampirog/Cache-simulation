package pl.apirog.cache;

import pl.apirog.sortersFrame.IntElement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

public class SoftHashMap extends AbstractMap
{
    private final Map hash = new HashMap();

    private final ReferenceQueue queue = new ReferenceQueue();



    public Object get(Object key)
    {
        Object result = null;

        SoftReference soft_ref = (SoftReference)hash.get(key);
        if (soft_ref != null)
        {
            result = soft_ref.get();
            if (result == null)
            {
                hash.remove(key);
            }

        }
        return result;
    }

    public boolean contains(Object key)
    {

        SoftReference soft_ref = (SoftReference)hash.get(key);
        if (soft_ref != null)
        {
            Object result = soft_ref.get();
            if (result == null)
            {
                hash.remove(key);
                return false;
            }
            else
            {
                return true;
            }

        }
        else
        {
            return false;
        }

    }

    private static class SoftValue extends SoftReference
    {
        private final Object key;

        private SoftValue(Object k, Object key, ReferenceQueue q)
        {
            super(k, q);
            this.key = key;
        }
    }

    private void cleanQueue()
    {
        SoftValue sv;
        while ((sv = (SoftValue)queue.poll()) != null)
        {
            hash.remove(sv.key);
        }
    }

    public Object put(Object key, Object value)
    {
        cleanQueue();
        return hash.put(key, new SoftValue(value, key, queue));
    }
    public Object remove(Object key)
    {
        cleanQueue();
        return hash.remove(key);
    }
    public void clear()
    {
        cleanQueue();
        hash.clear();
    }
    public int size()
    {
        cleanQueue();
        return hash.size();
    }

    public Set<Long> keySet()
    {
        return  hash.keySet();
    }

    public List<List<IntElement>> valueList()
    {
        List<List<IntElement>> result = new ArrayList<>();

        for(Long key : keySet())
        {
            result.add((List<IntElement>) get(key));
        }
        return result;

    }



    @Override
    public Set<Entry> entrySet()
    {
        return hash.entrySet();
    }
}
