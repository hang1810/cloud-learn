## Base images 基础镜像 使用jdk8作为基础镜像
FROM java:8
#指定作者
MAINTAINER hang
#暴露容器的8819端口
EXPOSE 8819
#将复制指定的xxl.jar为容器中的job.jar，相当于拷贝到容器中取了个别名
ADD target/one-test-package-1.0-SNAPSHOT.jar /job.jar
#创建一个新的容器并在新的容器中运行命令
RUN bash -c 'touch /job.jar'
#设置时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#相当于在容器中用cmd命令执行jar包  指定外部配置文件 #,"--spring.config.location=/usr/local/project/docker/xxl-job/config/application.yml"
ENTRYPOINT ["java","-jar","/job.jar"]