package com.grouproom.xyz.domain.memory.service;

import com.grouproom.xyz.domain.memory.dto.request.AddMemoryRequest;
import com.grouproom.xyz.domain.memory.dto.request.MemoryListRequest;
import com.grouproom.xyz.domain.memory.dto.response.AddMemoryResponse;
import com.grouproom.xyz.domain.memory.dto.response.MemoryDetailResponse;
import com.grouproom.xyz.domain.memory.dto.response.MemoryListResponse;
import com.grouproom.xyz.domain.memory.entity.Memory;
import com.grouproom.xyz.global.model.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemoryService {

    void saveMemoryFiles(Memory memory, FileType fileType, List<String> filePaths);

    MemoryListResponse findMemory(Long userSeq, MemoryListRequest memoryListRequest);

    AddMemoryResponse addMemory(Long userSeq, AddMemoryRequest addMemoryRequest, List<MultipartFile> images, List<MultipartFile> audios);

    Boolean removeMemory(Long userSeq, Long memorySeq);

    MemoryListResponse findMyMemory(Long userSeq);

    MemoryListResponse findLikedMemory(Long userSeq);

    void addMemoryLike(Long userSeq, Long memorySeq);

    void removeMemoryLike(Long userSeq, Long memorySeq);

    MemoryDetailResponse findMemoryDetail(Long memorySeq);

    void addMemoryComment(Long userSeq, Long memorySeq, String content);
}