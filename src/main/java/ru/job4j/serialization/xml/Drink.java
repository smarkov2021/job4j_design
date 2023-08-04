package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "drink")
public class Drink {

    @XmlAttribute
    public boolean isAlcohol;
    public int volume;
    public String name;
    public Tea tea;
    public String[] compositions;

    public Drink() {

    }

    @Override
    public String toString() {
        return "Drink{" + "isAlcohol=" + isAlcohol + ", volume=" + volume
                + ", name='" + name + '\'' + ", tea=" + tea
                + ", compositions=" + Arrays.toString(compositions) + '}';
    }

    public Drink(boolean isAlcohol, int volume, String name, Tea tea, String[] compositions) {
        this.isAlcohol = isAlcohol;
        this.volume = volume;
        this.name = name;
        this.tea = tea;
        this.compositions = compositions;
    }

    public static void main(String[] args) throws JAXBException {

        final Drink drink = new Drink(false, 500, "Tea With Ice",
                new Tea("Lipton", "Black"), new String[]{"Water", "Tea", "Sugar", "Ice"});

        JAXBContext context = JAXBContext.newInstance(Drink.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(drink, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Drink result = (Drink) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
