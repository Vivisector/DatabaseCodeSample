/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.it.sampleProject.database.model;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.it.sampleProject.database.entities.AccountTypeEntity;

import java.math.BigInteger;

/**
 * 
 * @author Igor Ivaniuk
 */
public class AccountType {

	private BigInteger id;
	private String name;
	private boolean showBody;
    private boolean viewConfig;
    private boolean editConfig;
    private boolean isAdmin;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShowBody() {
		return showBody;
	}

	public void setShowBody(boolean showBody) {
		this.showBody = showBody;
	}

    public boolean isViewConfig() {
        return viewConfig;
    }

    public void setViewConfig(boolean viewConfig) {
        this.viewConfig = viewConfig;
    }

    public boolean isEditConfig() {
        return editConfig;
    }

    public void setEditConfig(boolean editConfig) {
        this.editConfig = editConfig;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

	public static AccountType mapFromEntity(AccountTypeEntity entity) {
		AccountType accountType = new AccountType();
		accountType.setId(entity.getId());
		accountType.setName(entity.getName());
		accountType.setShowBody(entity.isShowBody());
        accountType.setViewConfig(entity.isViewConfig());
        accountType.setEditConfig(entity.isEditConfig());
        accountType.setAdmin(entity.isAdmin());
		return accountType;
	}

	public static AccountTypeEntity mapToEntity(AccountType accountType, AccountTypeEntity accountTypeEntity) {
		accountTypeEntity.setId(accountType.getId());
		accountTypeEntity.setName(accountType.getName());
		accountTypeEntity.setShowBody(accountType.isShowBody());
        accountTypeEntity.setViewConfig(accountType.isViewConfig());
        accountTypeEntity.setEditConfig(accountType.isEditConfig());
		return accountTypeEntity;
	}

	public static AccountTypeEntity mapToEntity(AccountType accountType) {
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
		return mapToEntity(accountType, accountTypeEntity);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() == AccountType.class && ((AccountType) obj).name.equals(name)) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher()
		       .putString(name, Charsets.UTF_8)
		       .hash();
		return hc.hashCode(); 
	}

}
