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


/**
 * Using replaceChild on an Element node attempt to replace a new Element child node with
 * new child nodes and vice versa and in each case verify the name of the replaced node.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-785887307">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-785887307</a>
 */
public class nodereplacechild30Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element parent;
        Element oldChild;
        Element newElement;
        Text newText;
        Comment newComment;
        ProcessingInstruction newPI;
        CDATASection newCdata;
        EntityReference newERef;
        Node replaced;
        String nodeName;
        Node appendedChild;
        doc = sampleXmlFile("hc_staff.xml");
        parent = doc.createElementNS("http://www.w3.org/1999/xhtml", "xhtml:html");
        oldChild = doc.createElementNS("http://www.w3.org/1999/xhtml", "xhtml:head");
        newElement = doc.createElementNS("http://www.w3.org/1999/xhtml", "xhtml:body");
        appendedChild = parent.appendChild(oldChild);
        appendedChild = parent.appendChild(newElement);
        newText = doc.createTextNode("Text");
        appendedChild = parent.appendChild(newText);
        newComment = doc.createComment("Comment");
        appendedChild = parent.appendChild(newComment);
        newPI = doc.createProcessingInstruction("target", "data");
        appendedChild = parent.appendChild(newPI);
        newCdata = doc.createCDATASection("Cdata");
        appendedChild = parent.appendChild(newCdata);
        newERef = doc.createEntityReference("delta");
        appendedChild = parent.appendChild(newERef);
        replaced = parent.replaceChild(newElement, oldChild);
        nodeName = replaced.getNodeName();
        assertEquals("xhtml:head", nodeName, "nodereplacechild30_1");
        replaced = parent.replaceChild(oldChild, newElement);
        nodeName = replaced.getNodeName();
        assertEquals("xhtml:body", nodeName, "nodereplacechild30_2");
        replaced = parent.replaceChild(newText, oldChild);
        nodeName = replaced.getNodeName();
        assertEquals("xhtml:head", nodeName, "nodereplacechild30_3");
        replaced = parent.replaceChild(oldChild, newText);
        nodeName = replaced.getNodeName();
        assertEquals("#text", nodeName, "nodereplacechild30_4");
        replaced = parent.replaceChild(newComment, oldChild);
        nodeName = replaced.getNodeName();
        assertEquals("xhtml:head", nodeName, "nodereplacechild30_5");
        replaced = parent.replaceChild(oldChild, newComment);
        nodeName = replaced.getNodeName();
        assertEquals("#comment", nodeName, "nodereplacechild30_6");
        replaced = parent.replaceChild(oldChild, newPI);
        nodeName = replaced.getNodeName();
        assertEquals("target", nodeName, "nodereplacechild30_7");
        replaced = parent.replaceChild(oldChild, newCdata);
        nodeName = replaced.getNodeName();
        assertEquals("#cdata-section", nodeName, "nodereplacechild30_8");
        replaced = parent.replaceChild(oldChild, newERef);
        nodeName = replaced.getNodeName();
        assertEquals("delta", nodeName, "nodereplacechild30_9");
    }
}

