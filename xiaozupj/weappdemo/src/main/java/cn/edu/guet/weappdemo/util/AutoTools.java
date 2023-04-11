package cn.edu.guet.weappdemo.util;
import cn.edu.guet.weappdemo.service.MenuMapperService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


/**
 * @version 1.0
 * @Author qin
 * @Date 2022/8/10 13:53
 * @功能：提供上传工具以及批量图片命名方法
 */

//将文件的静态图片文件重命名(根据数据库id,type,name编码)=>上传编号数据库
//    type+name+id?=编码(将type和name转成英文组合)
@RestController
public class AutoTools {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    //测试
    @Autowired
    MenuMapperService menuMapperService;
    public static void main(String[] args) throws Exception {
       new AutoTools().Post_Local_Jpg("YTj6ED0mQg8cFzVjkArJfo5oWXbnuKnj");
    }

    public void Find_Mod_Jpg(){
        String path="C:/Users/qin/Pictures/JPG"; //自己定义图片路径，自行扩展命名方式
        File file = new File(path);
        File[] files = file.listFiles();
        int i = 0;
        for (File file1 : files) {
            if (!file1.isDirectory()){
                file1.renameTo(new File(path+i+".jpg")); //JPG0.jpg
                i++;
                System.out.println(file1);
            }
        }
        System.out.println("执行完成");
    }


    //将图片名存到数据库:调用时机管理系统添加菜单时,并上传到服务器1
    public void Post_Local_Jpg(String out_trade_no) throws Exception {
        //File类型必须加文件名，不然会决绝访问？
        File directory = new File("src/main/resources/static/"+out_trade_no+".jpg");//但是有可能会被拒绝访问
        System.out.println(directory.getAbsolutePath());
        FileInputStream fileInputStream = new FileInputStream(directory);
        BufferedInputStream in = new BufferedInputStream(fileInputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        new AutoTools().sshSftp(content, out_trade_no+".jpg");
    }

    //将图片名存到数据库:调用时机管理系统添加菜单时,并上传到服务器2
    void sshSftp(byte[] bytes,String fileName) throws Exception {
        int port = 22;
        String user = "root";
        String password = "Qinzhiwei123";
        String ip = "120.27.199.8";
        // 服务器保存路径
        String filepath = "/home/ftpuser/images/wxpay/";
        Session session = null;
        Channel channel = null;

        JSch jSch = new JSch();

        if(port <=0){
            //连接服务器，采用默认端口
            session = jSch.getSession(user, ip);
        }else{
            //采用指定的端口连接服务器
            session = jSch.getSession(user, ip ,port);
        }

        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(password);//设置密码
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("userauth.gssapi-with-mic","no");
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        OutputStream outstream = null;
        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;

            //进入服务器指定的文件夹
            sftp.cd(filepath);

            //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换一下流就可以了
            outstream = sftp.put(fileName);
            outstream.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关流操作
            if (outstream != null) {
                outstream.flush();
                outstream.close();
            }
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
            System.out.println("上传成功!");
        }
    }

    }




