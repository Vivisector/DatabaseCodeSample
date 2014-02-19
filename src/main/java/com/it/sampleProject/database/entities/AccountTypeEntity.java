package com.it.sampleProject.database.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "account_type")
public class AccountTypeEntity implements Serializable {

    private static final long serialVersionUID = 7391931263925160562L;

    public AccountTypeEntity() {
    }
    @Id
     @SequenceGenerator(name="account_type_seq", sequenceName="account_type_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="account_type_seq")
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "account_type_name")
    private String name;
    @Column(name = "is_show_body")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean showBody;
    @Column(name = "is_view_config")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean viewConfig;
    @Column(name = "is_edit_config")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean editConfig;
    @Column(name = "is_admin")
    @Type(type = "org.hibernate.type.NumericBooleanType")
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

    public void setShowBody(boolean isShowBody) {
        this.showBody = isShowBody;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isEditConfig() {
        return editConfig;
    }

    public void setEditConfig(boolean editConfig) {
        this.editConfig = editConfig;
    }

    public boolean isViewConfig() {
        return viewConfig;
    }

    public void setViewConfig(boolean viewConfig) {
        this.viewConfig = viewConfig;
    }
}
