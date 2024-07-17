package ac.su.kiosk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.lang.Exception;

public class WebSocketHandler extends BinaryWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private final List<WebSocketSession> sessions = new ArrayList<>();

    // 웹소켓 클라이언트가 특정 엔드포인트로 연결할 시 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        logger.info("New WebSocket connection established: {}", session.getId());
    }

    // 웹소켓 클라이언트가 데이터를 전송할 때, 여기를 통해서 클라이언트가 전송한 데이터를 전달
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) /*throws Exception*/ {
        logger.info("Received text message: {}", message.getPayload());
        for (WebSocketSession webSocketSession : sessions) {
            try {
                webSocketSession.sendMessage(new TextMessage("Server received: " + message.getPayload()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        ByteBuffer payload = message.getPayload();
        byte[] bytes = new byte[payload.remaining()];
        payload.get(bytes);
        logger.info("Received binary message of length: {}", bytes.length);

        // 음성 데이터를 파일로 저장
        try (FileOutputStream fos = new FileOutputStream("received_audio.webm", true)) {
            fos.write(bytes);
        } catch (IOException e) {
            logger.error("Error saving audio data", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        logger.info("WebSocket connection closed: {}", session.getId());
    }
}
