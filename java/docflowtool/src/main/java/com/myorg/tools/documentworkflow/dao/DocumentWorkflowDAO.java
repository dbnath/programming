package com.myorg.tools.documentworkflow.dao;

import java.sql.SQLException;
import java.util.List;

import com.myorg.tools.documentworkflow.dto.DocumentDTO;
import com.myorg.tools.documentworkflow.model.DocWkflwProcess;
import com.myorg.tools.documentworkflow.model.DocumentWorkflow;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowDetail;
import com.myorg.tools.documentworkflow.model.DocumentWorkflowStatus;
import com.myorg.tools.documentworkflow.model.User;

public interface DocumentWorkflowDAO {
	
	 /**
	  * 
	  * @param userId - may be null for Inbox population 
	  * @return List of Documents in a workflow for myInbox and Inbox
	  */
	 public List<DocumentWorkflow> getAllDocuments(String userId) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param docId
	  * @return DocumentDetail object corresponding to a particular document
	  */
	 public DocumentWorkflowDetail getDocumentDetail(Integer docId) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param isFinalSubmit
	  * @param docDetailObj
	  * @return true if workflow can be done successfully, false if not
	  */
	 public boolean submitWorkflow(DocumentWorkflow docObj, DocumentWorkflowDetail docDetailObj, Boolean isFinalSubmit) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	 public List<DocWkflwProcess> fetchDocumentWorkflows(List<String> docIds) throws SQLException, Exception;
	 
	 /**
	  * 
	  * @param userId
	  * @param docIds
	  * @return true if assignment can be done successfully, else false
	  */
	 public boolean assignWorkflow(List<DocWkflwProcess> docIds, User user) throws SQLException, Exception;
	 
     public DocumentDTO getDocumentsForMaker(DocumentDTO documentDTO) throws SQLException, Exception;

	 public DocumentDTO getDocumentsForAllCheckers() throws SQLException, Exception;

     public DocumentDTO getDocumentsForChecker(DocumentDTO documentDTO) throws SQLException, Exception;

	 public DocumentDTO getDocumentsForAllSMEs() throws SQLException, Exception;

	 public DocumentDTO getDocumentsForSME(DocumentDTO documentDTO) throws SQLException, Exception;
	 
	 public DocumentDTO getAgreementsForAdminUsers(Integer viewId) throws SQLException, Exception;

	 public DocumentDTO startProcess(DocumentDTO documentDTO) throws SQLException, Exception;
	 
	 public DocumentDTO completeProcess(DocumentDTO documentDTO) throws SQLException, Exception;
	 
	 public DocumentDTO holdProcess(DocumentDTO documentDTO) throws SQLException, Exception;
	 
	 public List<DocumentWorkflowStatus> getWorkflowStatusListByRole(int roleId)throws SQLException, Exception;

}
