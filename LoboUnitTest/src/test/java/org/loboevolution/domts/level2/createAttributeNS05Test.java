
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

import static org.junit.jupiter.api.Assertions.*;


/**
 * The "createAttributeNS(namespaceURI,qualifiedName)" method for a
 * Document should return a new Attr object given that all parameters are
 * valid and correctly formed.
 * <p>
 * Invoke method createAttributeNS(namespaceURI,qualifiedName) on this document with
 * parameters equal "http://www.ecommerce.org/" and "ecom:local"
 * respectively. Method should return a new Attr object whose name is "ecom:local".
 *
 * @author NIST
 * @author Mary Brady
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-1112119403">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-1112119403</a>
 */
public class createAttributeNS05Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        String namespaceURI = "http://www.ecommerce.org/";
        String qualifiedName = "econm:local";
        Document doc;
        Attr newAttr;
        String attrName;
        doc = sampleXmlFile("staffNS.xml");
        newAttr = doc.createAttributeNS(namespaceURI, qualifiedName);
        attrName = newAttr.getName();
        assertEquals(qualifiedName, attrName, "throw_Equals");
    }
}

