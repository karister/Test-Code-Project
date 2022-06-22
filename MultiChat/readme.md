### 2021/9/11 bug记录
#### 1.server断开重新打开后，client重新连接上后卡在了输入流位置“Client.java-73”，捕获异常为`java.net.SocketException: Socket is closed`但多次回车push输入流后又可以正常通讯，socket重新打开。