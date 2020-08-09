package pl.apirog.sorters;

import pl.apirog.sortersFrame.AbstractFloatSorter;
import pl.apirog.sortersFrame.IElement;
import pl.apirog.sortersFrame.IntElement;

import java.util.List;

/**
 * Klasa sortująca algorytmem sortowania przez wstawianie (Insert sort)
 *
 * @author Adam Piróg
 */
public class InsertSorter extends AbstractFloatSorter
{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IElement> solve2(List<IElement> list)
    {
        int n = list.size();


        for (int i = 1; i < n; ++i)
        {
            IElement key = list.get(i);
            int j = i - 1;


            while (j >= 0 && list.get(j).getValue() > key.getValue())
            {

                list.set(j+1,list.get(j));
                j = j - 1;
            }
            list.set(j + 1,key);
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
        return "Insert sort";
    }

    @Override
    public boolean isStable()
    {
        return true;
    }

    @Override
    public boolean isInSitu()
    {
        return true;
    }


}
