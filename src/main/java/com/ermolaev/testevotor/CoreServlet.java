package com.ermolaev.testevotor;

import com.ermolaev.testevotor.domain.RequestType;
import com.ermolaev.testevotor.domain.ResultCode;
import com.ermolaev.testevotor.domain.SqlConstants;
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
import java.sql.SQLException;
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
            xmlRequest.prepare();
            if(RequestType.CREATE_AGT.equals(xmlRequest.getRequestType())) {
                createClient(xmlRequest, resp.getWriter());
            }
            else if(RequestType.GET_BALANCE.equals(xmlRequest.getRequestType())) {
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

    public void createClient(XmlRequest request, Writer writer) throws Exception {

        Optional<String> loginExtra = request.getExtraParam("login");
        Optional<String> passwordExtra = request.getExtraParam("password");

        if(!loginExtra.isPresent() || !passwordExtra.isPresent()) {
            throw new RuntimeException();
        }

        try(Connection conn = DatabaseService.getInstance().getConnection()) {
            final String sqlQuery = SqlConstants.INSERT_USER;
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, loginExtra.get());
            statement.setString(2, passwordExtra.get());

            statement.execute();
            conn.commit();
        }
        catch (SQLException e) {
            if(SqlConstants.DUPLICATE_KEYS == e.getErrorCode()) {
                sendError(ResultCode.USER_ALREADY_EXIST, writer);
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    public void getBalance(XmlRequest request, Writer writer) throws Exception {

        Optional<String> loginExtra = request.getExtraParam("login");
        Optional<String> passwordExtra = request.getExtraParam("password");

        if(!loginExtra.isPresent() || !passwordExtra.isPresent()) {
            throw new RuntimeException();
        }

        try(Connection conn = DatabaseService.getInstance().getConnection()) {
            final String sqlQuery = SqlConstants.SELECT_USER;
            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, loginExtra.get());

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                sendError(ResultCode.USER_NOT_FOUND, writer);
                return;
            }

            String password = resultSet.getString(1);
            BigDecimal balance = resultSet.getBigDecimal(2);

            if(!password.equals(passwordExtra.get())) {
                sendError(ResultCode.INVALIDE_PASSWORD, writer);
                return;
            }

            XmlResponse response = new XmlResponse();
            response.setResultCode(ResultCode.OK);

            XmlExtra balanceExtra = new XmlExtra();
            balanceExtra.setName("balance");
            balanceExtra.setValue(XmlUtils.balanceToString(balance));
            response.getExtras().add(balanceExtra);

            XmlUtils.convertXmlResponseToWriter(response, writer);
        }
    }

    public void sendError(int errorCode, Writer writer) {
        XmlResponse xmlResponse = new XmlResponse();
        xmlResponse.setResultCode(errorCode);
        XmlUtils.convertXmlResponseToWriter(xmlResponse, writer);
    }
}
