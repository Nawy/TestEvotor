package com.ermolaev.testevotor.test;

import com.ermolaev.testevotor.XmlUtils;
import com.ermolaev.testevotor.domain.xml.XmlExtra;
import com.ermolaev.testevotor.domain.xml.XmlRequest;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Ivan Ermolaev
 * @date 10:40 09-11-2016.
 */


public class XmlRequestTest {

    @Test
    public void testShouldReadDefaultCreateAgt() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        XmlRequest request = XmlUtils.parseXmlRequestFromStream(loader.getResourceAsStream("xmls/defaultCreateAgt.xml"));

        assertThat(request.getRequestType()).isEqualTo("CREATE-AGT");
        assertThat(request.getExtras()).contains(new XmlExtra("login", "123456"));
        assertThat(request.getExtras()).contains(new XmlExtra("password", "pwd"));
    }

    @Test
    public void testShouldReadExtraParamsAsParams() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        XmlRequest request = XmlUtils.parseXmlRequestFromStream(loader.getResourceAsStream("xmls/defaultCreateAgt.xml"));
        request.prepare();

        assertThat(request.getRequestType()).isEqualTo("CREATE-AGT");
        assertThat(request.getExtraParam("login").get()).isEqualTo("123456");
        assertThat(request.getExtraParam("password").get()).isEqualTo("pwd");
    }

    @Test
    public void testShouldReadDefaultGetBalance() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        XmlRequest request = XmlUtils.parseXmlRequestFromStream(loader.getResourceAsStream("xmls/defaultGetBalance.xml"));

        assertThat(request.getRequestType()).isEqualTo("GET-BALANCE");
        assertThat(request.getExtras()).contains(new XmlExtra("login", "123456"));
        assertThat(request.getExtras()).contains(new XmlExtra("password", "pwd"));
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidXmlShouldThrowsException() throws Exception {
        ClassLoader loader = getClass().getClassLoader();
        XmlRequest request = XmlUtils.parseXmlRequestFromStream(loader.getResourceAsStream("xmls/invalidXml.xml"));
    }
}
