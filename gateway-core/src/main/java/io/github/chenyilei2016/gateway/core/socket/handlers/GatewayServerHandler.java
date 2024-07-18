package io.github.chenyilei2016.gateway.core.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.chenyilei2016.gateway.core.ex.CancelConnectException;
import io.github.chenyilei2016.gateway.core.generic.IGenericReference;
import io.github.chenyilei2016.gateway.core.session.GatewaySession;
import io.github.chenyilei2016.gateway.core.session.GatewaySessionFactory;
import io.github.chenyilei2016.gateway.core.socket.BaseHandler;
import io.github.chenyilei2016.gateway.core.socket.agreement.RequestParser;
import io.github.chenyilei2016.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @description 会话服务处理器
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final GatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());

        // 返回信息控制：简单处理
        String uri = request.uri();
        if (uri.equals("/favicon.ico")) return;

        Map<String, Object> inputArgs = new RequestParser(request).parse();
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper(uri);
        Object result = reference.$invoke(inputArgs);

        // 3. 封装返回结果
        DefaultFullHttpResponse response = new ResponseParser().parse(result);
        channel.writeAndFlush(response);

        //增加一次计数, 不释放
//        request.retain();
//        ctx.fireChannelRead(request);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        if (cause instanceof CancelConnectException) {
            ctx.close();
        }
    }
}
