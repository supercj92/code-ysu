#!/bin/sh

main(){
echo 'hello main'
}

main "${@}"

#cd到当前目录，输入后面的命令执行。
#./test.sh