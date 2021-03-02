import time
import os
import datetime
import platform

log = print
from watchdog.observers import Observer
from watchdog.events import PatternMatchingEventHandler


class AutoTest(PatternMatchingEventHandler):
    patterns = [
        "*.java",
        # "*.py",
        # "*.gua",
        # "*.a16",
    ]

    def process(self, event):
        log('auto test', os.getcwd())
        log(datetime.datetime.now().strftime("%Y/%m/%d %I:%M:%S %p"))
        self.run_test_guas()

    @staticmethod
    def run_test_guas():
        os_name = platform.system()
        # log('os name', os_name)
        if os_name == 'Windows':
            cmd = "D:\\gua\\maven3\\bin\\mvn clean > mvn.log"
            log('mvn clean')
            os.system(cmd)

            cmd = "D:\\gua\\maven3\\bin\\mvn package > mvn.log"
            os.system(cmd)
            log('mvn package')

            cmd = "java -jar E:\\working\\mycode\plugins\\common-utils\\target\\common-utils-1.0-SNAPSHOT.jar"
            # log('run')
            os.system(cmd)
            # gualang = 'chcp 65001 && c:\\gualang_ide\\portable-data\\data\\user-data\\gualang\\gualang.exe'
        else:
            pass
            # gualang = '/Applications/gualang/code-portable-data/user-data/gualang/gualang.mac'


    def on_modified(self, event):
        self.process(event)


def main():
    path = '.'
    observer = Observer()
    observer.schedule(AutoTest(), path=path, recursive=True)
    observer.start()

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()

    observer.join()


if __name__ == '__main__':
    main()
