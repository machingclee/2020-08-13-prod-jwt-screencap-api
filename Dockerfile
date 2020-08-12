FROM openjdk:8

EXPOSE 3030


ADD /var/lib/jenkins/workspace/SpringDevOpsProject-ScreencapDb/target/dictionary-0.0.1-SNAPSHOT.jar /home/ec2-user/jar/dictionary-0.0.1-SNAPSHOT.jar


ENTRYPOINT [ "java","-jar","/home/ec2-user/jar/dictionary-0.0.1-SNAPSHOT.jar" ]