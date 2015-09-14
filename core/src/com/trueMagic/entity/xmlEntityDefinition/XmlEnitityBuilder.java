package com.trueMagic.entity.xmlEntityDefinition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.trueMagic.entity.Entities;
import com.trueMagic.entity.ProcessedArchetypeBuilder;
import org.jbox2d.dynamics.World;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class XmlEnitityBuilder {

    public static void processFiles(World pWorld, com.artemis.World ceWorld, Entities entities) {

        HashMap<String, XmlTagProcessor> processors = new HashMap<>();
        PhysicsTagProcessor physicsTagProcessor = new PhysicsTagProcessor(pWorld, ceWorld);
        processors.put(physicsTagProcessor.getTagName(), physicsTagProcessor);

        FileHandle entityFolder = Gdx.files.external("/Entities");
        FileHandle[] entityFiles = entityFolder.list();
        for(FileHandle entityFile: entityFiles) {
            Document document;
            try {
                DocumentBuilderFactory dbf = new DocumentBuilderFactoryImpl();
                DocumentBuilder db = dbf.newDocumentBuilder();
                document = db.parse(entityFile.file());

                Element rootElement = document.getDocumentElement();

                NodeList entityNodes = rootElement.getElementsByTagName("Entity");
                for(int i = 0; i < entityNodes.getLength(); i++) {
                    Element entityElement = (Element)entityNodes.item(i);
                    String name = entityElement.getAttribute("name");
                    ProcessedArchetypeBuilder builder = new ProcessedArchetypeBuilder();
                    for(String tagName : processors.keySet()) {
                        Element entitySubElement = (Element)entityElement.getElementsByTagName(tagName).item(0);
                        processors.get(tagName).process(builder, entitySubElement);
                    }
                    entities.add(name, builder);
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
