package com.morrisons.server;

import com.morrisons.handlers.ESpotHandler;
import com.morrisons.handlers.Response;
import com.morrisons.handlers.WCSHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.morrisons.handlers.Request.fromQueryString;

public class WCS extends AbstractHandler {

  private static final Map<String, WCSHandler> handlerMap;
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    handlerMap = new HashMap<String, WCSHandler>();
    handlerMap.put("/ESpotHandler", new ESpotHandler());
  }

  @Override
  public void handle(String target, Request request, HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse) throws IOException, ServletException {
    WCSHandler wcsHandler = handlerMap.get(target);
    String queryString = httpServletRequest.getQueryString();
    Response response = wcsHandler.handle(fromQueryString(queryString));
    httpServletResponse.setContentType("application/json");
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    request.setHandled(true);
    httpServletResponse.getWriter().println(objectMapper.writeValueAsString(response.body));
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(9876);
    server.setHandler(new WCS());
    server.start();
    server.join();
  }
}
