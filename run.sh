clean() {
  #清空之前的启动日志
  #echo "" > $./nohup.out
  rm ./nohup.out
}

shop() {
  echo '-----stop-------'
  sh ./stop.sh
}

restart() {
  echo '-----start-------'
  sh ./startup.sh
}

main() {
  clean
  shop
  restart
}

main


