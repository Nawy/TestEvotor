package com.ermolaev.testevotor;

import com.ermolaev.testevotor.domain.RequestType;
import com.ermolaev.testevotor.domain.ResultCode;
import com.ermolaev.testevotor.domain.xml.XmlExtra;
import com.ermolaev.testevotor.domain.xml.XmlRequest;
import com.ermolaev.testevotor.domain.xml.XmlResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * @author Ivan Ermolaev
 * @date 09:41 09-11-2016.
 */
public class CoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final XmlRequest xmlRequest = XmlUtils.parseXmlRequestFromStream(req.getInputStream());
            if(xmlRequest.getRequestType().equals(RequestType.CREATE_AGT)) {
                createClient(xmlRequest, resp.getWriter());
            }
            else if(xmlRequest.getRequestType().equals(RequestType.GET_BALANCE)) {
                getBalance(xmlRequest, resp.getWriter());
            }
            else {
                sendError(ResultCode.INTERNAL_ERROR, resp.getWriter());
            }
        }
        catch (Exception e) {
            sendError(ResultCode.INTERNAL_ERROR, resp.getWriter());
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

    public void getBalance(XmlRequest request, Writer writer) throws Exception {

        Optional<XmlExtra> loginExtra = request.getExtras().stream().filter(ex -> ex.getName().equals("login")).findFirst();
        Optional<XmlExtra> passwordExtra = request.getExtras().stream().filter(ex -> ex.getName().equals("password")).findFirst();

        if(!loginExtra.isPresent() || !passwordExtra.isPresent()) {
            throw new RuntimeException();
        }

        try(Connection conn = DatabaseService.getInstance().getConnection()) {
            final String sqlGetUsers = "SELECT login, password, balance FROM users WHERE login=?";
            PreparedStatement statement = conn.prepareStatement(sqlGetUsers);
            statement.setString(1, loginExtra.get().getValue());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet == null || resultSet.getFetchSize() == 1) {
                sendError(ResultCode.USER_NOT_FOUND, writer);
            }

            String login = resultSet.getString(1);
            String password = resultSet.getString(2);
            BigDecimal balance = resultSet.getBigDecimal(3);

            if(!password.equals(passwordExtra.get().getValue())) {
                sendError(ResultCode.INVALIDE_PASSWORD, writer);
                return;
            }
        }
    }

    public void sendError(int errorCode, Writer writer) {
        XmlResponse xmlResponse = new XmlResponse();
        xmlResponse.setResultCode(errorCode);
        XmlUtils.convertXmlResponseToWriter(xmlResponse, writer);
    }
}
