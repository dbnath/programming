package com.myorg.tools.documentworkflow.rest.resources.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.myorg.tools.documentworkflow.constant.Constants;
import com.myorg.tools.documentworkflow.dao.DocumentWorkflowDAO;
import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowProcess;
import com.myorg.tools.documentworkflow.model.User;
import com.myorg.tools.documentworkflow.rest.resources.BaseResource;
import com.myorg.tools.documentworkflow.rest.resources.DocumentWorkflowService;
import com.myorg.tools.documentworkflow.util.DocumentWorkflowToolUtility;

public class DocumentWorkflowServiceImpl extends BaseResource implements DocumentWorkflowService {

	static Logger log = Logger.getLogger(DocumentWorkflowServiceImpl.class.getName());
	
	private DocumentWorkflowDAO documentDAO;
	
	/**
	 * @return the documentDAO
	 */
	public DocumentWorkflowDAO getDocumentDAO() {
		return documentDAO;
	}

	/**
	 * @param documentDAO
	 * the documentDAO to set
	 */
	public void setDocumentDAO(DocumentWorkflowDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	
	private static Map<Integer, Integer> statusGroupMap = new HashMap<Integer, Integer>();
	
	static{
		statusGroupMap.put(1, 1);
		statusGroupMap.put(2, 1);
		statusGroupMap.put(3, 2);
		statusGroupMap.put(4, 2);
		statusGroupMap.put(5, 3);
		statusGroupMap.put(6, 3);
		statusGroupMap.put(7, 1);
		statusGroupMap.put(8, 1);
	}
	
	public Response getAllDocuments(String userId) {
		try {
			log.debug("Inside getAllDocuments userId = "+userId);
			List<DocumentWorkflow> documentList = documentDAO
					.getAllDocuments(userId);
			return Response.ok().entity(documentList).build();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return Response.serverError().build();
		}
	}

	public Response getDocumentDetail(Integer docId) {
		try {
			log.debug("docId" +docId);
			DocumentWorkflowDetail documentWorkflowDetail = documentDAO
					.getDocumentDetail(docId);
			return Response.ok().entity(documentWorkflowDetail).build();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return Response.serverError().build();
		}
	}

	public Response submitWorkflow(DocumentWorkflowProcess docWorkflowProcess) {
		User user = getLoggedInUser();
		String userId = user.getUserId();
		try {
			
			if(docWorkflowProcess != null){
				Boolean isFinalSubmit = docWorkflowProcess.getIsFinalSubmit();
				DocumentWorkflow docObj = docWorkflowProcess.getDocObj();
				DocumentWorkflowDetail docDetailObj = docWorkflowProcess.getDocDetail();
				
				if(isFinalSubmit){
					docObj.setAssignedTo(null);
					docObj.setAssignedDt(null);
					
					docObj.setWfStatusId(docObj.getWfStatusId()+1);
					docObj.setWfStatusDesc(null);
				}
				docObj.setLastUpdatedBy(userId);
				docObj.setLastUpdateDt(new Date());
				
				docDetailObj.setLastUpdatedBy(userId);
				docDetailObj.setLastUpdatedDt(new Date());

				log.debug("###### isFinalSubmit "+isFinalSubmit);
				log.debug("###### docObj "+docObj);
				log.debug("###### docDetailObj "+docDetailObj);
				log.debug("###### Submitting workflow for DocID "+docObj.getDocId()+",Doc Type "+docObj.getDocTypeId()+", Status "+docObj.getWfStatusId());
				documentDAO.submitWorkflow(docObj, docDetailObj, isFinalSubmit);
				log.debug("###### Completed workflow for DocID "+docObj.getDocId()+",Doc Type "+docObj.getDocTypeId()+", Status "+docObj.getWfStatusId());
			}
			
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			return Response.serverError().build();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return Response.serverError().build();
		}
	}
	
	/*public Response assignDocuments(List<DocumentWorkflow> docs) {
		try {
			String	userId = getLoggedInUserId();
			System.out.println("###### user id "+userId+" ###### "+docs);
			for (DocumentWorkflow doc : docs) {
				doc.setAssignedTo(userId);
				doc.setAssignedDt(new Date());
				doc.setLastUpdatedBy(userId);
				doc.setLastUpdateDt(new Date());
				doc.setWfStatusId(doc.getWfStatusId()+1);
				doc.setWfStatusDesc(null);
			}
			documentDAO.assignWorkflow(docs);
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response assignDocumentsTo(List<Integer> docIds) {
		try {
			List<DocumentWorkflow> docs = documentDAO.fetchDocumentWorkflows(docIds);
			User user = getLoggedInUser();
			String userId = user.getUserId();
			String userRoleId = user.getRoleId();
			//for (DocumentWorkflow doc : docs) {
			for (int i=0; i<docs.size(); i++) {
				DocumentWorkflow doc = docs.get(i);
				if (DocumentWorkflowToolUtility.areAllObjectsNull(doc.getAssignedTo()) && (Integer.valueOf(userRoleId)==4 || statusGroupMap.get(doc.getWfStatusId())==Integer.valueOf(userRoleId))) {
					doc.setAssignedTo(userId);
					doc.setAssignedDt(new Date());
					doc.setUserRole(userRoleId);
					doc.setWfStatusId(doc.getWfStatusId()+1);
					doc.setWfStatusDesc(null);
					doc.setLastUpdatedBy(userId);
					doc.setLastUpdateDt(new Date());
				} else {
					docs.remove(doc);
				}
			}
			documentDAO.assignWorkflow(docs);
			return Response.ok().entity(Boolean.TRUE).build();
		
          } catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}*/

	@Override
	public Response getAgreementsForAdminUsers(DocumentDTO documentDTO) {
		log.debug("###### "+documentDTO.getRoleId());
		try {
			documentDTO = documentDAO.getAgreementsForAdminUsers(documentDTO.getRoleId());
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return Response.ok(documentDTO).build(); 
	}

	@Override
	public Response getDocumentsForMaker(DocumentDTO documentDTO) {
		try {
			documentDTO = documentDAO.getDocumentsForMaker(documentDTO);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return Response.ok(documentDTO).build();
	}

	@Override
	public DocumentDTO getDocumentsForAllCheckers() {
		DocumentDTO documentDTO = null;
		try {
			documentDTO = documentDAO.getDocumentsForAllCheckers();
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForChecker(DocumentDTO documentDTO) {
		try {
			documentDTO = documentDAO.getDocumentsForChecker(documentDTO);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return documentDTO;
	}
	
	@Override
	public DocumentDTO getDocumentsForAllSMEs() {
		DocumentDTO documentDTO = null;
		try {
			documentDTO = documentDAO.getDocumentsForAllSMEs();
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return documentDTO;
	}

	@Override
	public DocumentDTO getDocumentsForSME(DocumentDTO documentDTO) {
		try {
			documentDTO = documentDAO.getDocumentsForSME(documentDTO);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return documentDTO;
	}

	@Override
	public String ping() {
		return "Success";
	}

	@Override
	public Response startProcess(DocumentDTO documentDTO) {
		try {
			//FIXME Grab logged in User id
			documentDTO = documentDAO.startProcess(documentDTO);
			if(documentDTO.isSuccess()){
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
			}else {
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, "Failure");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return Response.ok(documentDTO).build();
	}

	@Override
	public Response completeProcess(DocumentDTO documentDTO) {
		try {
			//FIXME Grab logged in User id
			documentDTO = documentDAO.completeProcess(documentDTO);
			if(documentDTO.isSuccess()){
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
			}else {
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, "Failure");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return Response.ok(documentDTO).build();
	}

	@Override
	public Response holdProcess(DocumentDTO documentDTO) {
		try {
			documentDTO = documentDAO.holdProcess(documentDTO);
			if(documentDTO.isSuccess()){
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
			}else {
				DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, "Failure");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			DocumentWorkflowToolUtility.setResponse(documentDTO, Constants.FAILURE_CODE, e.getMessage());
		}
		return Response.ok(documentDTO).build();
	}
	
	public Response assignWorkflows(List<String> docIds) {
		try {
			List<DocWkflwProcess> docs = documentDAO.fetchDocumentWorkflows(docIds);
			User user = getLoggedInUser();
			documentDAO.assignWorkflow(docs,user);
			return Response.ok().entity(Boolean.TRUE).build();
		
          } catch (Exception e) {
			log.error(e.getMessage(),e);
			return Response.serverError().build();
		}
	}
}
