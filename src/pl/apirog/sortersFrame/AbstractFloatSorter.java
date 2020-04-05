package pl.apirog.sortersFrame;

import java.util.List;
/**
 * Klasa abstrakcyjna będąca bazą dla klas sortujących.
 *Sortuje zarówno elementy całkowitoliczbowe ({@link IntElement})), jak i zmiennoprzecinkowe ({@link FloatElement})
 *
 * @author Adam Piróg
 */
public abstract class AbstractFloatSorter extends AbstractIntSorter
{
    /**
     * @param list lista elementów przeznaczonych do sortowania. Przyjmuje sortuje zarówno elementy całkowitoliczbowe ({@link IntElement})), jak i zmiennoprzecinkowe ({@link FloatElement})
     * @return lista posortowanych elementów. Jeśli algorytm działa w miejscu (zobacz: {@link AbstractIntSorter#isInSitu()}) lista będąca argumentem również zostanie posortowana.
     */
    abstract public List<IElement> solve2(List<IElement> list);


}
