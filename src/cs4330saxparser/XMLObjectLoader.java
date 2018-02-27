/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4330saxparser;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Trevor
 */
public class XMLObjectLoader {
    
    private static int nodeI;
    private static Stack<Integer> parentStack;
    
    public static XMLObjectList load(File file) {
        
        XMLObjectList xmlObjects = new XMLObjectList();
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                XMLObject obj = null;
                String title = "";
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    
                    ++nodeI;
                    int parentDepth = parentStack.peek();
                    
                    obj = new XMLObject();
                    obj.setTitle(qName);
                    
                    obj.setNodeIndex(nodeI);
                    obj.setParentIndex(parentDepth);
                    
                    parentStack.push(nodeI);
                    xmlObjects.addObject(obj);
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) {
                    parentStack.pop();
                    nodeI--;
                    // previously, they were added to the list here
                }
                
                @Override
                public void characters(char ch[], int start, int length) {
                    if (obj != null) {
                        obj.addData(new String(ch, start, length));
                    }
                }
                
                @Override
                public void startDocument() {
                    nodeI = -1;
                    parentStack = new Stack<>();
                    parentStack.push(nodeI);
                }
                
            };
            
            saxParser.parse(file.getAbsoluteFile(), handler);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLObjectLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return xmlObjects;
    }
    
}
