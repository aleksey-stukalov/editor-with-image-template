package com.company.imagefield.web.customer

import com.company.imagefield.entity.Customer

import com.haulmont.cuba.gui.components.AbstractEditor

import com.haulmont.cuba.core.app.FileStorageService
import com.haulmont.cuba.core.entity.FileDescriptor
import com.haulmont.cuba.core.global.FileStorageException
import com.haulmont.cuba.core.global.UuidProvider
import com.haulmont.cuba.gui.components.Embedded
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.components.FileUploadField

import org.slf4j.Logger

import javax.inject.Inject

class CustomerEdit extends AbstractEditor<Customer> {
    
    @Inject
    private Embedded imageDisplay

    @Inject
    private FileUploadField imageUpload

    @Inject
    private FileStorageService fileStorageService

    @Inject
    private FieldGroup fieldGroup
    
    @Inject
    private Logger log

    @Override
    void init(Map<String, Object> params) {
        super.init(params)

        imageUpload.addFileUploadSucceedListener({e ->
                imageDisplay.setSource("img-" + UuidProvider.createUuid(), imageUpload.getFileContent())})
        imageUpload.addAfterValueClearListener({e -> imageDisplay.resetSource()})
        fieldGroup.getFieldComponent("logo").setVisible(false)
    }

    @Override
    void ready() {
        super.ready()

        if (getItem() != null && getItem().getValue("logo") != null) {
            try {
                imageDisplay.setSource("img-" + UuidProvider.createUuid(),
                        new ByteArrayInputStream(fileStorageService.loadFile(getItem().getValue("logo") as FileDescriptor))
                    )
            } catch (FileStorageException e) {
                log.debug("Error showing the image", e)
            }
        } else
            imageDisplay.resetSource()
    }        
    
}