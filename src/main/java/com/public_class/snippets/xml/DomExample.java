package com.public_class.snippets.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

// http://tutorials.jenkov.com/java-xml/sax.html
// Kubanino @ https://public-class.com/
public class DomExample
{
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        Document document = builder.parse(new FileInputStream("data/sampleXML.xml"));

        // and here You can do whatever You want with that document, loaded into memory already
        System.out.println(document.getDocumentElement());
    }
}
