package com.morrisons.wcs;

import com.morrisons.wcs.addresses.UserAddressesHandler;
import com.morrisons.wcs.deliveryTypes.ShippingOptionsHandler;
import com.morrisons.wcs.espots.ESpotHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.morrisons.wcs.Request.fromRequestParams;

public class Server extends AbstractHandler {

  private static final Map<String, WCSHandler> handlerMap;
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    handlerMap = new HashMap<String, WCSHandler>();
    handlerMap.put("/ESpotHandler", new ESpotHandler());
    handlerMap.put("/UserAddressesHandler", new UserAddressesHandler());
    handlerMap.put("/ShippingOptionsHandler"), new ShippingOptionsHandler();
  }

  @Override
  public void handle(String target, Request baseRequest, HttpServletRequest request,
                     HttpServletResponse httpServletResponse) throws IOException, ServletException {
    WCSHandler wcsHandler = handlerMap.get(target);
    Response response = wcsHandler.handle(fromRequestParams(request.getQueryString(), request.getParameterMap()));
    httpServletResponse.setContentType("application/json");
    httpServletResponse.setStatus(response.statusCode);
    baseRequest.setHandled(true);
    httpServletResponse.getWriter().println(objectMapper.writeValueAsString(response.body));
  }

  public static void main(String[] args) throws Exception {
    org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(9876);
    server.setHandler(new Server());
    server.start();
    server.join();
  }
}
