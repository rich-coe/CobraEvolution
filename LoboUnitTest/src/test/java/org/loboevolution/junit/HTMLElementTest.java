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

package org.loboevolution.junit;

import org.htmlunit.cssparser.dom.DOMException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.gui.LocalHtmlRendererConfig;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.dom.HTMLDocument;
import org.loboevolution.html.dom.domimpl.HTMLCollectionImpl;
import org.loboevolution.html.dom.domimpl.HTMLElementImpl;
import org.loboevolution.html.dom.nodeimpl.DOMImplementationImpl;
import org.loboevolution.html.dom.nodeimpl.ElementImpl;
import org.loboevolution.html.node.*;
import org.loboevolution.html.node.css.CSSStyleDeclaration;
import org.loboevolution.http.UserAgentContext;

import static org.junit.jupiter.api.Assertions.*;

public class HTMLElementTest extends LoboUnitTest {
	private Document document;

	private static DOMImplementationImpl domImpl;

	@BeforeAll
	public static void setUpBeforeClass() {
		UserAgentContext context = new UserAgentContext(new LocalHtmlRendererConfig(), true);
		context.setUserAgentEnabled(false);
		domImpl = new DOMImplementationImpl(context);
	}
	@Test
	public void testCreateElementError() {
		try {
			document = sampleHtmlFile();
			document.createElement("p'");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.INVALID_CHARACTER_ERR, e.getCode());
		}
	}

	@Test
	public void testCreateElementNSError() {
		try {
			document = sampleHtmlFile();
			document.createElementNS("http://www.example.com/examplens", "e:p'");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.INVALID_CHARACTER_ERR, e.getCode());
		}
	}

	@Test
	public void testCreateElementNSError2() {
		try {
			document = sampleHtmlFile();
			document.createElementNS(null, "foo:bar");
			fail("Must throw an exception");
		} catch (DOMException e) {
		}
	}

	@Test
	public void testCreateAttributeStyle() {
		document = sampleHtmlFile();
		Attr attr = document.createAttribute("style");
		assertNotNull(attr);
		attr.setValue("display:block");
		assertEquals(0, attr.getChildNodes().getLength());
		assertNull(attr.getFirstChild());
		assertNull(attr.getLastChild());
		assertNull(attr.getParentNode());
		assertEquals(attr.getName(), "style");
		assertEquals(attr.getNodeName(), "style");
		assertEquals(attr.getValue(), "display:block");
		assertEquals(attr.getNodeValue(), "display:block");
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		html.setAttributeNode(attr);
		CSSStyleDeclaration style = html.getStyle();
		assertNotNull(style);
		assertEquals(style.getCssText(), "display: block");
		assertEquals(html.getAttribute("style"), "display:block");
		style.setCssText("margin-top: 10%");
		assertEquals(html.getAttribute("style"), "margin-top: 10%");
	}

	@Test
	public void testCreateAttributeNS() {
		document = sampleHtmlFile();
		Attr attr = document.createAttributeNS(Document.XMLNS_NAMESPACE_URI, "xmlns");
		assertNotNull(attr);
		attr.setValue(HTMLDocument.HTML_NAMESPACE_URI);
		assertEquals(0, attr.getChildNodes().getLength());
		assertNull(attr.getFirstChild());
		assertNull(attr.getLastChild());
		assertNull(attr.getParentNode());
		assertEquals(attr.getName(), "xmlns");
		assertEquals(attr.getNamespaceURI(), "http://www.w3.org/2000/xmlns/");
		assertEquals(HTMLDocument.HTML_NAMESPACE_URI, attr.getValue());
		assertEquals(attr.getNodeName(), "xmlns");
		assertEquals(HTMLDocument.HTML_NAMESPACE_URI, attr.getNodeValue());
	}

	@Test
	public void testCreateAttributeNS2() {
		document = sampleHtmlFile();
		Attr attr = document.createAttributeNS("http://www.w3.org/2000/svg", "version");
		assertNotNull(attr);
		attr.setValue("1.1");
		assertEquals(0, attr.getChildNodes().getLength());
		assertNull(attr.getFirstChild());
		assertNull(attr.getLastChild());
		assertNull(attr.getParentNode());
		assertEquals(attr.getNamespaceURI(), "http://www.w3.org/2000/svg");
		assertEquals(attr.getName(), "version");
		assertEquals(attr.getNodeName(), "version");
		assertEquals(attr.getValue(), "1.1");
		assertEquals(attr.getNodeValue(), "1.1");
		assertEquals(attr.toString(), "[object Attr]");
	}

	@Test
	public void testCreateAttributeNSError() {
		document = sampleHtmlFile();
		try {
			document.createAttributeNS("http://www.example.com/examplens", "xmlns");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.NAMESPACE_ERR, e.getCode());
		}
		Attr attr = document.createAttributeNS("http://www.example.com/examplens", "doc");
		try {
			attr.setPrefix("xmlns");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.NAMESPACE_ERR, e.getCode());
		}
		try {
			attr.setPrefix("xml");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.NAMESPACE_ERR, e.getCode());
		}
		try {
			attr.setPrefix("foo:");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.INVALID_CHARACTER_ERR, e.getCode());
		}
		try {
			document.createAttributeNS(null, "foo:bar");
			fail("Must throw exception");
		} catch (DOMException e) {
			assertEquals(DOMException.NAMESPACE_ERR, e.getCode());
		}
		attr = document.createAttributeNS(Document.XML_NAMESPACE_URI, "doc");
		attr.setPrefix("xml");
	}

	@Test
	public void setAttribute() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		Element body = document.createElement("body");
		html.appendChild(body);
		assertFalse(body.hasAttributes());
		body.setAttribute("foo", "bar");
		assertTrue(body.hasAttributes());
		assertEquals(body.getAttribute("foo"), "bar");
		Attr attr = body.getAttributeNode("foo");
		assertFalse(attr.isId());
		assertNull(attr.getSchemaTypeInfo().getTypeName());
		assertEquals(attr.getSchemaTypeInfo().getTypeNamespace(), "https://www.w3.org/TR/xml/");
		body.setAttribute("id", "bodyId");
		assertTrue(body.hasAttributes());
		assertEquals(2, body.getAttributes().getLength());
		assertEquals(body.getAttribute("id"), "bodyId");
		attr = body.getAttributeNode("id");
		assertTrue(attr.isId());
		assertEquals(attr.getSchemaTypeInfo().getTypeName(), "ID");
		assertEquals(attr.getSchemaTypeInfo().getTypeNamespace(), "https://www.w3.org/TR/xml/");
		body.setAttribute("id", "newId");
		assertEquals(body.getAttribute("id"), "newId");
		assertEquals(2, body.getAttributes().getLength());
	}

	@Test
	public void setAttributeCI() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		Element body = document.createElement("BODY");
		html.appendChild(body);
		assertFalse(body.hasAttributes());
		body.setAttribute("FOO", "bar");
		assertTrue(body.hasAttributes());
		assertTrue(body.hasAttribute("foo"));
		assertEquals(body.getAttribute("foo"), "bar");
		Attr attr = body.getAttributeNode("FOO");
		assertFalse(attr.isId());
		assertNull(attr.getSchemaTypeInfo().getTypeName());
		assertEquals(attr.getSchemaTypeInfo().getTypeNamespace(), "https://www.w3.org/TR/xml/");
		body.setAttribute("ID", "bodyId");
		assertTrue(body.hasAttributes());
		assertEquals(2, body.getAttributes().getLength());
		assertEquals(body.getAttribute("ID"), "bodyId");
		attr = body.getAttributeNode("ID");
		assertTrue(attr.isId());
		assertEquals(attr.getSchemaTypeInfo().getTypeName(), "ID");
		assertEquals(attr.getSchemaTypeInfo().getTypeNamespace(), "https://www.w3.org/TR/xml/");
		body.setAttribute("id", "newId");
		assertEquals(body.getAttribute("id"), "newId");
		assertEquals(2, body.getAttributes().getLength());
	}

	@Test
	public void testSetAttributeError() {
		document = sampleHtmlFile();
		Element p = document.createElement("p");
		try {
			p.setAttribute("foo=", "bar");
			fail("Must throw an exception");
		} catch (DOMException e) {
			assertEquals(DOMException.INVALID_CHARACTER_ERR, e.getCode());
		}
	}

	@Test
	public void setAttributeNode() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		Element body = document.createElement("body");
		html.appendChild(body);
		Attr attr = document.createAttribute("id");
		attr.setValue("bodyId");
		body.setAttributeNode(attr);
		assertTrue(body.hasAttributes());
		assertTrue(attr.isId());
		assertNull(attr.getParentNode());
		assertEquals(body.getAttribute("id"), "bodyId");
		assertEquals(1, body.getAttributes().getLength());
		// Set the attribute to itself
		assertNotNull(body.setAttributeNode(attr));
		assertEquals(1, body.getAttributes().getLength());
		// Remove
		Attr rmattr = body.removeAttributeNode(attr);
		assertSame(rmattr, attr);
		assertFalse(body.hasAttributes());
		assertEquals(0, body.getAttributes().getLength());
		assertEquals(attr.getValue(), "bodyId");
		// Class attribute
		body.setAttribute("class", "fooclass");
		assertTrue(body.hasAttributes());
		assertEquals(body.getAttribute("class"), "fooclass");
		assertFalse(body.getAttributeNode("class").isId());
		// Replace class attribute, first with another namespace
		attr = document.createAttributeNS("http://www.example.com/examplens", "e:class");
		attr.setValue("barclass");
		assertEquals(attr.getLocalName(), "e:class");
		body.setAttributeNodeNS(attr);
		assertEquals(body.getAttribute("class"), "fooclass");
		attr = document.createAttribute("class");
		attr.setValue("barclass");
		body.setAttributeNode(attr);
		assertEquals(body.getAttribute("class"), "barclass");
	}

	@Test
	public void setAttributeNodeCI() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		Element body = document.createElement("body");
		html.appendChild(body);
		Attr attr = document.createAttribute("ID");
		attr.setValue("bodyId");
		body.setAttributeNode(attr);
		assertTrue(body.hasAttributes());
		assertTrue(attr.isId());
		assertNull(attr.getParentNode());
		assertEquals(attr.getLocalName(), "ID");
		assertEquals(body.getAttribute("ID"), "bodyId");
		assertEquals(1, body.getAttributes().getLength());
		// Set the attribute to itself
		assertNotNull(body.setAttributeNode(attr));
		assertEquals(1, body.getAttributes().getLength());
		// Remove
		Attr rmattr = body.removeAttributeNode(attr);
		assertSame(rmattr, attr);
		assertFalse(body.hasAttributes());
		assertEquals(0, body.getAttributes().getLength());
		assertEquals(attr.getValue(), "bodyId");
		// Class attribute
		body.setAttribute("CLASS", "fooclass");
		assertTrue(body.hasAttributes());
		assertEquals(body.getAttribute("CLASS"), "fooclass");
		assertFalse(body.getAttributeNode("class").isId());
		// Set CLASS attribute with another namespace
		attr = document.createAttributeNS("http://www.example.com/examplens", "e:CLASS");
		attr.setValue("barclass");
		assertEquals(attr.getLocalName(), "e:CLASS");
		body.setAttributeNodeNS(attr);
		assertEquals(body.getAttribute("CLASS"), "fooclass");
		attr = document.createAttribute("CLASS");
		attr.setValue("barclass");
		assertEquals(attr.getName(), "CLASS");
		body.setAttributeNode(attr);
		assertEquals(body.getAttribute("CLASS"), "barclass");
	}

	@Test
	public void getAttributes() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		Element body = document.createElement("body");
		html.appendChild(body);
		body.setAttribute("ID", "bodyId");
		NamedNodeMap nnm = body.getAttributes();
		assertNotNull(nnm);
		assertEquals(1, nnm.getLength());
		Attr attr = (Attr) nnm.item(0);
		assertTrue(attr.isId());
		assertNull(attr.getParentNode());
		assertEquals(attr.getLocalName(), "ID");
		assertSame(attr, nnm.getNamedItem("ID"));
		assertSame(attr, nnm.getNamedItemNS(null, "id"));
		assertNull(nnm.getNamedItemNS(HTMLDocument.HTML_NAMESPACE_URI, "id"));
		// Set the attribute to itself
		assertNotNull(nnm.setNamedItem(attr));
		assertEquals(1, nnm.getLength());
		// Remove
		Attr rmattr = (Attr) nnm.removeNamedItem(attr.getName());
		assertSame(rmattr, attr);
		assertFalse(body.hasAttributes());
		assertEquals(0, nnm.getLength());
		assertEquals(attr.getValue(), "bodyId");
		// Class attribute
		Attr classAttr = document.createAttribute("CLASS");
		classAttr.setValue("fooclass");
		nnm.setNamedItem(classAttr);
		assertTrue(body.hasAttributes());
		assertEquals(body.getAttribute("CLASS"), "fooclass");
		assertFalse(body.getAttributeNode("class").isId());
		assertSame(classAttr, nnm.getNamedItem("class"));
		// Set CLASS attribute with another namespace
		attr = document.createAttributeNS("http://www.example.com/examplens", "e:CLASS");
		attr.setValue("barclass");
		nnm.setNamedItem(attr);
		assertSame(attr, nnm.getNamedItem("e:CLASS"));
		assertSame(attr, nnm.getNamedItemNS("http://www.example.com/examplens", "CLASS"));
		//
		assertEquals(body.getAttribute("CLASS"), "fooclass");
		attr = document.createAttribute("CLASS");
		attr.setValue("barclass");
		assertEquals(attr.getName(), "CLASS");
		nnm.setNamedItem(attr);
		assertEquals(body.getAttribute("CLASS"), "barclass");
		Attr attr2 = (Attr) nnm.getNamedItem("CLASS");
		assertSame(attr, attr2);
		assertEquals(2, nnm.getLength());
		//
		rmattr = (Attr) nnm.removeNamedItem(attr.getName());
		assertSame(rmattr, attr);
		assertEquals(1, nnm.getLength());
	}

	@Test
	public void setAttributeNodeClass() {
		document = sampleHtmlFile();
		HTMLCollectionImpl fooelms = (HTMLCollectionImpl) document.getElementsByClassName("foo");
		assertEquals(0, fooelms.getLength());
		Element body = document.createElement("body");
		document.getDocumentElement().appendChild(body);
		Attr attr = document.createAttribute("class");
		attr.setValue("foo bar");
		assertEquals(attr.getName(), "class");
		assertEquals(attr.getValue(), "foo bar");
		body.setAttributeNode(attr);
		assertTrue(body.hasAttribute("class"));
		assertNull(attr.getParentNode());
		assertEquals(attr.getValue(), "foo bar");

		fooelms = (HTMLCollectionImpl) document.getElementsByClassName("foo");

		assertEquals(1, fooelms.getLength());
		assertEquals(fooelms.toString(), document.getElementsByClassName("foo").toString());
		assertSame(body, fooelms.item(0));
		HTMLCollectionImpl barelms = (HTMLCollectionImpl) document.getElementsByClassName("bar");
		assertEquals(1, barelms.getLength());
		HTMLCollectionImpl foobarelms = (HTMLCollectionImpl) document.getElementsByClassName("bar foo");
		assertEquals(1, foobarelms.getLength());
		assertSame(body, foobarelms.item(0));
		body.getClassList().remove("bar");
		assertEquals(0, barelms.getLength());
		assertEquals(0, foobarelms.getLength());
		body.getClassList().toggle("bar");
		assertEquals(1, barelms.getLength());
		body.removeAttribute("class");
		assertEquals(attr.getValue(), "foo bar");
		assertEquals(0, fooelms.getLength());
		assertEquals(0, barelms.getLength());
	}

	@Test
	public void testSetAttributeNodeClassCI() {
		document = sampleHtmlFile();
		HTMLCollectionImpl fooelms = (HTMLCollectionImpl) document.getElementsByClassName("foo");
		assertEquals(0, fooelms.getLength());
		Element body = document.createElement("body");
		document.getDocumentElement().appendChild(body);
		Attr attr = document.createAttribute("CLASS");
		attr.setValue("foo bar");
		assertEquals(attr.getName(), "CLASS");
		assertEquals(attr.getValue(), "foo bar");
		body.setAttributeNode(attr);
		assertTrue(body.hasAttribute("CLASS"));
		assertNull(attr.getParentNode());
		assertEquals(attr.getValue(), "foo bar");
		fooelms = (HTMLCollectionImpl) document.getElementsByClassName("foo");

		assertEquals(1, fooelms.getLength());
		assertEquals(fooelms.toString(), document.getElementsByClassName("foo").toString());
		assertSame(body, fooelms.item(0));
		HTMLCollectionImpl barelms = (HTMLCollectionImpl)document.getElementsByClassName("bar");
		assertEquals(1, barelms.getLength());
		HTMLCollectionImpl foobarelms = (HTMLCollectionImpl) document.getElementsByClassName("bar foo");
		assertEquals(1, foobarelms.getLength());
		assertSame(body, foobarelms.item(0));
		body.getClassList().remove("bar");

		foobarelms = (HTMLCollectionImpl) document.getElementsByClassName("bar foo");
		barelms = (HTMLCollectionImpl)document.getElementsByClassName("bar");

		assertEquals(0, barelms.getLength());
		assertEquals(0, foobarelms.getLength());
		body.getClassList().toggle("bar");

		barelms = (HTMLCollectionImpl)document.getElementsByClassName("bar");

		assertEquals(1, barelms.getLength());
		body.removeAttribute("CLASS");
		assertEquals(attr.getValue(), "foo bar");

		foobarelms = (HTMLCollectionImpl) document.getElementsByClassName("foo");
		barelms = (HTMLCollectionImpl)document.getElementsByClassName("bar");

		assertEquals(0, fooelms.getLength());
		assertEquals(0, barelms.getLength());
	}

	@Test
	public void getClassList() {
		document = sampleHtmlFile();
		Element body = document.createElement("body");
		DOMTokenList list = body.getClassList();
		assertNotNull(list);
		assertEquals(0, list.getLength());
		Attr attr = document.createAttribute("class");
		attr.setValue("foo");
		body.setAttributeNode(attr);
		list = body.getClassList();
		assertEquals(1, list.getLength());
		assertEquals(list.item(0), "foo");
		assertEquals(list.getValue(), "foo");
		attr.setValue("foo bar");
		list = body.getClassList();
		assertEquals(2, list.getLength());
		assertEquals(list.item(0), "foo");
		assertEquals(list.getValue(), "foo bar");
		list.add("000");
		list = body.getClassList();
		assertEquals(3, list.getLength());
		assertEquals(list.item(0), "foo");
		assertEquals(list.item(2), "000");
		assertEquals(list.getValue(), "foo bar 000");
		assertEquals(attr.getValue(), "foo bar 000");
		body.removeAttribute("class");
		list = body.getClassList();
		assertEquals(0, list.getLength());
		assertEquals(attr.getValue(), "foo bar 000");
	}

	@Test
	public void testGetClassList2() {
		document = sampleHtmlFile();
		Element body = document.createElement("body");
		DOMTokenList list = body.getClassList();
		assertNotNull(list);
		assertEquals(0, list.getLength());
		list.add("foo");
		assertTrue(body.hasAttribute("class"));
		assertEquals(body.getAttribute("class"), "foo");
	}

	@Test
	public void matchesStringString() {
		document = sampleHtmlFile();
		Element body = document.createElement("body");
		document.getDocumentElement().appendChild(body);
		body.setAttribute("id", "bodyId");
		Element div1 = document.createElement("div");
		Element div2 = document.createElement("div");
		body.appendChild(div1);
		body.appendChild(div2);
		assertFalse(body.matches(".foo"));
		DOMTokenList list = body.getClassList();
		assertNotNull(list);
		assertEquals(0, list.getLength());
		Attr attr = document.createAttribute("class");
		attr.setValue("foo");
		body.setAttributeNode(attr);
		assertTrue(body.matches(".foo"));
		assertTrue(body.matches("#bodyId"));
		attr.setValue("foo bar");
		assertTrue(body.matches(".bar"));
		assertTrue(div1.matches(".bar div"));
		assertTrue(div1.matches("body > div"));
		assertTrue(div1.matches("div:first-child"));
		assertFalse(div2.matches("div:first-child"));
		assertTrue(div2.matches("div:last-child"));
		body.removeAttribute("class");
		assertFalse(body.matches(".bar"));
	}

	@Test
	public void matchesStringString2() {
		document = sampleHtmlFile();
		Element body = document.createElement("body");
		document.getDocumentElement().appendChild(body);
		body.setAttribute("id", "bodyId");
		Element div1 = document.createElement("div");
		Element div2 = document.createElement("div");
		body.appendChild(div1);
		body.appendChild(div2);
		assertTrue(div2.matches("div:last-child"));
		assertFalse(div1.matches("div:last-child"));
		assertFalse(body.matches("div:last-child"));
		NodeList elements = document.querySelectorAll("div:last-child");
		assertNotNull(elements);
		assertEquals(1, elements.getLength());
		assertSame(div2, elements.item(0));
		elements = document.querySelectorAll("div:first-child");
		assertNotNull(elements);
		assertEquals(1, elements.getLength());
		assertSame(div1, elements.item(0));
		elements = document.querySelectorAll("#nosuchID");
		assertNotNull(elements);
		assertEquals(0, elements.getLength());
	}

	@Test
	public void createElement() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		HTMLElementImpl body = (HTMLElementImpl)document.createElement("body");
		html.appendChild(body);
		Element svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
		body.appendChild(svg);
		assertEquals(svg.getNamespaceURI(), "http://www.w3.org/2000/svg");
		Element p = document.createElementNS(null, "p");
		body.appendChild(p);
		assertEquals(HTMLDocument.HTML_NAMESPACE_URI, p.getNamespaceURI());
	}

	@Test
	public void testGetStyle() {
		document = sampleHtmlFile();
		HTMLElementImpl body = (HTMLElementImpl)document.createElement("body");
		assertFalse(body.hasAttributes());
		document.getDocumentElement().appendChild(body);
		body.setAttribute("style", "font-family: Arial");
		assertTrue(body.hasAttributes());
		assertTrue(body.hasAttribute("style"));
		assertEquals(body.getAttribute("style"), "font-family: Arial");
		CSSStyleDeclaration style = body.getStyle();
		assertNotNull(style);
		assertEquals(1, style.getLength());
		assertEquals(style.getCssText(), "font-family: arial");
		style.setCssText("font-family: Helvetica");
		assertEquals(style.getCssText(), "font-family: Helvetica");
		assertEquals(body.getAttribute("style"), "font-family: Helvetica");
		body.removeAttribute("style");
		// Upper case
		body.setAttribute("STYLE", "font-family: Arial");
		assertTrue(body.hasAttributes());
		assertTrue(body.hasAttribute("STYLE"));
		assertEquals(body.getAttribute("STYLE"), "font-family: Arial");
		style = body.getStyle();
		assertNotNull(style);
		assertEquals(1, style.getLength());
		assertEquals(style.getCssText(), "font-family: arial");
	}

	@Test
	public void getChildren() {
		document = domImpl.createDocument(null, "HTML", null);
		Element html = document.getDocumentElement();
		Element body = document.createElement("body");
		html.appendChild(body);
		body.appendChild(document.createTextNode("\n   \n"));
		Element div1 = document.createElement("div");
		body.appendChild(div1);
		body.appendChild(document.createTextNode("\n   \n"));
		Element div2 = document.createElement("div");
		body.appendChild(div2);
		body.appendChild(document.createTextNode("\n   \n"));
		Element div3 = document.createElement("div");
		body.appendChild(div3);
		body.appendChild(document.createTextNode("\n   \n"));
		body.appendChild(document.createComment("This is a comment"));
		Element div4 = document.createElement("div");
		body.appendChild(div4);
		body.appendChild(document.createTextNode("\n   \n"));
		body.appendChild(document.createTextNode("\n   \n"));
		HTMLCollection list = body.getChildren();
		assertNotNull(list);
		assertEquals(4, list.getLength());
		assertSame(div1, list.item(0));
		assertSame(div2, list.item(1));
		assertSame(div3, list.item(2));
		assertSame(div4, list.item(3));
		assertNull(list.item(4));
		assertSame(div1, body.getFirstElementChild());
		assertSame(div4, body.getLastElementChild());
		assertEquals(list.getLength(), body.getChildElementCount());
		assertSame(list, body.getChildren());
		list = document.getChildren();
		assertNotNull(list);
		assertEquals(1, list.getLength());
		assertSame(html, list.item(0));
		assertEquals(1, document.getChildElementCount());
		list = html.getChildren();
		assertNotNull(list);

		assertEquals(1, list.getLength());
		assertSame(body, list.item(0));
		assertNull(list.item(1));
		assertNull(list.item(-1));
		assertSame(1, html.getChildElementCount());
		assertSame(body, html.getFirstElementChild());
		assertSame(body, html.getLastElementChild());
		assertSame(list, html.getChildren());
	}

	@Test
	public void getAttributeNS() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		HTMLElementImpl body = (HTMLElementImpl)document.createElement("body");
		Attr idattr = document.createAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "id");
		idattr.setValue("bodyId");
		body.setAttributeNode(idattr);
		assertNull(idattr.getParentNode());
		assertEquals(body.getAttribute("id"), "bodyId");
		assertEquals(body.getAttributeNode("id").getValue(), "bodyId");
		assertEquals(body.getAttributeNodeNS(HTMLDocument.HTML_NAMESPACE_URI, "id").getValue(), "bodyId");
		assertTrue(body.hasAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "id"));
		assertFalse(body.hasAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "foo"));
		assertNull(body.getAttribute("foo"));
		assertNull(body.getAttributeNode("foo"));
		assertNull(body.getAttributeNS("http://www.w3.org/2000/svg", "id"));
		assertNull(body.getAttributeNodeNS("http://www.w3.org/2000/svg", "id"));
		html.appendChild(body);
		body.removeAttribute("foo");
		assertTrue(body.hasAttributes());
		boolean success = false;

		try {
			body.removeAttributeNS("http://www.w3.org/2000/svg", "id");
		} catch (DOMException ex) {
			success = (ex.getCode() == DOMException.NOT_FOUND_ERR);
		}
		assertTrue(success, "throw_NOT_FOUND_ERR");
		assertTrue(body.hasAttribute("id"));
		idattr = body.removeAttributeNode(idattr);
		assertFalse(body.hasAttributes());
		body.setAttributeNode(idattr);
		assertTrue(body.hasAttribute("id"));
		body.removeAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "id");
		assertFalse(body.hasAttribute("id"));
		assertFalse(body.hasAttributes());
		HTMLElementImpl svg = (HTMLElementImpl)document.createElementNS("http://www.w3.org/2000/svg", "svg");
		Attr version = document.createAttributeNS("http://www.w3.org/2000/svg", "version");
		version.setValue("1.1");
		assertEquals(version.getNamespaceURI(), "http://www.w3.org/2000/svg");
		svg.setAttributeNodeNS(version);
		assertEquals(svg.getAttribute("version"), "1.1");
		assertEquals(svg.getAttributeNode("version").getValue(), "1.1");
		assertEquals(svg.getAttributeNodeNS("http://www.w3.org/2000/svg", "version").getValue(), "1.1");
		assertNull(svg.getAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "version"));
		assertNull(svg.getAttributeNodeNS(HTMLDocument.HTML_NAMESPACE_URI, "version"));
		assertFalse(svg.hasAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "version"));
		assertTrue(svg.hasAttributeNS("http://www.w3.org/2000/svg", "version"));

		try {
			svg.removeAttributeNS(HTMLDocument.HTML_NAMESPACE_URI, "version");
		} catch (DOMException ex) {
			success = (ex.getCode() == DOMException.NOT_FOUND_ERR);
		}
		assertTrue(success, "throw_NOT_FOUND_ERR");

		assertTrue(svg.hasAttributeNS("http://www.w3.org/2000/svg", "version"));
		svg.removeAttributeNS("http://www.w3.org/2000/svg", "version");
		assertFalse(svg.hasAttributeNS("http://www.w3.org/2000/svg", "version"));
	}

	@Test
	public void testGetInnerText() {
		document = sampleHtmlFile();
		Element html = document.getDocumentElement();
		ElementImpl body = (ElementImpl)document.createElement("body");
		html.appendChild(body);
		body.appendChild(document.createTextNode("    "));
		Element div = document.createElement("div");
		body.appendChild(div);
		div.appendChild(document.createTextNode("   "));
		Element span1 = document.createElement("span");
		span1.appendChild(document.createTextNode(" span   1 "));
		div.appendChild(span1);
		div.appendChild(document.createTextNode("   "));
		Element span2 = document.createElement("span");
		span2.appendChild(document.createTextNode(" span     2 "));
		span2.setAttribute("style", "text-transform: capitalize");
		div.appendChild(span2);
		Element pre = document.createElement("pre");
		pre.appendChild(document.createTextNode("  white  space   must   be\n   preserved   "));
		div.appendChild(pre);
		Element video = document.createElement("video");
		video.appendChild(document.createTextNode("Your browser doesn't support embedded videos."));
		div.appendChild(video);
		body.appendChild(document.createTextNode("   "));
		Element span3 = document.createElement("span");
		span3.appendChild(document.createTextNode(" span 3"));
		body.appendChild(span3);
		body.appendChild(document.createTextNode("     "));
		body.appendChild(document.createComment("This is a comment"));
		Element span4 = document.createElement("span");
		span4.appendChild(document.createTextNode(" span \n 4 "));
		span4.setAttribute("style", "white-space: pre-line; text-transform: uppercase");
		body.appendChild(span4);
		body.appendChild(document.createTextNode("   "));
		assertNotNull(body.getInnerText());
	}

	@Test
	public void cloneNode() {
		document = sampleHtmlFile();
		HTMLElementImpl html = (HTMLElementImpl)document.getDocumentElement();
		HTMLElementImpl body = (HTMLElementImpl)document.createElement("body");
		html.appendChild(body);
		Attr attr = document.createAttribute("id");
		attr.setValue("bodyId");
		body.setAttributeNode(attr);
		body.setAttribute("class", "fooclass");
		Element div = document.createElement("div");
		body.appendChild(div);
		div.appendChild(document.createTextNode("foo"));
		Element elm = (Element) body.cloneNode(false);
		assertEquals(body.getNodeName(), elm.getNodeName());
		assertEquals(body.getAttributes(), elm.getAttributes());
		elm = (Element) body.cloneNode(false);
		assertFalse(body.isEqualNode(elm));
		elm = (Element) body.cloneNode(true);
		assertTrue(body.isEqualNode(elm));
		assertEquals(elm.getChildNodes().item(0).getNodeName(), "div");
		assertEquals(elm.getChildNodes().item(0).getChildNodes().item(0).getNodeValue(), "foo");
	}

	@Test
	public void testGetStartTag() {
		document = sampleHtmlFile();
		Element elm = document.createElement("p");
		Attr attr = document.createAttribute("foo");
		attr.setValue("bar\"");
		elm.setAttributeNode(attr);
	}

	@Test
	public void testGetTagName() {
		document = sampleHtmlFile();
		Element elm = document.createElement("p");
		assertEquals(elm.getTagName(), "p");
		elm = document.createElementNS("http://www.example.com/examplens", "e:p");
		assertEquals(elm.getTagName(), "e:p");
	}

	@Test
	public void testToString() {
		document = sampleHtmlFile();
		Element elm = document.createElement("p");
		Attr attr = document.createAttribute("foo");
		attr.setValue("bar\"");
		elm.setAttributeNode(attr);
		assertEquals(elm.toString(), "[object HTMLPElement]");
	}

	@Test
	public void testToString2() {
		document = sampleHtmlFile();
		Element elm = document.createElement("link");
		Attr attr = document.createAttribute("href");
		attr.setValue("http://www.example.com/");
		elm.setAttributeNode(attr);
		assertEquals(elm.toString(), "http://www.example.com/");
	}
}
