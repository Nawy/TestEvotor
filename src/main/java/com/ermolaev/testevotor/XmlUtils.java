package com.ermolaev.testevotor;

import com.ermolaev.testevotor.domain.xml.XmlRequest;
import com.ermolaev.testevotor.domain.xml.XmlResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.Writer;

/**
 * @author Ivan Ermolaev
 * @date 11:18 09-11-2016.
 */
public class XmlUtils {

    public static XmlRequest parseXmlRequestFromStream(InputStream xmlInputStream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlRequest.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (XmlRequest)jaxbUnmarshaller.unmarshal(xmlInputStream);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertXmlResponseToWriter(XmlResponse response, Writer writer) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(response, writer);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
