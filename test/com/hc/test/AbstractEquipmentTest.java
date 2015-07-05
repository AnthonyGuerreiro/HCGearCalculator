package com.hc.test;

import static com.hc.gear.Equipment.DECEIVERS_SWORD;
import static com.hc.gear.Equipment.GLOVES_OF_HASTE;
import static com.hc.gear.Equipment.HELM_OF_MAGNUS_THORNE;
import static com.hc.gear.Equipment.KRELN_HORN_REEL_FRAGMENT;
import static com.hc.gear.Equipment.ROUGH_HEWN_AXE_FRAGMENT;
import static com.hc.gear.Equipment.SANADORIAN_WARDEN_HELM;
import static com.hc.gear.Equipment.SILVER_LANCE_FRAGMENT;
import static com.hc.gear.Equipment.TITANS_AXE_FRAGMENT;
import static com.hc.gear.Equipment.TUSK_SIGIL;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hc.gear.AbstractEquipment;
import com.hc.gear.parse.GearXMLParser;

public class AbstractEquipmentTest {

    private Map<String, AbstractEquipment> gear;

    protected final AbstractEquipment deceiversSword;
    protected final AbstractEquipment glovesOfHaste;
    protected final AbstractEquipment titansAxeFragment;
    protected final AbstractEquipment krelnHornReelFragment;
    protected final AbstractEquipment roughHewnAxeFragment;
    protected final AbstractEquipment tuskSigil;
    protected final AbstractEquipment helmofMagnusThorne;
    protected final AbstractEquipment silverLanceFragment;
    protected final AbstractEquipment sanadorianWardenHelm;

    public AbstractEquipmentTest() {
        gear = getParser().gear();

        deceiversSword = gear.get(DECEIVERS_SWORD);
        glovesOfHaste = gear.get(GLOVES_OF_HASTE);
        titansAxeFragment = gear.get(TITANS_AXE_FRAGMENT);
        krelnHornReelFragment = gear.get(KRELN_HORN_REEL_FRAGMENT);
        roughHewnAxeFragment = gear.get(ROUGH_HEWN_AXE_FRAGMENT);
        tuskSigil = gear.get(TUSK_SIGIL);
        helmofMagnusThorne = gear.get(HELM_OF_MAGNUS_THORNE);
        silverLanceFragment = gear.get(SILVER_LANCE_FRAGMENT);
        sanadorianWardenHelm = gear.get(SANADORIAN_WARDEN_HELM);
    }

    private GearXMLParser getParser() {
        try {
            return new GearXMLParser("Gear.xml");
        } catch (ParserConfigurationException | SAXException
                | IOException e) {
            // assume the file is there and has correct syntax
            e.printStackTrace();
            return null;
        }
    }

    protected Map<String, AbstractEquipment> getGear() {
        return Collections.unmodifiableMap(gear);
    }
}
