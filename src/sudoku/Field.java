package sudoku;

public class Field {
    
    // Field instance variables.
    private int value;
    private final boolean initial;

    /**
     * Constructor to create an "empty" field instance
     * (empty value and not initial).
     */
    public Field() {
        this.value = GameGrid.EMPTY_VAL;
        this.initial = false;
    }

    /**
     * Constructor to create a Field instance with the specified
     * value.
     * @param value - The value of the instance.
     * @param initial - Whether the Field instance is initial.
     */
    public Field(int value, boolean initial) {
        this.value = value;
        this.initial = initial;
    }

    /**
     * Getter for the value of the Field.
     * @return Returns the value.
     */    
    public int getValue() {
        return value;
    }

    /**
     * Setter for the value of the Field.
     * @param value - The value to set.
     */       
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for if the Field is initial.
     * @return Returns if the Field is initial.
     */
    public boolean isInitial() {
        return initial;
    }

    /**
     * Returns a string representation of a Field.
     * @return Returns a string representation of a Field.
     */    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        if (initial) {
            sb.append("'");
        }
        return sb.toString();
    }
}
