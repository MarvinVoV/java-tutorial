package com.sun.base.nio.exampleA;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by louis on 2015/1/26.
 */
public class NIOServer {
    private Selector selector;

    public void init(int port) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.selector=Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen(){
        System.out.println("Server start...");
        while(true){
            try {
                //This method will be block until event trigger
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key=iterator.next();
                    //To avoid repetition
                    iterator.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server=(ServerSocketChannel)key.channel();
                        SocketChannel channel=server.accept();
                        channel.configureBlocking(false);

                        channel.write(ByteBuffer.wrap("Hello client.".getBytes()));
                        channel.register(selector, SelectionKey.OP_READ);
                    }else if (key.isReadable()) {
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
        NIOServer server = new NIOServer();
        server.init(8000);
        server.listen();
    }

}
