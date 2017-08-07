package it.infn.mw.iam.api.scim.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.infn.mw.iam.api.scim.controller.utils.JsonDateSerializer;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ScimX509Certificate {

  @Length(max = 36)
  private final String display;

  private final Boolean primary;
  
  @Length(max = 128)
  private final String subjectDn;

  @Length(max = 128)
  private final String issuerDn;

  private final String pemEncodedCertificate;

  @JsonSerialize(using = JsonDateSerializer.class)
  private final Date created;

  @JsonSerialize(using = JsonDateSerializer.class)
  private final Date lastModified;

  @JsonCreator
  private ScimX509Certificate(@JsonProperty("label") String display, @JsonProperty("primary") Boolean primary,
      @JsonProperty("subjectDn") String subjectDn, 
      @JsonProperty("issuerDn") String issuerDn,  
      @JsonProperty("pemEncodedCertificate") String pemEncodedCertificate) {

    this.display = display;
    this.primary = primary;
    this.subjectDn = subjectDn;
    this.issuerDn = issuerDn;
    this.pemEncodedCertificate = pemEncodedCertificate;
    this.created = this.lastModified = null;
  }

  private ScimX509Certificate(Builder b) {
    this.display = b.display;
    this.primary = b.primary;
    this.subjectDn = b.subjectDn;
    this.issuerDn = b.issuerDn;
    this.created = b.created;
    this.lastModified = b.lastModified;
    this.pemEncodedCertificate = b.pemEncodedCertificate;
  }

  public String getDisplay() {
    return display;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public String getSubjectDn() {
    return subjectDn;
  }

  public String getPemEncodedCertificate() {
    return pemEncodedCertificate;
  }

  public String getIssuerDn() {
    return issuerDn;
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }


  public static class Builder {

    private String display;

    private String subjectDn;

    private String issuerDn;

    private Boolean primary;

    private String pemEncodedCertificate;

    private Date created;

    private Date lastModified;

    public Builder display(String display) {
      this.display = display;
      return this;
    }

    public Builder primary(Boolean primary) {

      this.primary = primary;
      return this;
    }

    public Builder subjectDn(String subjectDn) {
      this.subjectDn = subjectDn;
      return this;
    }

    public Builder issuerDn(String issuerDn) {
      this.issuerDn = issuerDn;
      return this;
    }

    public Builder created(Date created) {
      this.created = created;
      return this;
    }

    public Builder lastModified(Date lastModified) {
      this.lastModified = lastModified;
      return this;
    }

    public Builder pemEncodedCertificate(String certificate) {
      this.pemEncodedCertificate = certificate;
      return this;
    }

    public ScimX509Certificate build() {
      return new ScimX509Certificate(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
