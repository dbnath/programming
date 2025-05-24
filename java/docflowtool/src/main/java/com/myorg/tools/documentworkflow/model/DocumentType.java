package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class DocumentType implements Serializable, ReverseMappable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518388688598835567L;
	
	private Integer docTypeId;
	private String docTypeName;
	
	/**
	 * 
	 */
	public DocumentType() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the docTypeId
	 */
	public Integer getDocTypeId() {
		return docTypeId;
	}
	/**
	 * @param docTypeId the docTypeId to set
	 */
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}
	/**
	 * @return the docTypeName
	 */
	public String getDocTypeName() {
		return docTypeName;
	}
	/**
	 * @param docTypeName the docTypeName to set
	 */
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.Mappable#getMapKey()
	 */
	@Override
	public String getMapKey() {
		return docTypeName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.ReverseMappable#getCode()
	 */
	@Override
	public Integer getCode() {
		return docTypeId;
	}
	
	

	
}
