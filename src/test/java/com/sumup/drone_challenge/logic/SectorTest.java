package com.sumup.drone_challenge.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorTest {

    @Test
    public void testSectorInstantiation(){
        int maximumX = 10;
        int maximumY = 10;

        Sector sector = new Sector(maximumX, maximumY);

        assertEquals(maximumX, sector.getMaximumX());
        assertEquals(maximumY, sector.getMaximumY());
    }

}
