package com.chetan.service.multitenancy.util;

import com.microsoft.azure.storage.blob.CloudBlobContainer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class BlobCloudContainer {

    private static final InheritableThreadLocal<CloudBlobContainer> currentContainer = new InheritableThreadLocal<>();

    public static CloudBlobContainer getBlobCloudContainer() {
        return currentContainer.get();
    }

    public static void setCurrentContainer(CloudBlobContainer container) {
        currentContainer.set(container);
    }

    public static void clear() {
        currentContainer.remove();
    }
}