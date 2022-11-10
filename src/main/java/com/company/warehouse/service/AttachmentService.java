package com.company.warehouse.service;

import com.company.warehouse.entity.Attachment;
import com.company.warehouse.entity.AttachmentContent;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.AttachmentContentRepository;
import com.company.warehouse.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }


    public Message add(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file == null) return new Message("File is emty", false);
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();

        Attachment attachment = new Attachment();
        attachment.setFileOriginalName(originalFilename);
        attachment.setContentType(contentType);
        attachment.setSize(size);
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        try {
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        attachmentContentRepository.save(attachmentContent);


        return new Message("Successfully saved", true, attachment.getId());
    }


    public List<Attachment> getAll() {
        return attachmentRepository.findAll();
    }

    public Attachment getById(Integer id) {
        Optional<Attachment> optional = attachmentRepository.findById(id);
        if (optional.isEmpty()) return new Attachment();
        return optional.get();
    }


    public Message edite(Integer id, MultipartHttpServletRequest request) {

        Optional<Attachment> optional = attachmentRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);

        Attachment optionalAttachment = optional.get();

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file == null) return new Message("File is empty", false);
        optionalAttachment.setFileOriginalName(file.getOriginalFilename());
        optionalAttachment.setContentType(file.getContentType());
        optionalAttachment.setSize(file.getSize());
        Attachment savedAttachment = attachmentRepository.save(optionalAttachment);


        AttachmentContent attachmentContent = new AttachmentContent();

        try {
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new Message("Successfully edited", true);
    }


    public Message delete(Integer id) {
        Optional<Attachment> optional = attachmentRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        attachmentRepository.deleteById(id);
        return new Message("Successfully deleted", false);
    }


    public Message download(Integer id, HttpServletResponse response) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()) return new Message("Not founded", false);
        Attachment attachment = optionalAttachment.get();

        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (optionalAttachmentContent.isEmpty()) return new Message("Not founded", false);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        byte[] bytes = attachmentContent.getBytes();


        try {
            response.setHeader("Content-Disposition", "attachment ; filename=\"" + attachment.getFileOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            FileCopyUtils.copy(bytes, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Message("Successfully downloaded", true);
    }
}
