package com.zel.market.jobs;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.ShellUtil;
import com.zel.commonutils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * <p>
 * 1、备份命令mysqldump格式
 * <p>
 * 格式：mysqldump -h主机名  -P端口 -u用户名 -p密码 –database 数据库名 > 文件名.sql
 * <p>
 * 2、备份MySQL数据库为带删除表的格式
 * <p>
 * 备份MySQL数据库为带删除表的格式，能够让该备份覆盖已有数据库而不需要手动删除原有数据库。
 * <p>
 * mysqldump  --add-drop-table -uusername -ppassword -database databasename > backupfile.sql
 * <p>
 * <p>
 * 12、导入数据库
 * <p>
 * 常用source命令，用use进入到某个数据库，mysql>source d:\test.sql，后面的参数为脚本文件。
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/17
 */
@Component
@Slf4j
public class MysqlBackupJob {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${mysqlhost}")
    private String host;

    @Value("${mysqlport}")
    private String port;

    private String dbname = "user";

    private String fileName = "user_{}.sql";

    /**
     * 每天中午12点触发
     * todo 未完成
     */
    //@Scheduled(cron = "0 0 12 * * ?")
    public void backupDatebase() {

        String outputFile = StrUtil.format(fileName, DateUtil.format(new Date(), DateUtil.YMD_HMS_2));
        System.out.println("执行数据库备份" + outputFile);
        backup(outputFile);
    }

    /**
     * 数据库备份
     *
     * @param outputFile
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean backup(String outputFile) {
        // sudo docker exec -it  Mymysql mysqldump
        String pre = "sudo docker exec -it  Mymysql ";
        String command = String.format("mysqldump -h%s -P%s -u%s -p%s --add-drop-table --databases %s -r %s",
                host, port, username, password, dbname, outputFile);
        command = pre + " " + command;
        System.out.println(command);
        List<String>  strList = ShellUtil.run(command);
        System.out.println(strList);
        return true;
    }

    public static boolean restore(String dbUsername, String dbPassword, String dbName, String sourceFile)
            throws IOException, InterruptedException {
        String[] command = new String[]{
                "mysql",
                "-u" + dbUsername,
                "-p" + dbPassword,
                "-e",
                " source " + sourceFile,
                dbName
        };
        Process runtimeProcess = Runtime.getRuntime().exec(command);
        int processComplete = runtimeProcess.waitFor();
        return processComplete == 0;
    }
}
