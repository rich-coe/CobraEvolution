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
import org.loboevolution.html.dom.nodeimpl.DOMErrorMonitor;
import org.loboevolution.html.node.DOMConfiguration;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Node;
import org.loboevolution.html.node.ProcessingInstruction;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Normalize document based on section 3.1 with canonical-form set to true
 * and comments to false and check normalized document.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-canonical-form">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-canonical-form</a>
 */
public class canonicalform09Test extends LoboUnitTest {


    @Test
    public void runTest() {
        Document doc;
        DOMConfiguration domConfig;
        boolean canSet;
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        Node node;
        String nodeValue;
        int nodeType;
        int length;
        doc = sampleXmlFile("canonicalform01.xml");
        domConfig = doc.getDomConfig();
        canSet = domConfig.canSetParameter("canonical-form", Boolean.TRUE);

        if (canSet) {
            /*DOMErrorMonitor */
            domConfig.setParameter("error-handler", errorMonitor);
            domConfig.setParameter("canonical-form", Boolean.TRUE);
            domConfig.setParameter("comments", Boolean.FALSE);
            doc.normalizeDocument();
            assertTrue(errorMonitor.assertLowerSeverity(2), "normalizeError");
            node = doc.getFirstChild();
            nodeType = node.getNodeType();
            assertEquals(7, nodeType, "PIisFirstChild");
            nodeValue = ((ProcessingInstruction) /*Node */node).getData();
            length = nodeValue.length();
            assertEquals(36, length, "piDataLength");
            node = node.getNextSibling();
            nodeType = node.getNodeType();
            assertEquals(3, nodeType, "TextisSecondChild");
            nodeValue = node.getNodeValue();
            length = nodeValue.length();
            assertEquals(1, length, "secondChildLength");
            node = node.getNextSibling();
            nodeType = node.getNodeType();
            assertEquals(1, nodeType, "ElementisThirdChild");
            node = node.getNextSibling();
            nodeType = node.getNodeType();
            assertEquals(3, nodeType, "TextisFourthChild");
            nodeValue = node.getNodeValue();
            length = nodeValue.length();
            assertEquals(1, length, "fourthChildLength");
            node = node.getNextSibling();
            nodeType = node.getNodeType();
            assertEquals(7, nodeType, "PIisFifthChild");
            nodeValue = ((ProcessingInstruction) /*Node */node).getData();
            assertEquals("", nodeValue, "trailingPIData");
            node = node.getNextSibling();
            assertNull(node, "SixthIsNull");
        }
    }
}

