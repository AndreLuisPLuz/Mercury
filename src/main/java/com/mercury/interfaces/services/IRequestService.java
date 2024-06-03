package com.mercury.interfaces.services;

import java.util.concurrent.CompletableFuture;

import com.mercury.entity.NodeEntity;
import com.mercury.entity.RequestEntity;

public interface IRequestService {
    CompletableFuture<RequestEntity> insertRequest(RequestEntity request);

    CompletableFuture<Boolean> deleteRequest(RequestEntity request);
    CompletableFuture<Boolean> deleteRequest(Long id);

    CompletableFuture<RequestEntity> updateRequest(RequestEntity request);

    CompletableFuture<RequestEntity> fetchRequestById(Long id);
    CompletableFuture<RequestEntity> fetchRequestByNode(NodeEntity node);
    CompletableFuture<RequestEntity> fetchRequestNyNodeId(Long nodeId);
}
