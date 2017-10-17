package online.achernetsov.fseslspring.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import online.achernetsov.fseslspring.esl.FsChannelInitializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigNetty {
    @Bean
    ServerBootstrap serverBootstrap(
            @Qualifier("bossGroup") EventLoopGroup bossGroup,
            @Qualifier("workerGroup") EventLoopGroup workerGroup,
            FsChannelInitializer channelInitializer
    ) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(channelInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        return serverBootstrap;
    }

    @Bean("bossGroup")
    EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean("workerGroup")
    EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }
}
