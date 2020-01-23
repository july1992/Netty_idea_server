package com.vily.netty2.netty2.netty;


import com.alibaba.fastjson.JSON;
import com.vily.netty2.netty2.bean.ChennelMsg;
import com.vily.netty2.netty2.bean.TypeData;
//import com.vily.netty2.netty2.mq.MQUtils;
import com.vily.netty2.netty2.service.ChennelService;
import com.vily.netty2.netty2.utils.ChannelMapUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *  * description :  如果客户端是netty 用 String ， ctx.writeAndFlush("服务端:"+text);
 *                   如果客户端是websocket 用 TextWebSocketFrame :   ctx.writeAndFlush(new TextWebSocketFrame("服务端:"+text));
 *  * Author : Vily
 *  * Date : 2019-11-13
 *  
 **/

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {



	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {

		String text = textWebSocketFrame.text();

		System.out.println("服务端 content = "+text);
		ctx.writeAndFlush(new TextWebSocketFrame("服务端:"+text));
		ctx.writeAndFlush("服务端2:"+text);


	}
//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//
//		System.out.println("服务端 content = "+msg);
//
//		ctx.writeAndFlush(msg);
//	}




	/**
	 * 连接顺序 1
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("---handlerAdded--- ctxId = "+ctx.channel().id());

	}

	/**
	 * 连接顺序 2
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		System.out.println("---channelRegistered--- ctxId = "+ctx.channel().id());
	}

	/**
	 * 连接顺序 3
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("---channelActive--- ctxId = "+ctx.channel().id());
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端添加，channelId为：" + channelId);
		ctx.writeAndFlush(
				new TextWebSocketFrame(JSON.toJSONString("来至于服务端的响应")));
		ctx.writeAndFlush("来至于服务端的响应");
	}


	/**
	 * 关闭顺序-1
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		System.out.println("---channelInactive--- ctxId = "+ctx.channel().id());

	}


	/**
	 * 关闭顺序-2
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
		System.out.println("---channelUnregistered--- ctxId = "+ctx.channel().id());

	}


	/**
	 * 关闭顺序-3
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端被移除，channelId为：" + channelId);
		

		//通信微服务，改变在线状态  离线
		//ChannelMapUtils 根据channel查询发送者
		String senderNo = ChannelMapUtils.getSenderNo(ctx.channel());

		if(!StringUtils.isEmpty(senderNo)){
			char userType = senderNo.charAt(0);


		}

		//--------------------------------
		ChannelMapUtils.remove(ctx.channel());
		ctx.channel().close();


	}




	/**
	 * 异常捕获
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		String channelId = ctx.channel().id().asShortText();
		System.out.println("客户端发送异常 channelId = "+channelId);
		cause.printStackTrace();
	}


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {

		// 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;

			if (event.state() == IdleState.ALL_IDLE) {
				System.out.println("进入读写空闲...");
				ChennelMsg chatMsgHeart = new ChennelMsg();
				chatMsgHeart.setType(TypeData.PONG);
				chatMsgHeart.setState("server 读写空闲...");

				ctx.writeAndFlush(
						new TextWebSocketFrame(JSON.toJSONString(chatMsgHeart)));
			}
		}

	}


}
