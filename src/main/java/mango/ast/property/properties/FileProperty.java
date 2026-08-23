package mango.ast.property.properties;

import mango.ast.property.Property;
import lombok.Getter;

@Getter
public class FileProperty extends Property<String> {
    private final String extensionFilter;

    public FileProperty(String name, String path, String extensionFilter) {
        super(name, path);
        this.extensionFilter = extensionFilter;
    }

    public FileProperty(String name, String path) {
        super(name, path);
        this.extensionFilter = "";
    }
}
