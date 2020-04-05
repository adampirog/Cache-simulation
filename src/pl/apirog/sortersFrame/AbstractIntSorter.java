package pl.apirog.sortersFrame;

import java.util.List;
/**
 * Klasa abstrakcyjna będąca bazą dla klas sortujących.
 *Sortuje wyłącznie elementy całkowitoliczbowe ({@link IntElement})
 *
 * @author Adam Piróg
 */
public abstract class AbstractIntSorter
{
    /**
     * @param list lista elementów całkowitoliczbowych przeznaczonych do sortowania. Przyjmuje wyłącznie argumenty całkowitoliczbowe ({@link IntElement})
     * @return lista posortowanych elementów. Jeśli algorytm działa w miejscu (zobacz: {@link AbstractIntSorter#isInSitu()}) lista będąca argumentem również zostanie posortowana.
     */
    abstract public List<IntElement> solve(List<IntElement> list);

    /**
     * @return krótki opis zawierający nazwę algorytmu sortującego
     */
    abstract public String description();
    /**
     * @return true - jeśli podana metoda sortowania jest stabilna, false - jeśli niestabilna
     */
    abstract public boolean isStable();
    /**
     * @return true - jeśli sortowanie następuje w miejscu, false - jeśli wymaga dodatkowej pamięci
     */
    abstract public boolean isInSitu();



}
