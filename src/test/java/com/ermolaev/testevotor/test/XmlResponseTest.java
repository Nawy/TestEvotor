package com.ermolaev.testevotor.test;

import com.ermolaev.testevotor.XmlUtils;
import com.ermolaev.testevotor.domain.ResultCode;
import com.ermolaev.testevotor.domain.xml.XmlExtra;
import com.ermolaev.testevotor.domain.xml.XmlResponse;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Ivan Ermolaev
 * @date 14:36 09-11-2016.
 */
public class XmlResponseTest {

    public static final String RESULT_CODE_0_PATH = "xmls/resultCode0.xml";
    public static final String RESULT_CODE_1_PATH = "xmls/resultCode1.xml";
    public static final String RESULT_CODE_2_PATH = "xmls/resultCode2.xml";
    public static final String RESULT_CODE_3_PATH = "xmls/resultCode3.xml";
    public static final String RESULT_CODE_4_PATH = "xmls/resultCode4.xml";
    public static final String RESULT_CODE_1_AND_BALANCE = "xmls/resultCode1AndBalance.xml";

    public static String loadFromFile(String path) throws Exception {
        ClassLoader loader = XmlResponseTest.class.getClassLoader();
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                loader.getResource(path).toURI()
                        )
                )
        );
    }

    @Test
    public void testShouldWorkWithResultCode0() throws Exception {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.OK);

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode0Gold = loadFromFile(RESULT_CODE_0_PATH);

        assertThat(writer.toString()).isEqualTo(resultCode0Gold);
    }

    @Test
    public void testShouldWorkWithResultCode1() throws Exception {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.USER_ALREADY_EXIST);

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode1Gold = loadFromFile(RESULT_CODE_1_PATH);

        assertThat(writer.toString()).isEqualTo(resultCode1Gold);
    }

    @Test
    public void testShouldWorkWithResultCode2() throws Exception {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.INTERNAL_ERROR);

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode2Gold = loadFromFile(RESULT_CODE_2_PATH);

        assertThat(writer.toString()).isEqualTo(resultCode2Gold);
    }

    @Test
    public void testShouldWorkWithResultCode3() throws Exception {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.USER_NOT_FOUND);

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode3Gold = loadFromFile(RESULT_CODE_3_PATH);

        assertThat(writer.toString()).isEqualTo(resultCode3Gold);
    }

    @Test
    public void testShouldWorkWithResultCode4() throws Exception {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.INVALIDE_PASSWORD);

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode4Gold = loadFromFile(RESULT_CODE_4_PATH);

        assertThat(writer.toString()).isEqualTo(resultCode4Gold);
    }

    @Test
    public void testShouldReturnCorrectBalance() throws Exception {
        BigDecimal balance = new BigDecimal(3250.123d);
        DecimalFormat df = new DecimalFormat("#.##");

        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.OK);
        response.getExtras().add(new XmlExtra("balance", df.format(balance)));

        Writer writer = new StringWriter();
        XmlUtils.convertXmlResponseToWriter(response, writer);

        String resultCode1AndBalanceGold = loadFromFile(RESULT_CODE_1_AND_BALANCE);

        assertThat(writer.toString()).isEqualTo(resultCode1AndBalanceGold);
    }
}
