# Scenario: Update Java application (WAR) on Tomcat servers with zero downtime on AWS
Solution Highlights:
1. Upload the archive to s3 bucket and download using instance profile and cloud-config
2. Zero-downtime through canary/BG style deployment using terraform
3. Availability health checks for application availabity before any 
4. Switch between deployment using target groups
5. Blue/green launch configs and auto-scalling groups associated with the target group


## Sample App
### Run Locally
```
cd app
# Build and run WAR setup (tomcat+war)

docker build -t funfact:java-war-1.0 -f Dockerfile.tomcat .
docker run -d -p 8080:8080 funfact:java-war-1.0

# Test runnging app
open http://localhost:8080
curl http://localhost:8080/health
```

### Build
```
cd app
mvn package -Pwar

# No tests
mvn package -Pwar -Dmaven.test.skip=true -Dcheckstyle.skip=true -Dmaven.javadoc.skip=true --fail-never
```

### Upload
```
./scripts/upload.sh \
  -b bucket \
  -w ./app/target/FunfactApplication.war \
  -v 1.1.0 \
  -n funfact.war
```

## Terraform
### Setup Infra
```
cd terraform
terraform init -backend-config=./backend.tfvars
terraform validate
terraform plan -var "traffic_distribution=blue" -out tfplan
terraform apply tfplan
```
The script should spit out the public DNS. Validate app comes up correctly with 100% traffic going to the blue environment.

### Upgrade the app
1. Update `terraform.tfvars` blue/green archive values as needed.

2. Apply terraform scripts for gradual roll-out (pipeline-steps)
```
cd terraform
terraform init -backend-config=./backend.tfvars
terraform plan -var "traffic_distribution=blue-90" -out tfplan
terraform apply tfplan

# Validate that 10% traffic is going to the new app

terraform plan -var "traffic_distribution=split" -out tfplan
terraform apply tfplan

# Validate that 50% traffic is going to the new app

terraform plan -var "traffic_distribution=green" -out tfplan
terraform apply tfplan

# Validate that 100% traffic is going to the new app
```

3. Flip the blue/green variable values in the next upgrade cycle

