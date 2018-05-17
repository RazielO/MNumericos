package models;

/**
 * Clase modelo para poder hacer items en el ComboBox no seleccionables, permitiendo hacer el ComboBox
 * en categorias.
 * Contiene el constructor, el getter de selectable y el metodo toString que extiende de la clase Object.
 */

public class ComboBoxItem
{
    private final String name;
    private final boolean selectable;
    
    public ComboBoxItem(String name, boolean selectable)
    {
        this.name = name;
        this.selectable = selectable;
    }
    
    public boolean isSelectable()
    {
        return selectable;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
