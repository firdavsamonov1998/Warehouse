package com.company.warehouse.controller;

import com.company.warehouse.entity.Attachment;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    private Message add(MultipartHttpServletRequest request) {
        return attachmentService.add(request);
    }

    @GetMapping("/get")
    private List<Attachment> getAll() {
        return attachmentService.getAll();
    }

    @GetMapping("/get/{id}")
    private Attachment getById(@PathVariable Integer id) {
        return attachmentService.getById(id);
    }


    @PutMapping("/edite/{id}")
    private Message edite(@PathVariable Integer id, MultipartHttpServletRequest request) {
        return attachmentService.edite(id, request);
    }

    @DeleteMapping("/delete/{id}")
    private Message delete(@PathVariable Integer id) {
        return attachmentService.delete(id);
    }

    @GetMapping("/download/{id}")
    private Message download(@PathVariable Integer id, HttpServletResponse response) {
        return attachmentService.download(id, response);
    }
}
