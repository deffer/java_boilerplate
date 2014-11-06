package org.tests.xml;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Validation {

	public static void main(String[] args){
		try {
			validate();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public static boolean validate() throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setValidating(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		builder.setErrorHandler(new ErrorHandler() {
			@Override
			public void error(SAXParseException exception) throws SAXException {
				// do something more useful in each of these handlers
				exception.printStackTrace();
			}
			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				exception.printStackTrace();
			}

			@Override
			public void warning(SAXParseException exception) throws SAXException {
				exception.printStackTrace();
			}
		});

		Document doc = builder.parse(new File("cwmm.xml"));
		return doc.getDoctype() != null;
	}
}
