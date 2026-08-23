package mango.ast.property.properties;

import mango.ast.property.Property;

public class TextProperty extends Property<String> {
    public TextProperty(String text) {
        super("", text);
    }
}
