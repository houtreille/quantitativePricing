The aim of this project is get used with docker and kubernetes.

# Docker

## Dockerfile

The Dockerfile is used to build the image of the service. It is based on the openjdk:8-jdk-alpine image.

The Dockerfile is located in the root of the project.

## Build the image

To build the image, you can use the following command:

```
docker build -t health-service:latest .
```
 or if you use fabric8 plugin :
 ```
  mvn docker:build
  ```

## Run the image

To run the image, you can use the following command:

```
docker run -p 18090:8090 health-service:latest
```
 or if you use fabric8 plugin :
 ```
  mvn docker:run
  ```
## Push the image
    
To push the image, you can use the following command:

```
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
mvn docker:build
mvn docker:run
```
# Kube

## Create the deployment

To create the deployment, you can use the following command:

```
kubectl create -f deployment.yml
```

## Create the service

To create the service, you can use the following command:

```
kubectl create -f service.yml
```

## Get the pods

To get the pods, you can use the following command:

```
kubectl get pods
```

## Get the services

To get the services, you can use the following command:

```
kubectl get services
```

## Get the deployments

To get the deployments, you can use the following command:

```
kubectl get deployments
```

## Get the logs

To get the logs, you can use the following command:

```
kubectl logs <pod-name>
```

## Test the application

To test the application, you can use the following command:

```
minikube service health-service
```

# Kube with helm

delete namespace :

```
kubectl delete namespace quantitative
```

Create a new helm chart definition in a separate directory

```
helm create health-service-deploy
```

then to deploy :

```
helm install hs --namespace quantitative ./health-service-deploy
```

where 
    -install will be the name 
    -quantitative the namespace

go to Lens/minikube and check logs of your deployment/pod

go on your port forwarding and check





