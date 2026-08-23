package mango.ast.property.properties;

import mango.ast.property.Property;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String name, Boolean toggled) {
        super(name, toggled);
    }
}
