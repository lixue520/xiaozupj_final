> 前言：基本上每个项目，都会有个上传文件、头像这样的需求，文件可以存储在阿里云、腾讯云、七牛云这样的对象存储服务上，但是使用这些都不能白嫖，这就让人很难受啊。然后就找到了这个Minio，感觉还是很爽的，全部由自己掌控。代码中附带详细解释，不懂的也可以留言或私信，会及时作出回复！
> 
![在这里插入图片描述](https://img-blog.csdnimg.cn/a7f815e6507849dd9d121dc5945eda6c.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)
`地点：`湖南省永州市蓝山县舜河村

`作者`：用心笑*😀

@[TOC](SpringBoot整合Minio 项目中使用自己文件存储服务器！！！)
## 一、前言及环境准备

**minio介绍**: MinIO是根据GNU Affero通用公共许可证v3.0发布的高性能对象存储。
[史上最详细Docker安装Minio](https://editor.csdn.net/md/?articleId=119077809)

**minio特点**：

- 高性能（读/写速度上高达183 GB / 秒 和 171 GB / 秒）
- 可扩展性（扩展从单个群集开始，该群集可以与其他MinIO群集联合以创建全局名称空间, 并在需要时可以跨越多个不同的数据中心。）
- 可存储文件类型多，视频、execl文件、图片等等都是可以的。
- 实战的话 1）文件存储 2） 数据库文件备份等

------

大家都使用过云存储，minio其实也差不多，只是可以更加的方便。

别看我写这么多代码，其实逻辑非常简单，大家安装好minio，直接CV大法就能跑了。😀👨‍💻

对了，如果你需要找一个判断文件类型的工具类，此文也涵盖了。🙆‍♂️

**环境准备**

- 服务器上Docker安装MInio  ☞（[服务器上Docker安装Minio](https://juejin.cn/post/6992212798100996127)） 
- 本地下载Minio：[minio官网](https://min.io/download)

**项目结构**

![在这里插入图片描述](https://img-blog.csdnimg.cn/1fec4682cb624788b603c05fc15b88ec.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


只要搭建好minio服务后，项目编码实际上特别简单。



## 二、项目初始化

### 2.1、新建一个SpringBoot 项目

~~我想这个大家都会哈~~

### 2.2、pom.xml文件 

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<dependencies>
    <!--此处我用的最近更新的minio jar包-->
    <dependency> 
        <groupId>io.minio</groupId>
        <artifactId>minio</artifactId>
        <version>8.2.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
    </dependency>
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.6.5</version>
    </dependency>

    <!--为了兼容性 我用的是jdk11-->
    <dependency>
        <groupId>javax.xml.bind</groupId>
        <artifactId>jaxb-api</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>com.sun.xml.bind</groupId>
        <artifactId>jaxb-impl</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>com.sun.xml.bind</groupId>
        <artifactId>jaxb-core</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>javax.activation</groupId>
        <artifactId>activation</artifactId>
        <version>1.1.1</version>
    </dependency>
</dependencies>
```

### 2.3、yml文件

```yml
spring:
  profiles:
    active: prod
```

```yml
server:
  port: 8085
spring:
  application:
    name: springboot-minio
minio:
  endpoint: http://IP地址 :9000
  port: 9000
  accessKey: 登录账号
  secretKey: 登录密码
  secure: false
  bucket-name: commons # 桶名 我这是给出了一个默认桶名
  image-size: 10485760 # 我在这里设定了 图片文件的最大大小
  file-size: 1073741824 # 此处是设定了文件的最大大小
```

### 2.4、完善包结构

![在这里插入图片描述](https://img-blog.csdnimg.cn/07f6bd8945a24460b7b37090cc00a94a.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


大家随自己习惯哈。（🐕保命）



## 三、敲代码（CV大法）

### 3.1、MinioProperties

存在于config包下，此类的主要作用就是与配置文件进行绑定，方便注入以及后期维护。

```java
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author crush
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 是一个URL，域名，IPv4或者IPv6地址")
     */
    private String endpoint;

    /**
     *     //"TCP/IP端口号"
     */
    private Integer port;

    /**
     *     //"accessKey类似于用户ID，用于唯一标识你的账户"
     */
    private String accessKey;

    /**
     *     //"secretKey是你账户的密码"
     */
    private String secretKey;

    /**
     *     //"如果是true，则用的是https而不是http,默认值是true"
     */
    private boolean secure;

    /**
     *     //"默认存储桶"
     */
    private String bucketName;

    /**
     * 图片的最大大小
     */
    private long imageSize;

    /**
     * 其他文件的最大大小
     */
    private long fileSize;


    /**
     * 官网给出的 构造方法，我只是去爬了一下官网 （狗头保命）
     * 此类是 客户端进行操作的类
     */
    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .credentials(accessKey, secretKey)
                        .endpoint(endpoint,port,secure)
                        .build();
        return minioClient;
    }
}
```

### 3.2、使用到的工具类

1. **FileTypeUtils** ：是我结合Hutool 工具包 再次封装的一个工具类，为了方便调用的返回数据。

   自己觉得还是挺实用的（👩‍🚀🤱）

2. **MinioUtil**：是对minioClient操作的再一次封装。

#### FileTypeUtils

我是将文件分了大类，然后再根据准确的文件后缀名选择文件保存方式。

```java
import cn.hutool.core.io.FileTypeUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: crush
 * @Date: 2021-07-25 22:26
 * version 1.0
 */
public class FileTypeUtils {


    private final static String IMAGE_TYPE = "image/";
    private final static String AUDIO_TYPE = "audio/";
    private final static String VIDEO_TYPE = "video/";
    private final static String APPLICATION_TYPE = "application/";
    private final static String TXT_TYPE = "text/";

    public static String getFileType(MultipartFile multipartFile) {
        InputStream inputStream = null;
        String type = null;
        try {
            inputStream = multipartFile.getInputStream();
            type = FileTypeUtil.getType(inputStream);
            System.out.println(type);
            if (type.equalsIgnoreCase("JPG") || type.equalsIgnoreCase("JPEG")
                    || type.equalsIgnoreCase("GIF") || type.equalsIgnoreCase("PNG")
                    || type.equalsIgnoreCase("BMP") || type.equalsIgnoreCase("PCX")
                    || type.equalsIgnoreCase("TGA") || type.equalsIgnoreCase("PSD")
                    || type.equalsIgnoreCase("TIFF")) {
                return IMAGE_TYPE+type;
            }
            if (type.equalsIgnoreCase("mp3") || type.equalsIgnoreCase("OGG")
                    || type.equalsIgnoreCase("WAV") || type.equalsIgnoreCase("REAL")
                    || type.equalsIgnoreCase("APE") || type.equalsIgnoreCase("MODULE")
                    || type.equalsIgnoreCase("MIDI") || type.equalsIgnoreCase("VQF")
                    || type.equalsIgnoreCase("CD")) {
                return AUDIO_TYPE+type;
            }
            if (type.equalsIgnoreCase("mp4") || type.equalsIgnoreCase("avi")
                    || type.equalsIgnoreCase("MPEG-1") || type.equalsIgnoreCase("RM")
                    || type.equalsIgnoreCase("ASF") || type.equalsIgnoreCase("WMV")
                    || type.equalsIgnoreCase("qlv") || type.equalsIgnoreCase("MPEG-2")
                    || type.equalsIgnoreCase("MPEG4") || type.equalsIgnoreCase("mov")
                    || type.equalsIgnoreCase("3gp")) {
                return VIDEO_TYPE+type;
            }
            if (type.equalsIgnoreCase("doc") || type.equalsIgnoreCase("docx")
                    || type.equalsIgnoreCase("ppt") || type.equalsIgnoreCase("pptx")
                    || type.equalsIgnoreCase("xls") || type.equalsIgnoreCase("xlsx")
                    || type.equalsIgnoreCase("zip")||type.equalsIgnoreCase("jar")) {
                return APPLICATION_TYPE+type;
            }
            if (type.equalsIgnoreCase("txt")) {
                return TXT_TYPE+type;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

#### MinioUtil 

这个就比较多了，毕竟是对minioClient 的再次封装。代码简单，你莫慌，直接CV 完慢慢看🧜‍♂️

```java
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.crush.minio.config.MinioProperties;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import lombok.SneakyThrows;

/**
 * @Author crush
 * @Date 2021/7/25 11:43
 */

@Component
public class MinioUtil {

    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

    public MinioUtil(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    /**
     * 检查存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (found) {
            System.out.println(bucketName + " exists");
        } else {
            System.out.println(bucketName + " does not exist");
        }
        return found;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    @SneakyThrows
    public boolean makeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (!flag) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());

            return true;
        } else {
            return false;
        }
    }

    /**
     * 列出所有存储桶名称
     *
     * @return
     */
    @SneakyThrows
    public List<String> listBucketNames() {
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /**
     * 列出所有存储桶
     *
     * @return
     */
    @SneakyThrows
    public List<Bucket> listBuckets() {
        return minioClient.listBuckets();
    }


    /**
     * 删除存储桶
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public boolean removeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                // 有对象文件，则删除失败
                if (item.size() > 0) {
                    return false;
                }
            }
            // 删除存储桶，注意，只有存储桶为空时才能删除成功。
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            flag = bucketExists(bucketName);
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 列出存储桶中的所有对象名称
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public List<String> listObjectNames(String bucketName) {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }else{
            listObjectNames.add("存储桶不存在");
        }
        return listObjectNames;
    }


    /**
     * 列出存储桶中的所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param multipartFile
     */
    @SneakyThrows
    public void putObject(String bucketName, MultipartFile multipartFile, String filename, String fileType) {
        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(filename).stream(
                        inputStream, -1, minioProperties.getFileSize())
                        .contentType(fileType)
                        .build());
    }


    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(2, TimeUnit.MINUTES)
                            .build());
            System.out.println(url);
        }
        return url;
    }


    /**
     * 删除一个对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     */
    @SneakyThrows
    public boolean removeObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream =
                        minioClient.getObject(
                                GetObjectArgs.builder()
                                        .bucket(bucketName)
                                        .object(objectName)
                                        .build());
                return stream;
            }
        }
        return null;
    }

    /**
     * 获取对象的元数据
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     */
    @SneakyThrows
    public StatObjectResponse statObject(String bucketName, String objectName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse stat =
                    minioClient.statObject(
                            StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return stat;
        }
        return null;
    }

    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     *
     * @param bucketName  存储桶名称
     * @param objectNames 含有要删除的多个object名称的迭代器对象
     * @return
     */
    @SneakyThrows
    public boolean removeObject(String bucketName, List<String> objectNames) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            List<DeleteObject> objects = new LinkedList<>();
            for (int i = 0; i < objectNames.size(); i++) {
                objects.add(new DeleteObject(objectNames.get(i)));
            }
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                System.out.println(
                        "Error in deleting object " + error.objectName() + "; " + error.message());
                return false;
            }
        }
        return true;
    }

    /**
     * 以流的形式获取一个文件对象（断点下载）
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度 (可选，如果无值则代表读到文件结尾)
     * @return
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream =
                        minioClient.getObject(
                                GetObjectArgs.builder()
                                        .bucket(bucketName)
                                        .object(objectName)
                                        .offset(offset)
                                        .length(length)
                                        .build());
                return stream;
            }
        }
        return null;
    }


    /**
     * 通过InputStream上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param inputStream     要上传的流
     * @param contentType     要上传的文件类型 MimeTypeUtils.IMAGE_JPEG_VALUE
     * @return
     */
    @SneakyThrows
    public boolean putObject(String bucketName, String objectName, InputStream inputStream,String contentType) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                            inputStream, -1, minioProperties.getFileSize())
                            .contentType(contentType)
                            .build());
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                return true;
            }
        }
        return false;
    }
}
```



### 3.3、Service层编写

#### MinioService

```java
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;

/**
 * @Author crush
 * @Date 2021/7/25 9:58
 * @Description: MinioService
 */
public interface MinioService {

    /**
     * 判断 bucket是否存在
     *
     * @param bucketName
     * @return
     */
    boolean bucketExists(String bucketName);

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    void makeBucket(String bucketName);

    /**
     * 列出所有存储桶名称
     * @return
     */
    List<String> listBucketName();

    /**
     * 列出所有存储桶 信息
     *
     * @return
     */
    List<Bucket> listBuckets();

    /**
     * 根据桶名删除桶
     * @param bucketName
     */
    boolean removeBucket(String bucketName);

    /**
     * 列出存储桶中的所有对象名称
     * @param bucketName
     * @return
     */
    List<String> listObjectNames(String bucketName);

    /**
     * 文件上传
     *
     * @param multipartFile
     * @param bucketName
     */
    String putObject( MultipartFile multipartFile, String bucketName,String fileType);

    /**
     * 文件流下载
     * @param bucketName
     * @param objectName
     * @return
     */
    InputStream downloadObject(String bucketName, String objectName);


    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     */
    boolean removeObject(String bucketName, String objectName);



    /**
     * 批量删除文件
     * @param bucketName
     * @param objectNameList
     * @return
     */
    boolean removeListObject(String bucketName, List<String> objectNameList);

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    String getObjectUrl(String bucketName,String objectName);
}
```

#### MinioServiceImpl

```java
import com.crush.minio.config.MinioProperties;
import com.crush.minio.service.MinioService;
import com.crush.minio.utils.MinioUtil;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @Author crush
 * @Date 2021/7/25 9:58
 * @Description: MinioServiceImpl
 */
@Service
public class MinioServiceImpl implements MinioService {

    private final MinioUtil minioUtil;
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public MinioServiceImpl(MinioUtil minioUtil, MinioClient minioClient, MinioProperties minioProperties) {
        this.minioUtil = minioUtil;
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }


    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    public boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }


    @Override
    public List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }


    @Override
    public String putObject(MultipartFile file, String bucketName,String fileType) {
        try {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();

            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            minioUtil.putObject(bucketName, file, objectName,fileType);
            return minioProperties.getEndpoint()+"/"+bucketName+"/"+objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        return minioUtil.getObject(bucketName,objectName);
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName,objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName,String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }
}
```

### 3.4、Controller层编写

```java
import com.crush.minio.service.MinioService;
import com.crush.minio.utils.FileTypeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crush
 */
@RequestMapping("/minio")
@RestController
public class MinioController {

    private final MinioService minioService;


    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, String bucketName) {
        String fileType = FileTypeUtils.getFileType(file);
        if (fileType != null) {
            return minioService.putObject(file, bucketName, fileType);
        }
        return "不支持的文件格式。请确认格式,重新上传！！！";
    }

    @PostMapping("/addBucket/{bucketName}")
    public String addBucket(@PathVariable String bucketName) {
        minioService.makeBucket(bucketName);
        return "创建成功！！！";
    }

    @GetMapping("/show/{bucketName}")
    public List<String> show(@PathVariable String bucketName) {
        return minioService.listObjectNames(bucketName);
    }

    @GetMapping("/showBucketName")
    public List<String> showBucketName() {
        return minioService.listBucketName();
    }

    @GetMapping("/showListObjectNameAndDownloadUrl/{bucketName}")
    public Map<String, String> showListObjectNameAndDownloadUrl(@PathVariable String bucketName) {
        Map<String, String> map = new HashMap<>();
        List<String> listObjectNames = minioService.listObjectNames(bucketName);
        String url = "localhost:8085/minio/download/" + bucketName + "/";
        listObjectNames.forEach(System.out::println);
        for (int i = 0; i <listObjectNames.size() ; i++) {
            map.put(listObjectNames.get(i),url+listObjectNames.get(i));
        }
        return map;
    }

    @DeleteMapping("/removeBucket/{bucketName}")
    public String delBucketName(@PathVariable String bucketName) {
        return minioService.removeBucket(bucketName) == true ? "删除成功" : "删除失败";
    }

    @DeleteMapping("/removeObject/{bucketName}/{objectName}")
    public String delObject(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
        return minioService.removeObject(bucketName, objectName) == true ? "删除成功" : "删除失败";
    }

    @DeleteMapping("/removeListObject/{bucketName}")
    public String delListObject(@PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNameList) {
        return minioService.removeListObject(bucketName, objectNameList) == true ? "删除成功" : "删除失败";
    }


    @RequestMapping("/download/{bucketName}/{objectName}")
    public void download(HttpServletResponse response, @PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {
        InputStream in = null;
        try {
            in = minioService.downloadObject(bucketName, objectName);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(objectName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            //将字节从InputStream复制到OutputStream 。
            IOUtils.copy(in, response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
```

主启动没啥要改的，直接跑就欧克拉

莫慌，竟然带大家做了，肯定是要带大家看看测试结果的。

👇

## 四、实战测试

我目前Minio 的所含有的桶

![在这里插入图片描述](https://img-blog.csdnimg.cn/f25c1375411e453ea24c9f294825c35d.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


### 4.1、文件上传



![在这里插入图片描述](https://img-blog.csdnimg.cn/2d8f560b153c4f139ecbdca01c2f1752.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/3e8eea8b6c304d019d9f16117b410783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


在可视化平台上也可以看到已经上传成功了。

![在这里插入图片描述](https://img-blog.csdnimg.cn/5bcbebb122d8438fa1a25aa5d39cd312.png#pic_center)




### 4.2、文件下载

![在这里插入图片描述](https://img-blog.csdnimg.cn/57be11bca4c549029854c41bb395e7db.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


这个就是文件下载接口。

### 4.3、其他

其他的没有一一测试，但是方法命名应该可以给予你提示。

![在这里插入图片描述](https://img-blog.csdnimg.cn/04dfdc6e32024b11bcfb9dd97b7950aa.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)




## 五、自言自语

如若遇到错误或疑惑之处，请留言或私信，会及时给予回复。

Java这条路啊，真是越往前越卷啊。🛌
