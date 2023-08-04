package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tea")
public class Tea {

    @XmlAttribute
    public String name;

    public String type;

    public Tea() {

    }
    public Tea(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tea{" + "name='" + name + '\''
                + ", type='" + type + '\'' + '}';
    }

}
