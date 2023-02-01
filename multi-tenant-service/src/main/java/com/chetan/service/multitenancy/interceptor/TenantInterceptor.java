package com.chetan.service.multitenancy.interceptor;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import com.chetan.service.multitenancy.util.BlobCloudContainer;
import com.chetan.service.multitenancy.util.TenantContext;

import java.net.URISyntaxException;

@Component
public class TenantInterceptor implements WebRequestInterceptor {

    private final String defaultTenant;
    private final String module;

    @Autowired
    private CloudBlobClient cloudBlobClient;

    @Autowired
    public TenantInterceptor(
            @Value("${multitenancy.tenant.default-tenant:#{null}}") String defaultTenant, @Value("${multitenancy.tenant.module:#{null}}") String module) {
        this.defaultTenant = defaultTenant;
        this.module = module;
    }

    @Override
    public void preHandle(WebRequest request) throws URISyntaxException, StorageException {
        String tenantId;
        if (request.getHeader("X-TENANT-ID") != null) {
            tenantId = request.getHeader("X-TENANT-ID");
        } else if (this.defaultTenant != null) {
            tenantId = this.defaultTenant;
        } else {
            tenantId = ((ServletWebRequest) request).getRequest().getServerName().split("\\.")[0];
        }
        TenantContext.setTenantId(tenantId);
        BlobCloudContainer.setCurrentContainer(cloudBlobClient.getContainerReference(tenantId + "-" + module));
    }

    @Override
    public void postHandle(@NonNull WebRequest request, ModelMap model) {
        TenantContext.clear();
        BlobCloudContainer.clear();
    }

    @Override
    public void afterCompletion(@NonNull WebRequest request, Exception ex) {
        // NOOP
    }
}
