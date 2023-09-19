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
import org.junit.Test;
import org.loboevolution.gui.LocalHtmlRendererConfig;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.DOMError;
import org.loboevolution.html.dom.DOMLocator;
import org.loboevolution.html.dom.nodeimpl.DOMErrorMonitor;
import org.loboevolution.html.dom.nodeimpl.DOMImplementationImpl;
import org.loboevolution.html.node.*;
import org.loboevolution.http.UserAgentContext;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Create a document with an XML 1.1 valid but XML 1.0 invalid attribute and
 * normalize document with infoset set to true.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-infoset">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-infoset</a>
 */
public class infoset07Test extends LoboUnitTest {


    @Test
    public void runTest() {
        DOMImplementation domImpl;
        DocumentType nullDoctype = null;

        Document doc;
        Element docElem;
        Attr attr;
        Node retval;
        DOMConfiguration domConfig;
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        List errors;

        DOMError error;
        int severity;
        String type;
        DOMLocator locator;
        Node relatedNode;
        domImpl = new DOMImplementationImpl(new UserAgentContext(new LocalHtmlRendererConfig(), true));
        doc = domImpl.createDocument("http://www.w3.org/1999/xhtml", "html", nullDoctype);
        docElem = doc.getDocumentElement();

        boolean success = false;
        try {
            attr = doc.createAttribute("LegalNameࢎ");
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.INVALID_CHARACTER_ERR);
        }
        assertTrue("xml10InvalidName", success);


        try {
            doc.setXmlVersion("1.1");

        } catch (DOMException ex) {
            if (ex.getCode() == 9) {
                return;
            }
            throw ex;
        }
        docElem.setAttribute("LegalName", "foo");
        attr = docElem.getAttributeNode("LegalNamࢎ");
        doc.setXmlVersion("1.0");
        domConfig = doc.getDomConfig();
        domConfig.setParameter("infoset", Boolean.TRUE);
        /*DOMErrorMonitor */
        domConfig.setParameter("error-handler", errorMonitor);
        doc.normalizeDocument();
        errors = errorMonitor.getErrors();
        for (Object o : errors) {
            error = (DOMError) o;
            severity = error.getSeverity();
            assertEquals("severity", 2, severity);
            type = error.getType();
            assertEquals("type", "wf-invalid-character-in-node-name", type);
            locator = error.getLocation();
            relatedNode = locator.getRelatedNode();
            assertSame("relatedNode", attr, relatedNode);
        }
        assertSame("oneError", 1, errors.size());
    }
}

