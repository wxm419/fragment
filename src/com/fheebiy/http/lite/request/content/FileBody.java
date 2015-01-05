package com.fheebiy.http.lite.request.content;

import com.fheebiy.http.lite.data.Consts;

import java.io.File;

/**
 * @author MaTianyu
 * @date 14-7-29
 */
public class FileBody extends HttpBody {
    public File file;

    public FileBody(File file) {
        this(file, Consts.MIME_TYPE_OCTET_STREAM);
    }

    public FileBody(File file, String contentType) {
        this.file = file;
        this.contentType = contentType;
    }
}
