package online.achernetsov.fseslspring.esl;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FsChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOG = LoggerFactory.getLogger(FsChannelInitializer.class);

    private final ApplicationContext context;

    public FsChannelInitializer(ApplicationContext context) {
        this.context = context;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        LOG.debug("{}", socketChannel);
        socketChannel.pipeline().addLast(handler());
    }

    private ChannelHandler handler() {
        // each time creates new handler (prototype scope)
        return context.getBean(FsChannelHandler.class);
    }
}
