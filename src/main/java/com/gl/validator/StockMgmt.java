package com.gl.validator;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Audited
public class StockMgmt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	private String suplierName;

	private String supplierId;

	@Column(length = 15)
	private String invoiceNumber;

	@Column(length = 20)
	private String txnId;

	private String fileName;

	private Long userId;

	// @NotNull
	@NotAudited
	@OneToOne
	@JoinColumn(name="local_user_id", updatable = false)
	private User user;

	@Column(length = 15)
	private String roleType;

	private int quantity;

	@Column(length = 3)
	private int stockStatus;

	private int previousStockStatus;

	private int currency;

	private String userType;

	private Double totalPrice;

	private String remarks;

	private Long assignerId;

	@Transient
	private String stateInterp; 

	private Integer deleteFlag;

	@Transient
	private String deleteFlagInterp;
	
	private int deviceQuantity;
	
	private Long ceirAdminId;
	

	public String Email(UserProfile userProfile) {
		// TODO Auto-generated constructor stub
		userProfile.setEmail("radhay.1199@gmail.com");
		return userProfile.getEmail();
	}

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public LocalDateTime getCreatedOn() {
		return createdOn;
	}




	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}




	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}




	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}




	public String getSuplierName() {
		return suplierName;
	}




	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}




	public String getSupplierId() {
		return supplierId;
	}




	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}




	public String getInvoiceNumber() {
		return invoiceNumber;
	}




	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}




	public String getTxnId() {
		return txnId;
	}




	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}




	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		user=new User(new UserProfile());
		this.user = user;
	}




	public String getRoleType() {
		return roleType;
	}




	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public int getStockStatus() {
		return stockStatus;
	}




	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}




	public int getPreviousStockStatus() {
		return previousStockStatus;
	}




	public void setPreviousStockStatus(int previousStockStatus) {
		this.previousStockStatus = previousStockStatus;
	}




	public int getCurrency() {
		return currency;
	}




	public void setCurrency(int currency) {
		this.currency = currency;
	}




	public String getUserType() {
		return userType;
	}




	public void setUserType(String userType) {
		this.userType = userType;
	}




	public Double getTotalPrice() {
		return totalPrice;
	}




	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}




	public String getRemarks() {
		return remarks;
	}




	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public Long getAssignerId() {
		return assignerId;
	}




	public void setAssignerId(Long assignerId) {
		this.assignerId = assignerId;
	}




	public String getStateInterp() {
		return stateInterp;
	}




	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}




	public Integer getDeleteFlag() {
		return deleteFlag;
	}




	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}




	public String getDeleteFlagInterp() {
		return deleteFlagInterp;
	}




	public void setDeleteFlagInterp(String deleteFlagInterp) {
		this.deleteFlagInterp = deleteFlagInterp;
	}




	public int getDeviceQuantity() {
		return deviceQuantity;
	}




	public void setDeviceQuantity(int deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}




	public Long getCeirAdminId() {
		return ceirAdminId;
	}




	public void setCeirAdminId(Long ceirAdminId) {
		this.ceirAdminId = ceirAdminId;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockMgmt [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", suplierName=");
		builder.append(suplierName);
		builder.append(", supplierId=");
		builder.append(supplierId);
		builder.append(", invoiceNumber=");
		builder.append(invoiceNumber);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", user=");
		builder.append(user);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", stockStatus=");
		builder.append(stockStatus);
		builder.append(", previousStockStatus=");
		builder.append(previousStockStatus);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", stateInterp=");
		builder.append(stateInterp);
		builder.append("]");
		return builder.toString();
	}

}