1. Build the project.
mvn clean install

2. Push Dockerfile with application instance

docker build --tag {github_username}/kubernetes-demo:{version} .
e.g. docker build --tag ngudkov/kubernetes-demo:1.5 . 

docker login
 
docker push --tag {github_username}/kubernetes-demo:{version}
e.g. docker push ngudkov/kubernetes-demo:1.5


3.Start local kubernetes cluster

choco install minikube

minikube start

4. Run kubernetes deployments and services
To be able to run application in Kubernetes we can use Kompose 
to convert docker-compose.yml to kubernetes deployment and service files.

choco install kubernetes-kompose

kompose convert -o settings.yml

kubectl apply -f settings.yml

5. We can scale our application by executing

kubectl get deployment

kubectl scale --replicas=3 deployment/web

6. For observations we can run kubernetes dashboard to see our deployment status.
minikube dashboard


7. To access your url execute this command
minikube service list
minikube service web --url

To access only one pod:
It will give you port forwarding only for one pod in deployment:

kubectl port-forward deployment/activemq 8161:8161
kubectl port-forward deployment/web 8080:8080


8. Clean up resources and stop minikube

kubectl delete deployments activemq
kubectl delete deployments web

kubectl delete service activemq
kubectl delete service web

minikube stop

