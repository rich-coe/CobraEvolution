
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

import org.htmlunit.cssparser.dom.DOMException;
import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Attr;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.NamedNodeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * The method setNamedItemNS adds a node using its namespaceURI and localName and
 * raises a WRONG_DOCUMENT_ERR if arg was created from a different document than the
 * one that created this map.
 * <p>
 * Retreieve the second element whose local name is address and its attribute into a named node map.
 * Do the same for another document and retreive its street attribute.  Call the setNamedItemNS
 * using the first namedNodeMap and the retreive street attribute of the second.  This should
 * raise a WRONG_DOCUMENT_ERR.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS</a>
 * @see <a href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=259">http://www.w3.org/Bugs/Public/show_bug.cgi?id=259</a>
 * @see <a href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=408">http://www.w3.org/Bugs/Public/show_bug.cgi?id=408</a>
 */
public class namednodemapsetnameditemns03Test extends LoboUnitTest {

    /**
     * Runs the test case.
     */
    @Test
    public void runTest() {
        Document doc;
        Document docAlt;
        NamedNodeMap attributes;
        NamedNodeMap attributesAlt;
        HTMLCollection elementList;
        HTMLCollection elementListAlt;
        Element element;
        Element elementAlt;
        Attr attr;
        String nullNS = null;

        doc = sampleXmlFile("staffNS.xml");
        elementList = doc.getElementsByTagNameNS("*", "address");
        element = (Element) elementList.item(1);
        attributes = element.getAttributes();
        docAlt = sampleXmlFile("staffNS.xml");
        elementListAlt = docAlt.getElementsByTagNameNS("*", "address");
        elementAlt = (Element) elementListAlt.item(1);
        attributesAlt = elementAlt.getAttributes();
        attr = (Attr) attributesAlt.getNamedItemNS(nullNS, "street");
        boolean success = false;

        try {
            attributesAlt.removeNamedItemNS(nullNS, "street");
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.NOT_FOUND);
        }

        try {
            attributes.setNamedItemNS(attr);
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.WRONG_DOCUMENT_ERR);
        }
        assertTrue(success, "throw_WRONG_DOCUMENT_ERR");

    }
}

