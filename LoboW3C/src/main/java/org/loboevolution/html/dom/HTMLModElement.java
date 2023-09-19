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

package org.loboevolution.html.dom;


/**
 * Provides special properties (beyond the regular methods and properties available through the HTMLElement interface they also have available to them by inheritance) for manipulating modification elements, that is &lt;del&gt; and &lt;ins&gt;.
 *
 *
 *
 */
public interface HTMLModElement extends HTMLElement {
 

    /**
     * Sets or retrieves reference information about the object.
     *
     * @return a {@link java.lang.String} object.
     */
    String getCite();

    
    /**
     * <p>setCite.</p>
     *
     * @param cite a {@link java.lang.String} object.
     */
    void setCite(String cite);

    /**
     * Sets or retrieves the date and time of a modification to the object.
     *
     * @return a {@link java.lang.String} object.
     */
    String getDateTime();

    
    /**
     * <p>setDateTime.</p>
     *
     * @param dateTime a {@link java.lang.String} object.
     */
    void setDateTime(String dateTime);

}
