package com.hc.gear;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.hc.gear.parse.GearXMLParser;

/**
 * Instance of equipment parsed by {@link GearXMLParser}
 */
public class GenericEquipment implements AbstractEquipment {
    private String name;
    private String color;
    private Map<String, Integer> tmpmaterials = new HashMap<>();
    private SortedMap<AbstractEquipment, Integer> materials;
    private String type;

    private int xmlLineNumber;

    public GenericEquipment(String name, String color,
            Map<String, Integer> materials, String type) {

        if (name == null || name.trim().isEmpty() || color == null
                || color.trim().isEmpty()) {

            throw new NullPointerException();
        }
        if (materials == null) {
            materials = new HashMap<>();
        }

        this.type = type == null ? null : type.trim();
        this.name = name.trim();
        this.color = color.trim();
        tmpmaterials = materials;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String color() {
        return color;
    }

    /**
     * Sets the internal state of this equipment based on {@code gear}
     *
     * @param equipment
     *            all existing gear.
     */
    public void setup(Map<String, AbstractEquipment> equipment) {
        if (tmpmaterials == null || materials != null) {
            throw new IllegalStateException("Already setup");
        }
        materials = new TreeMap<>(new AbstractEquipmentNameComparator());
        _setup(equipment);
        tmpmaterials = null;
    }

    /**
     * Sets the internal state of this equipment based on {@code gear}
     *
     * @param equipment
     *            all existing gear.
     */
    private void _setup(Map<String, AbstractEquipment> equipment) {

        for (Map.Entry<String, Integer> entry : tmpmaterials.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();

            AbstractEquipment material = equipment.get(name);
            if (material == null) {
                String msg = String
                        .format("Unknown craft material (%s) defined in line %d for equipment %s",
                                name, xmlLineNumber, this.name);
                throw new IllegalStateException(msg);
            }
            materials.put(material, quantity);
        }
    }

    @Override
    public Map<AbstractEquipment, Integer> materials() {
        return Collections.unmodifiableMap(materials);
    }

    @Override
    public boolean requires(AbstractEquipment material) {
        if (material == null) {
            return false;
        }

        if (materials.containsKey(material)) {
            return true;
        }

        for (AbstractEquipment mat : materials.keySet()) {
            if (mat.isRaw()) {
                continue;
            }
            if (requires(mat)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRaw() {
        return materials.isEmpty();
    }

    /**
     * @return xml line number where this equipment was described
     */
    public int getXmlLineNumber() {
        return xmlLineNumber;
    }

    /**
     * Sets the {@code xmlLineNumber} where this equipment was described
     *
     * @param xmlLineNumber
     */
    public void setXmlLineNumber(int xmlLineNumber) {
        this.xmlLineNumber = xmlLineNumber;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GenericEquipment)) {
            return false;
        }
        GenericEquipment that = (GenericEquipment) obj;
        return name.equals(that.name);
    }

    @Override
    public String toString() {

        String s = String.format("Name: %s\tColor:%s", name, color);
        return s;
    }

}