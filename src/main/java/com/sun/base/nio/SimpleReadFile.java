package com.sun.base.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by louis on 2014/11/26.
 */
public class SimpleReadFile {
    /*
     *  mark <= position <= limit <= capacity
     *
     *  Buffer Usage:
     *  step 1: write data into the buffer
     *  step 2: call buffer.flip()
     *  step 3: read data out of the buffer
     *  step 4: call buffer.clear() or buffer.compact()
     *
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\smb.conf", "rw");
        FileChannel fileChannel=file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead=fileChannel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("Read "+bytesRead);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = fileChannel.read(buffer);
        }
        fileChannel.close();

    }
}
