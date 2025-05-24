package com.myorg.tools.documentworkflow.rest.resources;

import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Provider
@Path("/docadmin")
public interface DocumentAdminService {

	   /* To populate Doc Types*/
	  /* @GET
	   @Path("/doctype")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocType();
	   
	    To populate Doc Repos
	   @GET
	   @Path("/docRepo")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocRepository();
	  
	    To populate Doc Tags Master List 
	   @GET
	   @Path("/doctags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTagsMaster();

	    To populate Doc SubTags Master List 
	   @GET
	   @Path("/docsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocSubTagsMaster();

	    To populate Doc Tags based on doc types 
	   @GET
	   @Path("/doctypetags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTypeTagsMapping(@QueryParam("docTypeId") Integer docTypeId);
	   
	    To populate Unmapped Doc Tags based on doc types 
	   @GET
	   @Path("/docunmappedtypetags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateUnmappedDocTypeTags(@QueryParam("docTypeId") Integer docTypeId);

	    To populate Doc SubTags based on doc tags 
	   @GET
	   @Path("/doctagsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTagSubTagMapping(@QueryParam("docTagId") Integer docTagId);
	   
	    To populate Unmapped Doc SubTags based on doc tags 
	   @GET
	   @Path("/docunmappedtagsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateUnmappedDocTagSubTags(@QueryParam("docTagId") Integer docTagId);
	   
	    To populate Doc Tyape, Tag, SubTags based on doc type 
	   @GET
	   @Path("/doctypetagsubtags")
	   @Produces(MediaType.APPLICATION_JSON)	   
	   public Response populateDocTypeTagSubTagsMap(@QueryParam("docTypeId") Integer docTypeId);*/

	   @POST
	   @Path("/uploaddoc")
	   @Consumes(MediaType.MULTIPART_FORM_DATA)
	   @Produces(MediaType.TEXT_HTML)	   
	   //public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId);
	   public Response uploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("userId") String userId);
	   
	   @POST
	   @Path("/uploaderr")
	   @Consumes(MediaType.MULTIPART_FORM_DATA)
	   @Produces(MediaType.TEXT_HTML)	   
	   public Response uploadErrReasons(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId);

	   @POST
	   @Path("/uploadagrtype")
	   @Consumes(MediaType.MULTIPART_FORM_DATA)
	   @Produces(MediaType.TEXT_HTML)	   
	   public Response uploadAgreementType(@FormDataParam("file") InputStream uploadedInputStream,  @FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("path") String path, @FormDataParam("userId") String userId);
	   
	   @GET
	   @Path("/template")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getTemplate();
	   
	   @GET
	   @Path("/doctemplate")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getDocUploadTemplate();	 
	   
	   @GET
	   @Path("/errtemplate")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getErrTypeUploadTemplate();
	   
	   @GET
	   @Path("/agrtemplate")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getArgmtTypeUploadTemplate();		   
	   

	   @GET
	   @Path("/dump")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getAgreementDataDump(@QueryParam("rptFromDt") Date rptFromDt, @QueryParam("rptToDt") Date rptToDt);

	   @GET
	   @Path("/auditdump")
	   @Produces("application/vnd.ms-excel")	   
	   public Response getAgreementsAuditTrail(@QueryParam("rptFromDt") Date rptFromDt, @QueryParam("rptToDt") Date rptToDt);

}
