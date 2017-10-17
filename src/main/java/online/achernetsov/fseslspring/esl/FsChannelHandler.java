package online.achernetsov.fseslspring.esl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Scope("prototype") // created for each connection from FS
@Component
public class FsChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(FsChannelHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        CharSequence command = "connect\n\n";

        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeCharSequence(command, Charset.defaultCharset());

        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener((ChannelFutureListener) future -> LOG.debug("{}", future));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
