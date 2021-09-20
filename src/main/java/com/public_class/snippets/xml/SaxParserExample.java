package com.public_class.snippets.xml;

import com.sun.org.apache.xml.internal.resolver.readers.SAXParserHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// http://tutorials.jenkov.com/java-xml/sax.html
public class SaxParserExample
{
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        InputStream xmlInput = new FileInputStream("data/sampleXML.xml");
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new SAXCustomHandler();
        saxParser.parse(xmlInput, handler);
    }

    public static class SAXCustomHandler extends DefaultHandler
    {
        // You have much more methods, but it is just to get the idea...
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            System.out.println("Start of: " + qName + ", " + localName);
        }
    }
}
