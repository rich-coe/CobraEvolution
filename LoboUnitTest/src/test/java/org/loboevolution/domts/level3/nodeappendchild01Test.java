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
import org.loboevolution.html.node.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * An attempt to add a second doctype node should result in a HIERARCHY_REQUEST_ERR
 * or a NOT_SUPPORTED_ERR.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-184E7107">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-184E7107</a>
 */
public class nodeappendchild01Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        DOMImplementation domImpl;
        DocumentType docType;
        String nullPubId = null;
        String nullSysId = null;
        String tagName;
        Element docElem;
        doc = sampleXmlFile("barfoo.xml");
        docElem = doc.getDocumentElement();
        tagName = docElem.getTagName();
        domImpl = doc.getImplementation();
        docType = domImpl.createDocumentType(tagName, nullPubId, nullSysId);

        boolean success = false;
        try {
            doc.appendChild(docType);
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.HIERARCHY_REQUEST_ERR);
        }
        assertTrue(success, "throw_HIERARCHY_REQUEST_ERR");
    }
}

