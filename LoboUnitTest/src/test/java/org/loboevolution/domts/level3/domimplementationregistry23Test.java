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


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.loboevolution.gui.LocalHtmlRendererConfig;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.nodeimpl.DOMImplementationImpl;
import org.loboevolution.html.dom.nodeimpl.bootstrap.DOMImplementationRegistry;
import org.loboevolution.html.node.DOMImplementation;
import org.loboevolution.html.node.DOMImplementationList;
import org.loboevolution.http.UserAgentContext;

import static org.junit.jupiter.api.Assertions.*;


/**
 * DOMImplementationRegistry.getDOMImplementationList("cOrE 3.0 xMl 3.0 eVeNts 2.0 lS")
 * should return an empty list or a list of DOMImplementation that implements the specified features.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/java-binding">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/java-binding</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/ecma-script-binding">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/ecma-script-binding</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-getDOMImpls">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-getDOMImpls</a>
 */
public class domimplementationregistry23Test extends LoboUnitTest {
    @Test
    @SneakyThrows
    public void runTest() {
       
        DOMImplementation domImpl;
        boolean hasCore;
        boolean hasXML;
        boolean hasEvents;
        boolean hasLS;
        DOMImplementation baseImpl;
        String nullVersion = null;

        DOMImplementationList domImplList;
        int length;
         DOMImplementationRegistry domImplRegistry = DOMImplementationRegistry.newInstance();
        assertNotNull(domImplRegistry, "domImplRegistryNotNull");
        domImplList = domImplRegistry.getDOMImplementationList("cOrE 3.0 xMl 3.0 eVeNts 2.0 lS");
        length = (int) domImplList.getLength();

        if (length == 0) {
            baseImpl = new DOMImplementationImpl(new UserAgentContext(new LocalHtmlRendererConfig(), true));
            hasCore = baseImpl.hasFeature("Core", "3.0");
            hasXML = baseImpl.hasFeature("XML", "3.0");
            hasEvents = baseImpl.hasFeature("Events", "2.0");
            hasLS = baseImpl.hasFeature("LS", nullVersion);
            assertFalse( (hasCore & hasXML & hasEvents & hasLS), "baseImplFeatures");
        } else {
            for (int indexN10096 = 0; indexN10096 < domImplList.getLength(); indexN10096++) {
                domImpl = (DOMImplementation) domImplList.item(indexN10096);
                hasCore = domImpl.hasFeature("Core", "3.0");
                assertTrue(hasCore, "hasCore");
                hasXML = domImpl.hasFeature("XML", "3.0");
                assertTrue(hasXML, "hasXML");
                hasEvents = domImpl.hasFeature("Events", "2.0");
                assertTrue(hasEvents, "hasEvents");
                hasLS = domImpl.hasFeature("LS", nullVersion);
                assertTrue(hasLS, "hasLS");
            }
        }

    }
}

