package vip.wefuwu.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final int sendNumber;
    private int counter;
    static final String ECHO_REQ = "Hi, Jiaojian. Welcome to Netty.$_";

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = UserInfo();
        for(UserInfo userInfo : userInfos) {
            ctx.writeAndFlush(userInfo);
//            ctx.write(userInfo);
        }
//        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + " times receive server : [" + msg + "]");
//        ctx.write(msg);   // 为什么这句？
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    private UserInfo[] UserInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo;
        for (int i=0; i<sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
