/*************************************************************************
 *                                                                       *
 *  EJBCA - Proprietary Modules: Enterprise Certificate Authority        *
 *                                                                       *
 *  Copyright (c), PrimeKey Solutions AB. All rights reserved.           *
 *  The use of the Proprietary Modules are subject to specific           * 
 *  commercial license terms.                                            *
 *                                                                       *
 *************************************************************************/

package org.ejbca.ui.web.rest.api.io.request;

import org.ejbca.core.protocol.rest.EnrollPkcs10CertificateRequest;

/**
 * A class representing the input for certificate request REST method.
 */
public class CertificateRequestRestRequest {
    private String certificateRequest;
    private String username;
    private String password;
    private boolean includeChain;
    private String certificateAuthorityName;
    
    public CertificateRequestRestRequest() {
    }
    
    public String getCertificateRequest() {
        return certificateRequest;
    }
    
    public void setCertificateRequest(String certificateRequest) {
        this.certificateRequest = certificateRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIncludeChain() { 
        return includeChain; 
    }

    public void setIncludeChain(final boolean includeChain) {
        this.includeChain = includeChain;
    }
    
    public String getCertificateAuthorityName() {
        return certificateAuthorityName;
    }

    public void setCertificateAuthorityName(String certificateAuthorityName) {
        this.certificateAuthorityName = certificateAuthorityName;
    }


    /**
     * Returns a converter instance for this class.
     *
     * @return instance of converter for this class.
     */
    public static CertificateRequestRestRequestConverter converter() {
        return new CertificateRequestRestRequestConverter();
    }

    /**
     * Converter instance for this class.
     */
    public static class CertificateRequestRestRequestConverter {

        /**
         * Converts a CertificateRequestRestRequest into EnrollPkcs10CertificateRequest.
         *
         * @param certificateRequestRestRequest input.
         *
         * @return EnrollCertificateRestRequest instance.
         */
        public EnrollPkcs10CertificateRequest toEnrollPkcs10CertificateRequest(final CertificateRequestRestRequest certificateRequestRestRequest) {
            return new EnrollPkcs10CertificateRequest.Builder()
                    .certificateRequest(certificateRequestRestRequest.getCertificateRequest())
                    .username(certificateRequestRestRequest.getUsername())
                    .password(certificateRequestRestRequest.getPassword())
                    .includeChain(certificateRequestRestRequest.getIncludeChain())
                    .certificateAuthorityName(certificateRequestRestRequest.getCertificateAuthorityName())
                    .build();
        }
    }
}