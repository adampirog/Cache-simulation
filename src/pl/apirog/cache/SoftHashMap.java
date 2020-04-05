package pl.apirog.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

public class SoftHashMap extends AbstractMap
{
    private final Map hash = new HashMap();

    private final int HARD_SIZE;

    private final LinkedList hardCache = new LinkedList();

    private final ReferenceQueue queue = new ReferenceQueue();


    public SoftHashMap(int hardSize) { HARD_SIZE = hardSize; }
    public SoftHashMap() { HARD_SIZE = 100; }



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
            } else
                {
                hardCache.addFirst(result);
                if (hardCache.size() > HARD_SIZE)
                {
                    hardCache.removeLast();
                }
            }
        }
        return result;
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
        hardCache.clear();
        cleanQueue();
        hash.clear();
    }
    public int size()
    {
        cleanQueue();
        return hash.size();
    }


    @Override
    public Set<Entry> entrySet()
    {
        throw new UnsupportedOperationException();
    }
}
