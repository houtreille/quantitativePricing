# Docker

- Create a file named **Dockerfile** for each service

example :
```dockerfile
  FROM openjdk:11
  LABEL authors="mhoutreille"
  MAINTAINER eblood
  COPY target/health-service-0.0.1-SNAPSHOT.jar health-servicee.jar
  ENTRYPOINT ["java","-jar","/health-service.jar"]
```

to build your image :
```
docker build --tag [SERVICE_NAME] .
```

if you want to test your image running locally :

```
 docker run --name NAME -p [MAPPED_PORT]:[PORT] [SERVICE_NAME]:latest -detached
```

## Push your image to your docker repo 

First lets rename the image to comply with your remote repository name, then push it.

```
docker tag [IMAGE_NAME] mhoutreille/[IMAGE_NAME]
docker push mhoutreille/health-service
```

## Add your service to a docker-compose.yml file

```dockerfile
  quantitative-pricing-service:
  container_name: quantitative-pricing-service
  build:
  context: quantitative-pricing-service
  dockerfile: Dockerfile
  image: quantitative-pricing-service:latest
  ports:
  - 18666:8080
  networks:
  - spring-cloud-network
```

```
docker-compose config 
docker-compose up --build
```

if fabric8 is installed with following configuration :


 <plugin>
                <groupId>io.fabric8</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.39.1</version>
                <configuration>

                    <images>
                        <image>
                            <name>docker.io/mhoutreille/health-service</name>
                            <build>
                                <tags>
                                    <tag>${project.version}</tag>
                                </tags>
                                <contextDir>${project.basedir}</contextDir>
                                <args>
                                    <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                                </args>
                            </build>
                            <run>
                                <ports>
                                    <port>18090:8090</port>
                                </ports>
                            </run>
                        </image>
                    </images>
                </configuration>
            </plugin>

just launch the command :

```
mvn docker:build docker:run
```
# Kube

 First install [https://minikube.sigs.k8s.io/docs/start/](Minikube)

 launch minikube
 ```bash
minikube start 
minikube dashboard
```

First lets create a deployment yaml file for deployment and service

```yaml
kind: Service
apiVersion: v1
metadata:
  name: health-service
spec:
  selector:
    app: health-service
  ports:
    - protocol: TCP
      port: 8090
      nodePort: 30001
  type: NodePort
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: health-service
spec:
  selector:
    matchLabels:
      app: health-service
  replicas: 1
  template:
    metadata:
      labels:
        app: health-service
    spec:
      containers:
        - name: health-service
          image: mhoutreille/health-service:latest
          ports:
            - containerPort: 8090
```
and run the command

```bash
kubectl create -f deployment.yaml
```


```bash
kubectl get deployment 
kubectl get services 
```

If we want to have a look at the application logs, we need the Pod ID first:

```bash
kubectl get pods
kubectl logs <podId>
```

Let's test our application (On particular configuration like Darwin+Docker a tunnel we'll be created)
```bash
minikube service health-service
```

# Kube with helm 

delete namespace :

```bash
kubectl delete namespace quantitative
```

Create a new helm chart definition in a separate directory

```bash
helm create health-service-deploy
```

then to deploy :

```bash
helm install hs --namespace quantitative ./health-service-deploy
```

where 
    -install will be the name 
    -quantitative the namespace

go to Lens/minikube and check logs of your deployment/pod 
go on your port forwarding and check 


Links :

[https://www.baeldung.com/spring-boot-minikube]()

https://siweheee.medium.com/deploy-your-programs-onto-minikube-with-docker-and-helm-a68097e8d545
