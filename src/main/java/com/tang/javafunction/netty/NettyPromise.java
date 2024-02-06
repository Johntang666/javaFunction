package com.tang.javafunction.netty;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ExecutionException;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/2/2 13:50
 */
public class NettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Promise<String> promise = new DefaultPromise<>(new DefaultEventLoop());
        System.out.println(promise.isSuccess());    //在一开始肯定不是成功的
        promise.setSuccess("lbwnb");    //设定成功
        System.out.println(promise.isSuccess());   //再次获取，可以发现确实成功了
        System.out.println(promise.get());    //
    }
}
