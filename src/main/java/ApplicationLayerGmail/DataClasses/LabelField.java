package ApplicationLayerGmail.DataClasses;

public class LabelField {
    private String label;
    private String value;


    public LabelField(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "{label='" + this.label + '\'' + ", value='" + this.value + '\'' + '}';
    }

    @Override
    public boolean equals(Object labelFld) {
        LabelField labelField = (LabelField) labelFld;
        if (this.label.equals(labelField.label) && this.value.equals(labelField.value)) return true;
        else return false;
    }
}