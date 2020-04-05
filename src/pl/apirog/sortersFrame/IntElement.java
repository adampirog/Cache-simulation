package pl.apirog.sortersFrame;
/**
 * Klasa implementująca element zawierający wartość całkowitoliczbową.
 *
 * @author Adam Piróg
 */
public class IntElement implements IElement
{
    /**
     * pole przechowujące nazwę elementu
     */
   private String name;

    /**
     * pole przechowujące wartość elementu w formie całkowitoliczbowej
     */
   private int value;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public double getValue()
    {
        return (double)value;
    }

    @Override
    public void setValue(double val)
    {
        value = (int)val;
    }

    @Override
    public void setName(String string)
    {
        name = string;
    }


    /**
     * Konstruktor uproszczony. Nazwa pozostaje pusta.
     * @param value - wartość całkowitoliczbowa elementu
     */
    public IntElement(int value)
    {
        name = "";
        this.value=value;
    }

    /**
     * Konstruktor rozszerzony
     * @param name - nazwa elementu
     * @param value - wartość całkowitoliczbowa elementu
     */
    public IntElement( int value, String name)
    {
        this.name=name;
        this.value=value;
    }



    /**
     * @return zwraca opis elementu: wartość + nazwa
     */
    @Override
    public String toString()
    {
        return Integer.toString(value) +"   "+ name;
    }


}
