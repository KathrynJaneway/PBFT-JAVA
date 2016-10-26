package de.luckydonald.utils.mockups;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;

/**
 * @author luckydonald
 * @since 26.10.2016
 **/
public class ServerSocketMockup extends ServerSocket {
    public ServerSocketMockup() throws IOException {
    }

    public ServerSocketMockup(int port) throws IOException {
        super(port);
    }

    public ServerSocketMockup(int port, int backlog) throws IOException {
        super(port, backlog);
    }

    public ServerSocketMockup(int port, int backlog, InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
    }

    @Override
    public void bind(SocketAddress endpoint) throws IOException {
        super.bind(endpoint);
    }

    @Override
    public void bind(SocketAddress endpoint, int backlog) throws IOException {
        super.bind(endpoint, backlog);
    }

    @Override
    public InetAddress getInetAddress() {
        throw new UnsupportedOperationException("Not implemented."); // return super.getInetAddress();
    }

    @Override
    public int getLocalPort() {
        throw new UnsupportedOperationException("Not implemented."); // return super.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        throw new UnsupportedOperationException("Not implemented."); // return super.getLocalSocketAddress();
    }

    @Override
    public Socket accept() throws IOException {
        throw new UnsupportedOperationException("Not implemented."); // return super.accept();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public ServerSocketChannel getChannel() {
        throw new UnsupportedOperationException("Not implemented."); // return super.getChannel();
    }

    @Override
    public boolean isBound() {
        throw new UnsupportedOperationException("Not implemented."); // return super.isBound();
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException("Not implemented."); // return super.isClosed();
    }

    @Override
    public synchronized void setSoTimeout(int timeout) throws SocketException {
        super.setSoTimeout(timeout);
    }

    @Override
    public synchronized int getSoTimeout() throws IOException {
        throw new UnsupportedOperationException("Not implemented."); // return super.getSoTimeout();
    }

    @Override
    public void setReuseAddress(boolean on) throws SocketException {
        super.setReuseAddress(on);
    }

    public boolean getReuseAddress() throws SocketException {
        throw new UnsupportedOperationException("Not implemented."); // return super.getReuseAddress();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented."); // return super.toString();
    }

    @Override
    public synchronized void setReceiveBufferSize(int size) throws SocketException {
        super.setReceiveBufferSize(size);
    }

    @Override
    public synchronized int getReceiveBufferSize() throws SocketException {
        throw new UnsupportedOperationException("Not implemented."); // return super.getReceiveBufferSize();
    }

    @Override
    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        super.setPerformancePreferences(connectionTime, latency, bandwidth);
    }
}