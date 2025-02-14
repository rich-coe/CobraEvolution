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


import org.htmlunit.cssparser.dom.DOMException;
import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Invoke the renameNode method on a new document node and try to rename the default
 * attribute "dir"
 * Check if a WRONG_DOCUMENT_ERR gets thrown.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode</a>
 */
public class documentrenamenode08Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Document newDoc;
        DOMImplementation domImpl;
        Element element;
        Attr attr;
        HTMLCollection childList;
        DocumentType nullDocType = null;

        Element docElem;
        String docElemNS;
        String docElemName;
        doc = sampleXmlFile("hc_staff.xml");
        childList = doc.getElementsByTagName("p");
        element = (Element) childList.item(3);
        attr = element.getAttributeNodeNS("*", "dir");
        domImpl = doc.getImplementation();
        docElem = doc.getDocumentElement();
        docElemNS = docElem.getNamespaceURI();
        docElemName = docElem.getTagName();
        newDoc = domImpl.createDocument(docElemNS, docElemName, nullDocType);

        boolean success = false;
        try {
            newDoc.renameNode(attr, "http://www.w3.org/XML/1998/namespace", "xml:lang");
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.WRONG_DOCUMENT_ERR);
        }
        assertTrue(success, "documentrenamenode08_WRONG_DOCUMENT_ERR");

    }
}

