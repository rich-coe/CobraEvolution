
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

import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.node.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * The "importNode(importedNode,deep)" method for a
 * Document should import the given importedNode into that Document.
 * The importedNode is of type Entity.
 * <p>
 * Retrieve entity "ent4" from staffNS.xml document.
 * Invoke method importNode(importedNode,deep) on this document with deep as false.
 * Method should return a node of type Entity whose descendant is copied.
 * The returned node should belong to this document whose systemId is "staffNS.dtd"
 *
 * @author NIST
 * @author Mary Brady
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#Core-Document-importNode">http://www.w3.org/TR/DOM-Level-2-Core/core#Core-Document-importNode</a>
 */
public class importNode12Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        Document aNewDoc;
        DocumentType doc1Type;
        NamedNodeMap entityList;
        EntityReference entity2;
        EntityReference entity1;
        Document ownerDocument;
        DocumentType docType;
        String system;
        String entityName;
        Node child;
        String childName;
        doc = sampleXmlFile("staffNS.xml");
        aNewDoc = sampleXmlFile("staffNS.xml");
        doc1Type = aNewDoc.getDoctype();
        entityList = doc1Type.getEntities();
        assertNotNull(entityList, "entitiesNotNull");
        entity2 = (EntityReference) entityList.getNamedItem("ent4");
        entity1 = (EntityReference) doc.importNode(entity2, true);
        ownerDocument = entity1.getOwnerDocument();
        docType = ownerDocument.getDoctype();
        system = docType.getSystemId();
        assertEquals("staffNS.dtd", system, "systemId");
        entityName = entity1.getNodeName();
        assertEquals("ent4", entityName, "entityName");
    }
}

