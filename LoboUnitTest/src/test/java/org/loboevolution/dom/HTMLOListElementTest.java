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

package org.loboevolution.dom;

import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;

/**
 * Tests for {@link org.loboevolution.html.dom.HTMLOListElement}.
 */
public class HTMLOListElementTest extends LoboUnitTest {


    /**
     * <p>compact.</p>
     */
    @Test
    public void compact() {
        final String html =
                "<html>\n"
                        + "  <head>\n"
                        + "    <script>\n"
                        + "      function test() {\n"
                        + "        alert(document.getElementById('o1').compact);\n"
                        + "        alert(document.getElementById('o2').compact);\n"
                        + "        alert(document.getElementById('o3').compact);\n"
                        + "        alert(document.getElementById('o4').compact);\n"
                        + "        alert(document.getElementById('o1').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o2').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o3').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o4').getAttribute('compact'));\n"

                        + "        document.getElementById('o1').compact = true;\n"
                        + "        document.getElementById('o2').compact = false;\n"
                        + "        document.getElementById('o3').compact = 'xyz';\n"
                        + "        document.getElementById('o4').compact = null;\n"
                        + "        alert(document.getElementById('o1').compact);\n"
                        + "        alert(document.getElementById('o2').compact);\n"
                        + "        alert(document.getElementById('o3').compact);\n"
                        + "        alert(document.getElementById('o4').compact);\n"
                        + "        alert(document.getElementById('o1').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o2').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o3').getAttribute('compact'));\n"
                        + "        alert(document.getElementById('o4').getAttribute('compact'));\n"
                        + "      }\n"
                        + "    </script>\n"
                        + "  </head>\n"
                        + "  <body onload='test()'>\n"
                        + "    <ol id='o1'><li>a</li><li>b</li></ol>\n"
                        + "    <ol compact='' id='o2'><li>a</li><li>b</li></ol>\n"
                        + "    <ol compact='blah' id='o3'><li>a</li><li>b</li></ol>\n"
                        + "    <ol compact='2' id='o4'><li>a</li><li>b</li></ol>\n"
                        + "  </body>\n"
                        + "</html>";
        final String[] messages = {"false", "true", "true", "true", null, "", "blah", "2",
                "true", "false", "true", "false", "", null, "", null};
        checkHtmlAlert(html, messages);
    }

    /**
     * <p>type.</p>
     */
    @Test
    public void type() {
        final String html =
                "<html>\n"
                        + "  <head>\n"
                        + "    <script>\n"
                        + "      function test() {\n"
                        + "        alert(document.getElementById('o1').type);\n"
                        + "        alert(document.getElementById('o2').type);\n"
                        + "        alert(document.getElementById('o3').type);\n"
                        + "        alert(document.getElementById('o4').type);\n"
                        + "        alert(document.getElementById('o1').getAttribute('type'));\n"
                        + "        alert(document.getElementById('o2').getAttribute('type'));\n"
                        + "        alert(document.getElementById('o3').getAttribute('type'));\n"
                        + "        alert(document.getElementById('o4').getAttribute('type'));\n"

                        + "        document.getElementById('o1').type = '1';\n"
                        + "        alert(document.getElementById('o1').type);\n"

                        + "        document.getElementById('o1').type = 'a';\n"
                        + "        alert(document.getElementById('o1').type);\n"

                        + "        document.getElementById('o1').type = 'A';\n"
                        + "        alert(document.getElementById('o1').type);\n"

                        + "        document.getElementById('o1').type = 'i';\n"
                        + "        alert(document.getElementById('o1').type);\n"

                        + "        document.getElementById('o1').type = 'I';\n"
                        + "        alert(document.getElementById('o1').type);\n"

                        + "        try { document.getElementById('o1').type = 'u' } catch(e) {alert('exception');}\n"
                        + "        alert(document.getElementById('o1').type);\n"
                        + "      }\n"
                        + "    </script>\n"
                        + "  </head>\n"
                        + "  <body onload='test()'>\n"
                        + "    <ol id='o1'><li>a</li><li>b</li></ol>\n"
                        + "    <ol type='' id='o2'><li>a</li><li>b</li></ol>\n"
                        + "    <ol type='blah' id='o3'><li>a</li><li>b</li></ol>\n"
                        + "    <ol type='A' id='o4'><li>a</li><li>b</li></ol>\n"
                        + "  </body>\n"
                        + "</html>";
        final String[] messages = {"", "", "blah", "A", null, "", "blah", "A", "1", "a", "A", "i", "I", "u"};
        checkHtmlAlert(html, messages);
    }
}
