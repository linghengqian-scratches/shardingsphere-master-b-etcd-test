# shardingsphere-master-b-etcd-test

# Fixed by https://github.com/apache/shardingsphere/pull/29738 and https://github.com/apache/shardingsphere/pull/29755 .

- For https://github.com/apache/shardingsphere/issues/29052 .

- Execute the following command on the Ubuntu 22.04.3 LTS instance with `SDKMAN!`.

```shell
sdk install java 17.0.9-graalce
sdk use java 17.0.9-graalce

git clone git@github.com:apache/shardingsphere.git
cd ./shardingsphere/
git reset --hard 4ff6c5f87fd41b0e0c53d2aba97a985b5c4b42e1
./mvnw clean install -Prelease -T1C -DskipTests -Djacoco.skip=true -Dcheckstyle.skip=true -Drat.skip=true -Dmaven.javadoc.skip=true

cd ../

git clone git@github.com:linghengqian/shardingsphere-master-b-etcd-test.git
cd ./shardingsphere-master-b-etcd-test/
./mvnw clean test
```

- You will find that the `org.apache.shardingsphere:shardingsphere-cluster-mode-repository-etcd` class enters 
an infinite loop at this time and does not throw an error. The unit test is blocked.

- The unit test for CURD operations on H2Database is blocked and cannot exit actively.

```shell
17:05:13.054 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xb3ef4c88, L:/127.0.0.1:53080 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:13.067 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xb3ef4c88, L:/127.0.0.1:53080 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:13.966 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x41c99883, L:/127.0.0.1:44630 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:13.967 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x41c99883, L:/127.0.0.1:44630 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:15.715 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xa818a2f0, L:/127.0.0.1:44648 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:15.716 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xa818a2f0, L:/127.0.0.1:44648 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:17.792 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xc5882928, L:/127.0.0.1:44674 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:17.793 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xc5882928, L:/127.0.0.1:44674 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:21.627 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xd2fe7b72, L:/127.0.0.1:44688 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:21.627 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xd2fe7b72, L:/127.0.0.1:44688 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:29.263 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xdd20aa89, L:/127.0.0.1:43872 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:29.264 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xdd20aa89, L:/127.0.0.1:43872 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:41.723 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x4fa473c8, L:/127.0.0.1:37740 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:41.723 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x4fa473c8, L:/127.0.0.1:37740 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:05:57.152 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x6b5210cf, L:/127.0.0.1:33270 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:05:57.152 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x6b5210cf, L:/127.0.0.1:33270 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:06:22.315 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xb532c2c6, L:/127.0.0.1:49088 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:06:22.315 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xb532c2c6, L:/127.0.0.1:49088 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:06:58.452 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x85ba829d, L:/127.0.0.1:47294 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:06:58.453 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x85ba829d, L:/127.0.0.1:47294 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:08:20.875 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xe68e3cd8, L:/127.0.0.1:41068 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:08:20.875 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0xe68e3cd8, L:/127.0.0.1:41068 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
17:10:00.540 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x0008a09e, L:/127.0.0.1:37476 - R:localhost/127.0.0.1:62391] OUTBOUND SETTINGS: ack=false settings={ENABLE_PUSH=0, MAX_CONCURRENT_STREAMS=0, INITIAL_WINDOW_SIZE=1048576, MAX_HEADER_LIST_SIZE=8192}
17:10:00.540 [vert.x-eventloop-thread-0] DEBUG io.grpc.netty.NettyClientHandler - [id: 0x0008a09e, L:/127.0.0.1:37476 - R:localhost/127.0.0.1:62391] OUTBOUND WINDOW_UPDATE: streamId=0 windowSizeIncrement=983041
```
