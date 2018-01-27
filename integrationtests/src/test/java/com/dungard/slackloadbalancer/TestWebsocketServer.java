package com.dungard.slackloadbalancer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a websocket server to send and
 * receive messages to/from application
 */
public class TestWebsocketServer extends WebSocketServer {
    private int joined;
    private List<String> lastMessagesReceived = new ArrayList<>();

    protected TestWebsocketServer() {
        super(new InetSocketAddress(80));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        joined++;
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        getLastMessagesReceived().add(message);
    }

    protected int getJoined() {
        return joined;
    }

    protected void setJoined(int joined) {
        this.joined = joined;
    }

    protected List<String> getLastMessagesReceived() {
        return lastMessagesReceived;
    }

    protected void setLastMessagesReceived(List<String> lastMessagesReceived) {
        this.lastMessagesReceived = lastMessagesReceived;
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }
}
