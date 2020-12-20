stopApp() {
  file=$1
  pid=$2

  if [ "$pid" ];
  then
    if kill -9 $pid
        then echo kill $file $pid DONE
        else echo kill $file $pid FAIL !!!
    fi
  else echo $file is already stopped...
  fi
}


main() {
  for file in *.jar
  do
    pid=$(ps -ef| grep $file | gawk '$0 !~/grep/ {print $2}' | tr -s '\n' ' ')
    stopApp $file "$pid"
  done
}


main

