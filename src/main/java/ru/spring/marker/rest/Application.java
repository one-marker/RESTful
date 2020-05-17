package ru.spring.marker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
		SpringApplication.run(Application.class, args);


//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		factory.setNamespaceAware(true); // never forget this!
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document doc = builder.parse("D:\\database.xml");
//
//		XPathFactory Xfactory = XPathFactory.newInstance();
//
//
//		XPath xpath = Xfactory.newXPath();
//
//
//		XPathExpression expr = xpath.compile("//User");
//
//
//		Object result = expr.evaluate(doc, XPathConstants.NODESET);
//
//		NodeList nodes = (NodeList) result;
//		for (int i = 0; i < nodes.getLength(); i++) {
//			System.err.println(nodes.item(i).getNodeValue());
//		}

		File inputFile = new File("D:\\database.xml");

		DocumentBuilderFactory dbFactory
				= DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();

		System.out.println(new PrintStream(new File("D:\\output-file.txt")));


		NodeList nList = doc.getElementsByTagName("Users");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		}
	}


}
