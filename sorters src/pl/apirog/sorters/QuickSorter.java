package pl.apirog.sorters;

import pl.apirog.sortersFrame.AbstractFloatSorter;
import pl.apirog.sortersFrame.IElement;
import pl.apirog.sortersFrame.IntElement;

import java.util.Collections;
import java.util.List;

/**
 * Klasa sortująca algorytmem sortowania szybkiego (Quick sort)
 *
 * @author Adam Piróg
 */
public class QuickSorter extends AbstractFloatSorter
{
    /**
     * Metoda pomocnicza wykorzystywana w kolejnych wywołaniach funkcji, ma na celu "częściowe" posortowanie listy.
     * Jako element rozdzielający (pivot) jest wybierany ostatni element z listy.
     *
     * @param list lista elementów do posortowania częściowego.
     * @param start indeks początkowy zakresu do posortowania
     * @param end indeks końcowy zakresu do posortowania
     * @return pivot - wartość całkowitoliczbowa będąca indeksem elementu rozdzielającego
     */
    private int part(List<IElement> list, int start, int end)
    {
        double pivot = list.get(end).getValue();
        int i = (start-1);

        for (int j=start; j<end; j++)
        {

            if (list.get(j).getValue() < pivot)
            {
                i++;
                Collections.swap(list,i,j);

            }
        }


        Collections.swap(list,i+1,end);

        return i+1;
    }

    /**
     * Główna metoda sortująca.
     * Jest wywoływana rekurencyjnie w kolejnych etapach algorytmu.
     *
     * @param list lista elementów do posortowania
     * @param start indeks początkowy zakresu do posortowania
     * @param end indeks końcowy zakresu do posortowania
     */
    private void quickSort(List<IElement> list, int start, int end)
    {
        if (start < end)
        {
            int pivot = part(list, start, end);

            quickSort(list, start, pivot - 1);
            quickSort(list, pivot + 1, end);
        }
    }

    @Override
    public List<IElement> solve2(List<IElement> list)
    {
        quickSort(list, 0, list.size()-1);

        return list;
    }



    @Override
    public List<IntElement> solve(List<IntElement> list)
    {
        List<IElement> list2 = (List) list;

        quickSort(list2, 0, list.size()-1);

        List<IntElement> list3 = (List) list2;

        return list3;
    }

    @Override
    public String description() { return "Quick sort"; }

    @Override
    public boolean isStable() { return false; }

    @Override
    public boolean isInSitu() { return true; }
}
