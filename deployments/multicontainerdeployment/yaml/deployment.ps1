docker login dockerchetan.azurecr.io --username dockerchetan --password VOADY5UT3WaApMjDOHZtfvX4OQJfSN+oJz/1n6s9AQ+ACRD6OEVK
mvn compile -P prod jib:build

az container create --resource-group Development --file deployments/multicontainerdeployment/yaml/deployment.yaml

az container create --name tenant --resource-group Development --image dockerchetan.azurecr.io/multi-tenant-service:latest --registry-login-server dockerchetan.azurecr.io --registry-username dockerchetan --registry-password VOADY5UT3WaApMjDOHZtfvX4OQJfSN+oJz/1n6s9AQ+ACRD6OEVK --dns-name-label tenantsrvc --ports 80 --query ipAddress.fqdn