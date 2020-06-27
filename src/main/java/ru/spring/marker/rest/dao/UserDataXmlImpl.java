package ru.spring.marker.rest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.spring.marker.rest.model.User;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Implementation of the UserData interface providing to work with XML file
 * For XML: in bean id="userData" set class="ru.spring.marker.rest.dao.UserDataXmlImpl"
 */
@Repository
public class UserDataXmlImpl implements UserData {

    private static final Logger logger = LoggerFactory.getLogger(UserDataSQLImpl.class);

    /**
     * The path to the XML file
     */
    @Value("${app.file.xml}")
    String fileXML;

    /**
     * This method adds the user to the XML file.
     * @param user Type of data representing one user
     */
    @Override
    public void addUser(User user)  {

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileXML);
            Element root = document.getDocumentElement();

            Collection<User> users = new ArrayList<>();
            users.add(new User());

            for (User userModel : users) {

                Element newUser = document.createElement("User");

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(user.getName()));
                newUser.appendChild(name);

                Element surname = document.createElement("surname");
                surname.appendChild(document.createTextNode(user.getSurname()));
                newUser.appendChild(surname);

                Element birthday = document.createElement("birthday");
                birthday.appendChild(document.createTextNode(user.getBirthday()));
                newUser.appendChild(birthday);

                root.appendChild(newUser);
            }

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(fileXML);
            transformer.transform(source, result);

            logger.info("User successfully saved. User name: " + user);

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (TransformerConfigurationException e) {
            logger.error(e.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXException e) {
            logger.error(e.getMessage());
        } catch (TransformerException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * This method updates user data. In the XML file, the user is searched by name field
     * @param user Type of data representing one user
     */
    @Override
    public void updateUser(User user) {

        try {

            File inputFile = new File(fileXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/Users/User";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element curElement = (Element) nNode;


                    if (user.getName().equals(curElement.getElementsByTagName("name").
                            item(0).getTextContent())) {

                        curElement.getElementsByTagName("surname")
                                .item(0)
                                .setTextContent(user.getSurname());
                        curElement.getElementsByTagName("birthday")
                                .item(0)
                                .setTextContent(user.getBirthday());

                    }

                }

            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult consoleResult = new StreamResult(fileXML);
            transformer.transform(source, consoleResult);

            logger.info("User successfully update by name. User name: " + user.getName());

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (TransformerConfigurationException e) {
            logger.error(e.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXException e) {
            logger.error(e.getMessage());
        } catch (TransformerException e) {
            logger.error(e.getMessage());
        } catch (XPathExpressionException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * This method removes the user by name field.
     * @param value The name field value
     */
    @Override
    public void removeUser(String value) {
        try {
            File inputFile = new File(fileXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/Users/User";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);


            for (int i = 0; i < nodeList.getLength(); i++) {
                Element curElement = (Element) nodeList.item(i);
                String name = curElement
                        .getElementsByTagName("name")
                        .item(0)
                        .getTextContent();
                if (name.equals(value)) {
                    doc.getDocumentElement().removeChild(curElement);
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult consoleResult = new StreamResult(fileXML);
            transformer.transform(source, consoleResult);
            logger.info("User successfully removed from " + fileXML + ". User name: " + value);

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (TransformerConfigurationException e) {
            logger.error(e.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXException e) {
            logger.error(e.getMessage());
        } catch (TransformerException e) {
            logger.error(e.getMessage());
        } catch (XPathExpressionException e) {
            logger.error(e.getMessage());
        }


    }

    /**
     *
     * @param value The name field value
     * @return a list of users
     */
    @Override
    public List<Map<String, Object>> getUser(String value) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {

            File inputFile = new File(fileXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/Users/User";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String name = eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent();
                    if (name.equals(value)) {
                        list.add(new HashMap<String, Object>() {{

                            String name = eElement
                                    .getElementsByTagName("name")
                                    .item(0)
                                    .getTextContent();

                            put("name", name);
                            put("surname", eElement
                                    .getElementsByTagName("surname")
                                    .item(0)
                                    .getTextContent());
                            put("birthday", eElement
                                    .getElementsByTagName("birthday")
                                    .item(0)
                                    .getTextContent());


                        }});
                    }
                }
            }
            logger.info("User successfully received. User name: " + value);

        } catch(IOException e){
            logger.error(e.getMessage());
        } catch(ParserConfigurationException e){
            logger.error(e.getMessage());
        } catch(SAXException e){
            logger.error(e.getMessage());
        } catch (XPathExpressionException e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    /**
     *
     * @return a list of users
     */
    @Override
    public List<Map<String, Object>> listUsers()  {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {

            File inputFile = new File(fileXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/Users/User";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    list.add(new HashMap<String, Object>() {{
                        put("name", eElement
                                .getElementsByTagName("name")
                                .item(0)
                                .getTextContent());
                        put("surname", eElement
                                .getElementsByTagName("surname")
                                .item(0)
                                .getTextContent());
                        put("birthday", eElement
                                .getElementsByTagName("birthday")
                                .item(0)
                                .getTextContent());
                    }});
                }
            }

            logger.info("Users successfully received from XML");

        } catch (IOException e) {
            logger.error(e.getMessage());
        }  catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXException e) {
            logger.error(e.getMessage());
        } catch (XPathExpressionException e) {
            logger.error(e.getMessage());
        }

        return list;
    }
}
