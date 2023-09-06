public class Field {
    
    private int value;
    private final boolean initial;

    public Field() {
        this.value = 0;
        this.initial = false;
    }

    public Field(int value, boolean initial) {
        this.value = value;
        this.initial = initial;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isInitial() {
        return initial;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        if (initial) {
            sb.append("'");
        }
        return sb.toString();
    }
}
