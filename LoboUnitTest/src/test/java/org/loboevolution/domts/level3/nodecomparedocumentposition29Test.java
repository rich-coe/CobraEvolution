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
import org.loboevolution.html.node.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Create two entity reference nodes. Using compareDocumentPosition to check if the child of the first Entity
 * Ref node precedes the child of the second Entity Ref node, and that the child of the second Entity Ref node
 * follows the child of the first Entity Ref node.
 *
 * @author IBM
 * @author Jenny Hsu
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-compareDocumentPosition">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-compareDocumentPosition</a>
 */
public class nodecomparedocumentposition29Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element docElem;
        EntityReference entRef1;
        EntityReference entRef2;
        Element entRefChild1;
        ProcessingInstruction entRefChild2;
        int entRefChild1Position;
        int entRefChild2Position;
        Node appendedChild;
        doc = sampleXmlFile("hc_staff.xml");
        entRef1 = doc.createEntityReference("ent4");
        entRef2 = doc.createEntityReference("ent4");
        docElem = doc.getDocumentElement();
        appendedChild = docElem.appendChild(entRef1);
        appendedChild = docElem.appendChild(entRef2);
        entRefChild1 = (Element) entRef1.getFirstChild();
        assertNotNull(entRefChild1, "entRefChild1NotNull");
        entRefChild2 = (ProcessingInstruction) entRef2.getLastChild();
        assertNotNull(entRefChild2, "entRefChild2NotNull");
        entRefChild1Position = entRefChild1.compareDocumentPosition(entRefChild2);
        assertEquals(4, entRefChild1Position, "nodecomparedocumentpositionFollowing29");
        entRefChild2Position = entRefChild2.compareDocumentPosition(entRefChild1);
        assertEquals(2, entRefChild2Position, "nodecomparedocumentpositionPRECEDING29");
    }
}

