/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4330saxparser;

import java.util.ArrayList;

/**
 *
 * @author Trevor
 */
public class XMLObjectList {
    
    private ArrayList<XMLObject> objects;
    
    public XMLObjectList() {
        objects = new ArrayList<>();
    }
    
    public ArrayList<XMLObject> getObjects() {
        return objects;
    }
    
    public void addObject(XMLObject xo) {
        objects.add(xo);
    }
    
}
