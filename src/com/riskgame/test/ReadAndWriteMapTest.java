package com.riskgame.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.riskgame.service.ReadAndWriteMap;

 
public class ReadAndWriteMapTest {
	
	 ReadAndWriteMap readAndWriteMap;
	  private String validMap;
	  private String invalidMap;
//	  private String validContinentsData;
//	  private String invalidContinentsData;
	  public String Map;
	  
	public ReadAndWriteMapTest() {
	    readAndWriteMap = new ReadAndWriteMap();
	    
        validMap = "src/com/riskgame/maps/validMap.map";
        invalidMap = "src/com/riskgame/maps/invalidMap.map";
//        validContinentsData = "Northern Africa=4";
//        invalidContinentsData= "Northern Africa";
           Map="Author=Anusha";
        }
    
    @Test
    public void isValidMap() throws IOException {
        assertTrue(readAndWriteMap.uploadMap(validMap));
    }
    
    @Test
    public void isInvalid() throws IOException {
        assertFalse(readAndWriteMap.uploadMap(invalidMap));
    }
//	@Test
//    public void testvalidatecontinents() throws IOException {
//    	assertTrue(readAndWriteMap.validatecontinents(validContinentsData));
//    }
//    @Test
//    public void testvalidatemetadata() {
//    	assertTrue(readAndWriteMap.validatemetadata(Map));
//    }
}


