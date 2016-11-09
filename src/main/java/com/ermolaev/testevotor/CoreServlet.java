package com.ermolaev.testevotor;

import com.ermolaev.testevotor.domain.RequestType;
import com.ermolaev.testevotor.domain.ResultCode;
import com.ermolaev.testevotor.domain.xml.XmlRequest;
import com.ermolaev.testevotor.domain.xml.XmlResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author Ivan Ermolaev
 * @date 09:41 09-11-2016.
 */
public class CoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final XmlRequest xmlRequest = XmlUtils.parseXmlRequestFromStream(req.getInputStream());
            if(xmlRequest.getRequestType() == RequestType.CREATE_AGT) {
                createClient(xmlRequest, resp.getWriter());
            }
            else if(xmlRequest.getRequestType() == RequestType.GET_BALANCE) {
                getBalance(xmlRequest, resp.getWriter());
            }
        }
        catch (Exception e) {
            internalError(resp.getWriter());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("It's work!");
    }

    public void destroy() {

    }

    public void createClient(XmlRequest request, Writer writer) {
        XmlResponse response = new XmlResponse();
        response.setResultCode(ResultCode.USER_ALREADY_EXIST);
        XmlUtils.convertXmlResponseToWriter(response, writer);
    }

    public void getBalance(XmlRequest request, Writer writer) {

    }

    public void internalError(Writer writer) {
        XmlResponse xmlResponse = new XmlResponse();
        xmlResponse.setResultCode(ResultCode.INTERNAL_ERROR);
        XmlUtils.convertXmlResponseToWriter(xmlResponse, writer);
    }
}
