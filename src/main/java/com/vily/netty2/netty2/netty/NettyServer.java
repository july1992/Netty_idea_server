package com.vily.netty2.netty2.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-11-13
 *  
 **/

public class NettyServer {


    private static NettyServer mInstance;

    public static NettyServer getInstance(){

        if(mInstance == null){
            mInstance = new NettyServer();
        }

        return mInstance;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 客户端是netty
//                            pipeline.addLast(new StringDecoder());
//                            pipeline.addLast(new StringEncoder());
//
//                            // 客户端是websocket
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(1024*64));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            // 如果是读空闲或者写空闲，不处理
//                            pipeline.addLast(new IdleStateHandler(0, 0, 120));

                            pipeline.addLast(new ChatHandler());

                        }
                    });

            Channel serverChannel = bootstrap.bind(8485).sync().channel();

            System.out.println("------Netty Server Start------");

            serverChannel.closeFuture().sync();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
