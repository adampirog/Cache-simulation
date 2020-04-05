package pl.apirog.sortersFrame;
/**
 * Interfejs będący szkieletem przyszłych klas sortujących.
 * Implementuje podstawowe metody, które powinny się znaleźć w każdej takiej klasie.
 *
 * @author Adam Piróg
 */
public interface IElement
{
    /**
     * @return  zwraca nazwę elementu
     */
     String getName();

    /**
     * @return zwraca wartość elementu w formie double dla zwiększenia elastyczności
     */
     double getValue();
    /**
     * @param value ustawia jako wartość elementu
     */
     void setValue(double value);
    /**
     * @param name ustawia jako nazwę elementu
     */
     void setName(String name);
}
