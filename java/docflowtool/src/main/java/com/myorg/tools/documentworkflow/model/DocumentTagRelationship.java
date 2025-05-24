package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentTagRelationship implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518488689498835668L;
	
	private Integer docId;
	private Integer docTypeId;
	private String docTypeDesc;
	private Integer docTagId;
	private String docTagDesc;
	private Integer docSubTagId;
	private String docSubTagDesc;
	private String createdBy;
	private Date creationDt;
	private String lastUpdatedBy;
	private Date lastUpdatedDt;
	/**
	 * 
	 */
	public DocumentTagRelationship() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the docId
	 */
	public Integer getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(Integer docId) {
		this.docId = docId;
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
	 * @return the docTypeDesc
	 */
	public String getDocTypeDesc() {
		return docTypeDesc;
	}
	/**
	 * @param docTypeDesc the docTypeDesc to set
	 */
	public void setDocTypeDesc(String docTypeDesc) {
		this.docTypeDesc = docTypeDesc;
	}
	/**
	 * @return the docTagId
	 */
	public Integer getDocTagId() {
		return docTagId;
	}
	/**
	 * @param docTagId the docTagId to set
	 */
	public void setDocTagId(Integer docTagId) {
		this.docTagId = docTagId;
	}
	/**
	 * @return the docTagDesc
	 */
	public String getDocTagDesc() {
		return docTagDesc;
	}
	/**
	 * @param docTagDesc the docTagDesc to set
	 */
	public void setDocTagDesc(String docTagDesc) {
		this.docTagDesc = docTagDesc;
	}
	/**
	 * @return the docSubTagId
	 */
	public Integer getDocSubTagId() {
		return docSubTagId;
	}
	/**
	 * @param docSubTagId the docSubTagId to set
	 */
	public void setDocSubTagId(Integer docSubTagId) {
		this.docSubTagId = docSubTagId;
	}
	/**
	 * @return the docSubTagDesc
	 */
	public String getDocSubTagDesc() {
		return docSubTagDesc;
	}
	/**
	 * @param docSubTagDesc the docSubTagDesc to set
	 */
	public void setDocSubTagDesc(String docSubTagDesc) {
		this.docSubTagDesc = docSubTagDesc;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the creationDt
	 */
	public Date getCreationDt() {
		return creationDt;
	}
	/**
	 * @param creationDt the creationDt to set
	 */
	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
	}
	/**
	 * @return the lastUpdatedBy
	 */
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	/**
	 * @param lastUpdatedBy the lastUpdatedBy to set
	 */
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	/**
	 * @return the lastUpdatedDt
	 */
	public Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}
	/**
	 * @param lastUpdatedDt the lastUpdatedDt to set
	 */
	public void setLastUpdatedDt(Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}
}
