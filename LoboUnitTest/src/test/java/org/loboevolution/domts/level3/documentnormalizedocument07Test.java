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
import org.loboevolution.html.dom.DOMError;
import org.loboevolution.html.dom.DOMLocator;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.dom.nodeimpl.DOMErrorMonitor;
import org.loboevolution.html.node.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Add a CDATASection containing "]]>" and perform normalization with split-cdata-sections=false.  Should result
 * in an error.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-split-cdata-sections">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-split-cdata-sections</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-severity">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-severity</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-message">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-message</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-type">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-type</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-relatedException">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-relatedException</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-relatedData">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-relatedData</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-location">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ERROR-DOMError-location</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-line-number">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-line-number</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-column-number">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-column-number</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-byteOffset">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-byteOffset</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-utf16Offset">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-utf16Offset</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-node">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-node</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-uri">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#DOMLocator-uri</a>
 * @see <a href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=542">http://www.w3.org/Bugs/Public/show_bug.cgi?id=542</a>
 */
public class documentnormalizedocument07Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element elem;
        DOMConfiguration domConfig;
        HTMLCollection elemList;
        CDATASection newChild;
        Node oldChild;
        Node retval;
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        List<DOMError> errors;

        DOMError error;
        int errorCount = 0;
        int severity;
        Node problemNode;
        DOMLocator location;
        int lineNumber;
        int columnNumber;
        int byteOffset;
        int utf16Offset;
        String uri;
        String type;
        String message;
        Object relatedException;
        Object relatedData;
        int length;
        doc = sampleXmlFile("barfoo.xml");
        elemList = doc.getElementsByTagName("p");
        elem = (Element) elemList.item(0);
        oldChild = elem.getFirstChild();
        newChild = doc.createCDATASection("this is not ]]> good");
        retval = elem.replaceChild(newChild, oldChild);
        domConfig = doc.getDomConfig();
        domConfig.setParameter("split-cdata-sections", Boolean.FALSE);
        /*DOMErrorMonitor */
        domConfig.setParameter("error-handler", errorMonitor);
        doc.normalizeDocument();
        errors = errorMonitor.getErrors();
        for (int indexN100E0 = 0; indexN100E0 < errors.size(); indexN100E0++) {
            error = errors.get(indexN100E0);
            severity = error.getSeverity();

            if (severity == 2) {
                location = error.getLocation();
                problemNode = location.getRelatedNode();
                assertSame(newChild, problemNode, "relatedNode");
                lineNumber = location.getLineNumber();
                columnNumber = location.getColumnNumber();
                byteOffset = location.getByteOffset();
                utf16Offset = location.getUtf16Offset();
                uri = location.getUri();
                message = error.getMessage();
                length = message.length();
                assertTrue((length > 0), "messageNotEmpty");
                type = error.getType();
                relatedData = error.getRelatedData();
                relatedException = error.getRelatedException();
                errorCount += 1;
            } else {
                assertEquals(1, severity, "anyOthersShouldBeWarnings");
            }

        }
        assertEquals(1, errorCount, "oneError");
    }
}

