package ApplicationLayerGmail.DataClasses;

public class IDField {
    private String id;
    private String value;


    public IDField(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getID() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "{id='" + this.id + '\'' + ", value='" + this.value + '\'' + '}';
    }

    @Override
    public boolean equals(Object iDFld) {
        IDField idField = (IDField) iDFld;
        if (this.id.equals(idField.id) && this.value.equals(idField.value)) return true;
        else return false;
    }
}