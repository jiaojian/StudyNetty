package vip.wefuwu.msgpack.handler.codec.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import vip.wefuwu.echo.UserInfo;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        final byte[] array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        MessagePack msgpack = new MessagePack();
        // 想在handler中直接获取对象，这里需要指定Bean类，否则会被解析成列表。
        out.add(msgpack.read(array, UserInfo.class));
    }
}
