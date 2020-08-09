package pl.apirog.sorters;

import pl.apirog.sortersFrame.AbstractFloatSorter;
import pl.apirog.sortersFrame.IElement;
import pl.apirog.sortersFrame.IntElement;

import java.util.Collections;
import java.util.List;

/**
 * Klasa sortująca algorytmem sortowania przez wybieranie (Selection sort)
 *
 * @author Adam Piróg
 */
public class SelectSorter extends AbstractFloatSorter
{
    /**
     * {@inheritDoc}
     */


    @Override
    public List<IElement> solve2(List<IElement> list)
    {
        int n = list.size();


        for (int i = 0; i < n-1; i++)
        {

            int min = i;
            for (int j = i+1; j < n; j++)
                if (list.get(j).getValue()< list.get(min).getValue())
                    min = j;


            Collections.swap(list,i,min);
        }

        return list;
    }

    @Override
    public List<IntElement> solve(List<IntElement> list)
    {
        List<IElement> list2 = (List) list;

        solve2(list2);

        List<IntElement> list3 = (List) list2;

        return list3;
    }

    @Override
    public String description()
    {
        return "Selection sort";
    }

    @Override
    public boolean isStable()
    {
        return false;
    }

    @Override
    public boolean isInSitu()
    {
        return true;
    }
}
