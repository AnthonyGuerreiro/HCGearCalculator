package com.hc.test.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hc.test.gear.AbstractEquipmentTest;

public class TestParseGearXML extends AbstractEquipmentTest {

    /**
     * Tests if Gloves of Haste item was correctly parsed from the xml
     */
    @Test
    public void testParseGlovesOfHaste() {
        String glovesOfHasteString = "Name: Gloves of Haste\tColor:green";
        assertEquals(glovesOfHasteString, glovesOfHaste.toString());
    }

    /**
     * Tests if Deceiver's Sword was correctly parsed from the xml
     */
    @Test
    public void testParseDeceiversSword() {
        String deceiversSwordString = "Name: Deceiver's Sword\tColor:orange";
        assertEquals(deceiversSwordString, deceiversSword.toString());
    }

}
