package com.project.webSocket.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;

/**
 * netty 实现webSocket协议
 *
 * @author liangyuding
 * @create 2020-04-15
 */
public class NettySocketServer {

    /**
     * 主线程
     * NioEventLoopGroup 和NioEventLoop 都可以.但是前者使用的是线程池.
     * 其实bossgroup如果服务端开启的是一个端口(大部分都是一个),单线程即可. 默认线程数是 cpu核心数的2倍. 但是也可以通过
     * -Dio.netty.eventLoopThreads 参数在服务端启动的时候指定 . boss用来监控tcp链接,执行
     * server.accept()操作,用于服务端接受客户端的连接
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    /**
     * io线程
     * worker用来处理io事件,处理事件的读写到业务逻辑处理等后续操作,进行SocketChannel的网络读写
     */
    private final EventLoopGroup workGroup = new NioEventLoopGroup(8);
    /**
     * websocket是否开启，默认关闭
     */
    @Value("${netty.websocket.enabled: false}")
    private boolean enabled;
    /**
     * websocket监听端口
     */
    @Value("${netty.websocket.port: 8088}")
    private int port;

    //开启服务
    public void startServer() {
        //netty 启动NIO服务端的辅助启动类，目的降低服务端的开发复杂度
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                //设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
                .group(bossGroup, workGroup)
                //设置通道类型NIO webSocket
                .channel(NioServerSocketChannel.class)
                //协议具体实现,IO事件的处理类，主要用于处理网络I/O时间，例如记录日志，对消息进行编解码等
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });

    }
}
