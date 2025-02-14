
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
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.DocumentType;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.NamedNodeMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * An attempt to add an element to the named node map returned by notations should
 * result in a NO_MODIFICATION_ERR or HIERARCHY_REQUEST_ERR.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-D46829EF">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-D46829EF</a>
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-setNamedItemNS</a>
 */
public class hc_notationssetnameditemns1Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        NamedNodeMap notations;
        DocumentType docType;
        Element elem;
        doc = sampleXmlFile("hc_staff.xml");
        docType = doc.getDoctype();

        assertNotNull(docType, "docTypeNotNull");
        notations = docType.getNotations();
        assertNotNull(notations, "notationsNotNull");
        elem = doc.createElementNS("http://www.w3.org/1999/xhtml", "br");

        try {
            notations.setNamedItemNS(elem);
            fail("throw_HIER_OR_NO_MOD_ERR");

        } catch (DOMException ex) {
            switch (ex.getCode()) {
                case 3:
                case 7:
                    break;
                default:
                    throw ex;
            }
        }
    }
}

