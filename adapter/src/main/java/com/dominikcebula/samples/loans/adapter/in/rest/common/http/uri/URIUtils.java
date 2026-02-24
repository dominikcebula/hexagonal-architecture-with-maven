package com.dominikcebula.samples.loans.adapter.in.rest.common.http.uri;

import java.net.URI;

public class URIUtils {
    private URIUtils() {
    }

    public static URI pathTo(String base, long id) {
        return pathTo(base, String.valueOf(id));
    }

    public static URI pathTo(String base, String path) {
        if (!base.endsWith("/"))
            base += "/";

        return URI.create(base).resolve(path);
    }
}
