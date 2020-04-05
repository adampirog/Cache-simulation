package pl.apirog.sortersFrame;

/**
 * Klasa implementująca element zawierający wartość zmiennoprzecinkową.
 *
 * @author Adam Piróg
 */
public class FloatElement implements IElement
{

    /**
     * pole przechowujące nazwę elementu
     */
    private String name;

    /**
     * pole przechowujące wartość elementu w formie zmiennoprzecinkowej
     */
    private float value;

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
        return value;
    }

    @Override
    public void setValue(double val) { value = (float) val; }

    @Override
    public void setName(String aString)
    {
        name = aString;
    }

    /**
     * Konstruktor uproszczony. Nazwa pozostaje pusta.
     * @param value wartość całkowitoliczbowa elementu
     */
    public FloatElement(float value)
    {
        name = "";
        this.value=value;
    }

    /**
     * Konstruktor rozszerzony
     * @param name nazwa elementu
     * @param value wartość całkowitoliczbowa elementu
     */
    public FloatElement( float value, String name)
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
        return Float.toString(value) +"   "+ name;
    }


}
