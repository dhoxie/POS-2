package com.scottsdaleair;

import com.scottsdaleair.controller.DatabaseGetter;
import com.scottsdaleair.data.Invoice;
import junit.framework.TestCase;
import org.junit.Test;

// Data Types
import com.scottsdaleair.data.*;

public class DatabaseTest extends TestCase {

    @Test
    public void testGetAllCustomers(){
        Customer[] allCustomers = DatabaseGetter.getAllCustomers();
        assertTrue(allCustomers.length != 0);
    }

    @Test
    public void testQueryCustomers(){
        Customer[] someCustomers = DatabaseGetter.queryCustomers("fName", "Kayla");

        int isAccurate = 0;
        for(Customer c : someCustomers){
            if(c.getFname().equals("Kayla")){
                isAccurate++;
            }
        }

        assertTrue(isAccurate == someCustomers.length);
    }

    @Test
    public void testGetAllInvoices(){
        Invoice[] allInvoices = DatabaseGetter.getAllInvoices();
        assertTrue(allInvoices.length != 0);
    }

    @Test
    public void testQueryInvoices(){
        Invoice[] someInvoices = DatabaseGetter.queryInvoices("id", "____");

        assertTrue(someInvoices.length == 1 && someInvoices[0].getId() == "111"); //change id value to match
    }

    @Test
    public void testGetAllKits(){
        Kit[] allKits = DatabaseGetter.getAllKits();
        assertTrue(allKits.length != 0);
    }

    @Test
    public void testQueryKits(){
        Kit[] someKits = DatabaseGetter.queryKits("id", "___");

        assertTrue(someKits.length == 1 && someKits[0].getId() == "111"); //change id value to match
    }

    @Test
    public void testGetAllParts(){
        Part[] allParts = DatabaseGetter.getAllParts();
        assertTrue(allParts.length != 0);
    }

    @Test
    public void testQueryParts(){
        Part[] someParts = DatabaseGetter.queryParts("partNum", "___");

        assertTrue(someParts.length == 1 && someParts[0].getPartNum() == "111");
    }

    @Test
    public void testGetAllServices(){
        Service[] allServices = DatabaseGetter.getAllServices();
        assertTrue(allServices.length != 0);
    }

    @Test
    public void testQueryServices(){
        Service[] someServices = DatabaseGetter.queryServices("id", "___");

        assertTrue(someServices.length == 1 && someServices[0].getId() == "111");
    }

    public void testGetAllVehicles(){
        Vehicle[] allVehicles = DatabaseGetter.getAllVehicles();
        assertTrue(allVehicles.length != 0);
    }

    @Test
    public void testQueryVehicles(){
        Vehicle[] someVehicles = DatabaseGetter.queryVehicles("vin", "___");

        assertTrue(someVehicles.length == 1 && someVehicles[0].getVin() == "111");
    }

}
