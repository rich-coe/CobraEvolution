
/*
 * MIT License
 *
 * Copyright (c) 2014 - 2023 LoboEvolution
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Contact info: ivan.difrancesco@yahoo.it
 */

package org.loboevolution.domts.level2;

import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.node.Attr;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.NamedNodeMap;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The method removeNamedItemNS removes a node specified by local name and namespace
 * <p>
 * Create a new element node and add 2 new attribute nodes to it that have the same localName
 * but different namespaceURI's.  Remove the first attribute node from the namedNodeMap of the
 * new element node and check to see that the second attribute still exists.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-D58B193">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-D58B193</a>
 */
public class namednodemapremovenameditemns03Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        NamedNodeMap attributes;
        Element element;
        Attr attribute;
        Attr newAttribute;
        Attr attribute1;
        Attr attribute2;
        String nodeName;
        doc = sampleXmlFile("staffNS.xml");
        element = doc.createElementNS("http://www.w3.org/DOM/Test", "root");
        attribute1 = doc.createAttributeNS("http://www.w3.org/DOM/L1", "L1:att");
        
        newAttribute = element.setAttributeNodeNS(attribute1);
        attribute2 = doc.createAttributeNS("http://www.w3.org/DOM/L2", "L2:att");
        
        newAttribute = element.setAttributeNodeNS(attribute2);
        attributes = element.getAttributes();
        attribute = (Attr)attributes.removeNamedItemNS("http://www.w3.org/DOM/L1", "att");
        attribute = (Attr) attributes.getNamedItemNS("http://www.w3.org/DOM/L2", "att");
        nodeName = attribute.getNodeName();
        assertEquals("L2:att", nodeName, "namednodemapremovenameditemns02");
    }
}

