package com.sun.base.nio.exampleA;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by louis on 2015/1/26.
 */
public class NIOClient {
    private Selector selector;

    public void init(String ip,int port) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector=Selector.open();

        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void listen(){
        while(true){
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    if (key.isConnectable()) {
                        SocketChannel channel=(SocketChannel)key.channel();
                        //If connection is on the fly ,finished connection
                        if(channel.isConnectionPending()) {
                            channel.finishConnect();
                        }

                        channel.configureBlocking(false);
                        channel.write(ByteBuffer.wrap("Send a message to server.".getBytes()));

                        channel.register(selector, SelectionKey.OP_READ);

                    }else if(key.isReadable()){
                        read(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }
    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        //Received message
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.init("localhost", 8000);
        client.listen();
    }
}
