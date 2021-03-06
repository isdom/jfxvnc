package org.jfxvnc.net.rfb.codec.handshaker;

/*
 * #%L
 * RFB protocol
 * %%
 * Copyright (C) 2015 comtel2000
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;

import org.jfxvnc.net.rfb.codec.ProtocolHandshakeHandler;
import org.jfxvnc.net.rfb.codec.ProtocolVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RfbClientHandshaker {

    private static Logger logger = LoggerFactory.getLogger(RfbClientHandshaker.class);

    public abstract RfbClientDecoder newRfbClientDecoder();

    public abstract RfbClientEncoder newRfbClientEncoder();

    private AtomicBoolean handshakeComplete = new AtomicBoolean(false);

    private final ProtocolVersion version;

    public RfbClientHandshaker(ProtocolVersion version) {
	this.version = version;
    }

    public boolean isHandshakeComplete() {
	return handshakeComplete.get();
    }

    private void setHandshakeComplete() {
	handshakeComplete.set(true);
    }

    public ChannelFuture handshake(Channel channel) {
	return handshake(channel, channel.newPromise());
    }

    public final ChannelFuture handshake(Channel channel, final ChannelPromise promise) {

	channel.writeAndFlush(Unpooled.copiedBuffer(version.getBytes())).addListener(new ChannelFutureListener() {
	    @Override
	    public void operationComplete(ChannelFuture future) {
		if (!future.isSuccess()) {
		    promise.setFailure(future.cause());
		    return;
		}
		if (future.isSuccess()) {
		    ChannelPipeline p = future.channel().pipeline();
		    ChannelHandlerContext ctx = p.context(ProtocolHandshakeHandler.class);
		    p.addBefore(ctx.name(), "rfb-handshake-decoder", newRfbClientDecoder());
		    p.addBefore(ctx.name(), "rfb-handshake-encoder", newRfbClientEncoder());
		    promise.setSuccess();
		} else {
		    promise.setFailure(future.cause());
		}
	    }
	});
	return promise;
    }

    public final void finishHandshake(Channel channel, ProtocolVersion response) {
	setHandshakeComplete();
	
	ChannelPipeline p = channel.pipeline();
	p.remove("rfb-handshake-decoder");
	p.remove("rfb-handshake-encoder");

	logger.info("server {} - client {}", String.valueOf(version).trim(), String.valueOf(response).trim());

    }

}
