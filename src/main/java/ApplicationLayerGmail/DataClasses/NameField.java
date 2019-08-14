package ApplicationLayerGmail.DataClasses;

public class NameField {
    private String name;
    private String value;


    public NameField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "{name='" + this.name + '\'' + ", value='" + this.value + '\'' + '}';
    }

    @Override
    public boolean equals(Object nameFld) {
        NameField nameField = (NameField) nameFld;
        if (this.name.equals(nameField.name) && this.value.equals(nameField.value)) return true;
        else return false;
    }
}