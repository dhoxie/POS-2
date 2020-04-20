package com.scottsdaleair.utils;

import com.scottsdaleair.data.*;

/**
 * A class for data conversion methods.
 */
public class DataConverter {

    /**
     * Converts an Object[] to an {@link Invoice}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Invoice}[]
     */
    public static Customer[] objToCust(Object[] objs) {
        Customer[] custs = new Customer[objs.length];
        for (int i = 0; i < objs.length; i++) {
            custs[i] = (Customer) objs[i];
        }
        return custs;
    }

    /**
     * Converts an Object[] to an {@link Invoice}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Invoice}[]
     */
    public static Invoice[] objToInv(Object[] objs) {
        Invoice[] invs = new Invoice[objs.length];
        for (int i = 0; i < objs.length; i++) {
            invs[i] = (Invoice) objs[i];
        }
        return invs;
    }

    /**
     * Converts an Object[] to an {@link Kit}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Kit}[]
     */
    public static Kit[] objToKit(Object[] objs) {
        Kit[] kits = new Kit[objs.length];
        for (int i = 0; i < objs.length; i++) {
            kits[i] = (Kit) objs[i];
        }
        return kits;
    }

    /**
     * Converts an Object[] to an {@link Part}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Part}[]
     */
    public static Part[] objToPrt(Object[] objs) {
        Part[] pts = new Part[objs.length];
        for (int i = 0; i < objs.length; i++) {
            pts[i] = (Part) objs[i];
        }
        return pts;
    }

    /**
     * Converts an Object[] to an {@link Service}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Service}[]
     */
    public static Service[] objToSvc(Object[] objs) {
        Service[] svcs = new Service[objs.length];
        for (int i = 0; i < objs.length; i++) {
            svcs[i] = (Service) objs[i];
        }
        return svcs;
    }

    /**
     * Converts an Object[] to an {@link Vehicle}[].
     * 
     * @param objs Object[] to convert
     * @return Converted {@link Vehicle}[]
     */
    public static Vehicle[] objToVehc(Object[] objs) {
        Vehicle[] vehcs = new Vehicle[objs.length];
        for (int i = 0; i < objs.length; i++) {
            vehcs[i] = (Vehicle) objs[i];
        }
        return vehcs;
    }
}