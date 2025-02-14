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

package org.loboevolution.domts.level3;


import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Attr;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Using isEqualNode check if 2 Attr nodes having the same nodeName and a null namespaceURI
 * attribute, one created using createAttributeNS and the other retreived from this document
 * are equal.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isEqualNode">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isEqualNode</a>
 */
public class nodeisequalnode15Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Attr attr1;
        Attr attr2;
        Element addrElement;
        HTMLCollection elementList;
        boolean isEqual;
        String nullNS = null;

        doc = sampleXmlFile("hc_staff.xml");
        elementList = doc.getElementsByTagName("acronym");
        addrElement = (Element) elementList.item(3);
        attr1 = addrElement.getAttributeNodeNS(nullNS, "title");
        attr2 = doc.createAttributeNS(nullNS, "title");
        attr2.setValue("Yes");
        isEqual = attr1.isEqualNode(attr2);
        assertTrue(isEqual, "nodeisequalnode15");
    }
}

