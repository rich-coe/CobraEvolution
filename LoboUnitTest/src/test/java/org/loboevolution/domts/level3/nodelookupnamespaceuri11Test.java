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
import org.loboevolution.html.node.DOMImplementation;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.DocumentType;
import org.loboevolution.html.node.Element;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Invoke lookupNamespaceURI on an imported new Element node with a namespace URI and prefix
 * in a new Document and using the parents prefix as an argument, verify if the namespaceURI
 * returned is a valid namespaceURI of the parent.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-lookupNamespaceURI">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-lookupNamespaceURI</a>
 */
public class nodelookupnamespaceuri11Test extends LoboUnitTest {
@Test
public void runTest(){
        Document doc;
        DOMImplementation domImpl;
        Document newDoc;
        Element elem;
        Element importedNode;
        String namespaceURI;
        DocumentType nullDocType=null;

        Element docElem;
        String rootNS;
        String rootName;
        doc=sampleXmlFile("hc_staff.xml");
        docElem=doc.getDocumentElement();
        rootNS=docElem.getNamespaceURI();
        rootName=docElem.getTagName();
        domImpl=doc.getImplementation();
        newDoc=domImpl.createDocument(rootNS,rootName,nullDocType);
        elem=doc.createElementNS("http://www.w3.org/1999/xhtml","dom3:p");
        importedNode=(Element)newDoc.importNode(elem,true);
        namespaceURI=importedNode.lookupNamespaceURI("dom3");
        assertEquals("nodelookupnamespaceuri11","http://www.w3.org/1999/xhtml",namespaceURI);
        }
        }

