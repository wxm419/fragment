package com.fheebiy.http.lite.request.content;

import com.fheebiy.http.lite.data.Consts;
import com.fheebiy.http.lite.request.content.multi.AbstractPart;
import com.fheebiy.http.lite.request.content.multi.BoundaryCreater;

import java.util.LinkedList;

/**
 * @author MaTianyu
 * @date 14-7-29
 */
public class MultipartBody extends HttpBody {
    private LinkedList<AbstractPart> httpParts;
    private BoundaryCreater boundaryCreater = new BoundaryCreater();

    public MultipartBody() {
        contentType = Consts.MIME_TYPE_FORM_DATA + Consts.BOUNDARY_PARAM
                + boundaryCreater.getBoundary();
    }

    public LinkedList<AbstractPart> getHttpParts() {
        return httpParts;
    }

    public MultipartBody setHttpParts(LinkedList<AbstractPart> httpParts) {
        this.httpParts = httpParts;
        return this;
    }

    public MultipartBody addPart(AbstractPart part) {
        if (part == null) return this;
        if (httpParts == null) httpParts = new LinkedList<AbstractPart>();
        httpParts.add(part);
        return this;
    }

    public String getBoundary() {
        return boundaryCreater.getBoundary();
    }

    public byte[] getBoundaryLine() {
        return boundaryCreater.getBoundaryLine();
    }

    public byte[] getBoundaryEnd() {
        return boundaryCreater.getBoundaryEnd();
    }
}
