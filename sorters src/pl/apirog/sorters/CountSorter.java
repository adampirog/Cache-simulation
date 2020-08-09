package pl.apirog.sorters;

import pl.apirog.sortersFrame.AbstractIntSorter;
import pl.apirog.sortersFrame.IntElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa sortująca algorytmem sortowania przez zliczanie (Counting sort)
 *
 * @author Adam Piróg
 */
public class CountSorter extends AbstractIntSorter
{
    /**
     * {@inheritDoc}
     */

    @Override
    public List<IntElement> solve(List<IntElement> list)
    {
        int min = (int)list.get(0).getValue();
        int max = (int)list.get(0).getValue();
        int range, index,n=list.size();

        List<IntElement> result = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            result.add(new IntElement(0));
        }

        for(int i=1; i<n; i++)
        {
            if((int)list.get(i).getValue() > max)
                max = (int)list.get(i).getValue();
            if((int)list.get(i).getValue() < min)
                min = (int)list.get(i).getValue();
        }
        range = max - min + 1;
        int count[] = new int[range];

        for (int i = 0; i < n; i++)
        {
            count[(int)list.get(i).getValue() - min]++;
        }

        for (int i = 1; i < count.length; i++)
        {
            count[i] += count[i - 1];
        }

        //Od tylu dla zachowania stablinosci
        for (int i = n - 1; i >= 0; i--)
        {
            result.set((count[(int)list.get(i).getValue() - min ] -1),list.get(i));
            count[(int)list.get(i).getValue() - min]--;
        }


        return result;
    }

    @Override
    public String description()
    {
        return "Counting sort";
    }

    @Override
    public boolean isStable()
    {
        return true;
    }

    @Override
    public boolean isInSitu()
    {
        return false;
    }
}
