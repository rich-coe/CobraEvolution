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
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Using isDefaultNamespace on a new Child of a new Element node with a namespace URI
 * and prefix and using the childs namespace URI as an argument, verify if the
 * value returned is true.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isDefaultNamespace">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isDefaultNamespace</a>
 */
public class nodeisdefaultnamespace10Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element parent;
        Element child;
        boolean isDefault;
        Node appendedChild;
        doc = sampleXmlFile("hc_staff.xml");
        parent = doc.createElementNS("http://www.w3.org/1999/xhtml", "xhtml:body");
        child = doc.createElementNS("http://www.w3.org/1999/xhtml", "p");
        appendedChild = parent.appendChild(child);
        isDefault = child.isDefaultNamespace("http://www.w3.org/1999/xhtml");
        assertTrue(isDefault, "nodeisdefaultnamespace10_1");
        isDefault = parent.isDefaultNamespace("http://www.w3.org/1999/xhtml");
        assertFalse(isDefault, "nodeisdefaultnamespace10_2");
    }
}

