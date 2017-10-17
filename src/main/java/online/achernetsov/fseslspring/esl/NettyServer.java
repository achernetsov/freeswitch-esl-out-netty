package online.achernetsov.fseslspring.esl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NettyServer {
    private final ServerBootstrap serverBootstrap;

    public NettyServer(ServerBootstrap serverBootstrap) {
        this.serverBootstrap = serverBootstrap;
    }

    private int port=8084;

    @PostConstruct
    public void run() throws Exception {
        // Bind and start to accept incoming connections.
        ChannelFuture f = serverBootstrap.bind(port).sync();
    }
}