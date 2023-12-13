package com.example.watchShop.config.socket;


import com.example.watchShop.jwt.JwtTokenUtil;
import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoServerOptions;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SocketIOServer {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Getter
  private final EngineIoServer mEngineIoServer;
  @Getter
  private final SocketIoServer mSocketIoServer;

  public SocketIOServer() {
    EngineIoServerOptions options = EngineIoServerOptions.newFromDefault();
    options.setAllowedCorsOrigins(new String[]{"*"});
    options.setPingTimeout(30000);
    mEngineIoServer = new EngineIoServer(options);
    mSocketIoServer = new SocketIoServer(mEngineIoServer);
    init();
  }

  private void init() {
    SocketIoNamespace ns = mSocketIoServer.namespace("/");
    ns.on(
        "connection",
        args -> {
          SocketIoSocket socket = (SocketIoSocket) args[0];
          List<String> jwts = socket.getInitialHeaders().get("authorization");
//          if (CollectionUtils.isEmpty(jwts) || StringUtils.isBlank(jwts.get(0))) {
//            disconnectUnauthorizedConnection(socket);
//          } else {
//            try {
//              socket.joinRoom(jwtTokenUtil.getIdFromToken(jwts.get(0).replace("Bearer ", "")));
//              socket.send("message", "welcome");
//            } catch (MalformedJwtException e) {
//              log.error("Socket must have token. DisconnectUnauthorized....");
//              disconnectUnauthorizedConnection(socket);
//            } catch (Exception e) {
//              disconnectUnauthorizedConnection(socket);
//            }
//          }
        });
  }

  private void disconnectUnauthorizedConnection(SocketIoSocket socket) {
    socket.send("connection_refused", "Invalid authentications");
    socket.disconnect(true);
  }
}
