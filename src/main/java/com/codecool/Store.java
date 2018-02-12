package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Store implements StorageCapable {

    private List<Product> productList;

    private void saveToXml(Product product) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Products");
            doc.appendChild(rootElement);
            for (Product p : getAllProduct()) {
                addProductToDOM(doc, rootElement, p);
            }
            //addProductToDOM(doc, rootElement, product);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("products.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private void addProductToDOM(Document doc, Element rootElement, Product p) {
        Element prEl = doc.createElement("Product");
        rootElement.appendChild(prEl);
        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(p.getName()));
        prEl.appendChild(name);
        Element price = doc.createElement("Price");
        price.appendChild(doc.createTextNode(Integer.toString(p.getPrice())));
        prEl.appendChild(price);

        if (p instanceof CDProduct) {
            CDProduct c = (CDProduct) p;
            Element type = doc.createElement("Type");
            type.appendChild(doc.createTextNode("Cd"));
            prEl.appendChild(type);
            Element size = doc.createElement("Size");
            size.appendChild(doc.createTextNode(Integer.toString(c.getNumOfTracks())));
            prEl.appendChild(size);

        } else {
            BookProduct b = (BookProduct) p;

            Element type = doc.createElement("Type");
            type.appendChild(doc.createTextNode("Book"));
            prEl.appendChild(type);

            Element size = doc.createElement("Size");
            size.appendChild(doc.createTextNode(Integer.toString(b.getNumOfPages())));
            prEl.appendChild(size);
        }
    }


    protected void storeProduct(Product product) {
        productList.add(product);
    }

    protected Product createProduct(String type, String name, int price, int size) {
        if (type.equals("CD")) {
            return new CDProduct(name, price, size);
        } else {
            return new BookProduct(name, price, size);
        }
    }

    public List<Product> loadProducts() throws IOException, SAXException, ParserConfigurationException {
        List<Product> productsLoaded = new ArrayList<>();
        File fXmlFile = new File("products.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("Product");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nodeLoaded = nList.item(temp);

            if (nodeLoaded.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nodeLoaded;

                if (eElement.getElementsByTagName("type").equals("cd")) {
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    int price = Integer.parseInt(eElement.getElementsByTagName("Price").item(0).getTextContent());
                    int size = Integer.parseInt(eElement.getElementsByTagName("Size").item(0).getTextContent());
                    Product productLoaded = new CDProduct(name, price, size);
                    productsLoaded.add(productLoaded);
                } else {
                    // folyton ide jut!
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    int price = Integer.parseInt(eElement.getElementsByTagName("Price").item(0).getTextContent());
                    int size = Integer.parseInt(eElement.getElementsByTagName("Size").item(0).getTextContent());
                    Product productLoaded = new BookProduct(name, price, size);
                    productsLoaded.add(productLoaded);
                }
            }
        }
        return productsLoaded;
    }

    public void store(Product product) {
        saveToXml(product);
        storeProduct(product);
    }
}
