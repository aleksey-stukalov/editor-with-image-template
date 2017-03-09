<%
if (copyright) {
    println '/*'
    println " * ${copyright.replace('\n', '\n * ')}"
    println ' */'
} else {
    print ""
}
%>package ${packageName};

import com.haulmont.cuba.gui.components.AbstractEditor;

<%if (imageProperty?.trim()) {%>
import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.UuidProvider;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.components.FieldGroup;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.Map;
<%}%>

import ${entity.fqn};
<%if (classComment) {%>
${classComment}<%}%>
public class ${controllerName} extends AbstractEditor<${entity.className}> {
    <%if (imageProperty?.trim()) {%>
    @Inject
    private Embedded imageDisplay;

    @Inject
    private FileUploadField imageUpload;

    @Inject
    private FileStorageService fileStorageService;

    @Inject
    private FieldGroup fieldGroup;
    
    @Inject
    private Logger log;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        imageUpload.addFileUploadSucceedListener(e ->
                imageDisplay.setSource("img-" + UuidProvider.createUuid(), imageUpload.getFileContent()));
        imageUpload.addAfterValueClearListener(e -> imageDisplay.resetSource());
        fieldGroup.getFieldComponent("${imageProperty}").setVisible(false);
    }

    @Override
    public void ready() {
        super.ready();

        ${entity.className} loadedItem = getItem();
        if (loadedItem != null && loadedItem.getValue("${imageProperty}") != null) {
            try {
                imageDisplay.setSource("img-" + UuidProvider.createUuid(),
                        new ByteArrayInputStream(fileStorageService.loadFile(loadedItem.getValue("${imageProperty}")))
                    );
            } catch (FileStorageException e) {
                log.debug("Error showing the image", e);
            }
        } else
            imageDisplay.resetSource();
    }    
    <%}%>
}