/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author nbret00
 */
@Provider
public class NewJaxRsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, Set-Cookie");//origin, content-type, accept, authorization");
        //responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");//GET, POST, PUT, DELETE, OPTIONS, HEAD");
        //responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");  

        MediaType type = responseContext.getMediaType();
        if (type != null) {
            String contentType = type.toString();
            if (!contentType.contains("charset")) {
                contentType = contentType + ";charset=utf-8";
                responseContext.getHeaders().putSingle("Content-Type", contentType);
            }
        }
    }

}
