start() {
  startPort=8001

  for file in *.jar
  do
    # 每个文件开启两个端口
    echo $file $startPort
    nohup java -jar $file --server.port=$startPort &
    startPort=`expr $startPort + 1`

    echo $file $startPort
    nohup java -jar $file --server.port=$startPort &
    startPort=`expr $startPort + 1`
  done
}

start
