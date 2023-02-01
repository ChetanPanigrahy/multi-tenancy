docker login dockerchetan.azurecr.io --username dockerchetan --password VOADY5UT3WaApMjDOHZtfvX4OQJfSN+oJz/1n6s9AQ+ACRD6OEVK
mvn compile -P prod jib:build

echo "Tenant Management API Creation Started.."
az container create --name tenantmgmt --resource-group Development --image dockerchetan.azurecr.io/multi-tenant-management:latest --registry-login-server dockerchetan.azurecr.io --registry-username dockerchetan --registry-password VOADY5UT3WaApMjDOHZtfvX4OQJfSN+oJz/1n6s9AQ+ACRD6OEVK --dns-name-label tenantmgmtsrvc --ports 81 --query ipAddress.fqdn
echo "Tenant Management API Created"



echo "Org API Creation Started.."
az container create --name org --resource-group Development --image dockerchetan.azurecr.io/org-service:latest --registry-login-server dockerchetan.azurecr.io --registry-username dockerchetan --registry-password VOADY5UT3WaApMjDOHZtfvX4OQJfSN+oJz/1n6s9AQ+ACRD6OEVK --dns-name-label orgsrvc --ports 80 --query ipAddress.fqdn
echo "Org API Created"