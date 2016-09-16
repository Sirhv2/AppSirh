package co.gov.ideam.sirh.seguridad.view;

import co.gov.ideam.sirh.util.IdeamException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class FinderButtons {
    /**
     * To convert the InputStream to String we use the
     * Reader.read(char[] buffer) method. We iterate until the
     * Reader return -1 which means there's no more data to
     * read. We use the StringWriter class to produce the string.
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();     
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {        
            return "No exiete contenido";
        }
    }
    /**
     * Retorna un mapa con los Id's y nombres de todos los componentes que se 
     * encuentran en el archivo y que corresponden con el tag recibido como
     * parametro
     * @param is
     * @param openTag
     * @return
     * @throws IdeamException
     */
    public static Map getComponentsByTag(InputStream is, String openTag)throws IdeamException{
        Map mapaComponentes = new HashMap();
        if (is == null){
            return mapaComponentes;
        }
        try {
            DocumentBuilderFactory dbf =
                        DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName(openTag);
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                String id = element.getAttribute("id");
                String nombre = element.getAttribute("text");
                mapaComponentes.put(id,nombre);
            }

        } catch (ParserConfigurationException e) {
            throw new IdeamException(e);
        } catch (SAXException e) {
            throw new IdeamException(e);
        } catch (IOException e) {
            throw new IdeamException(e);
        }
        return mapaComponentes;
    }
    
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
           CharacterData cd = (CharacterData) child;
           return cd.getData();
        }
        return "?";
      }    
}
