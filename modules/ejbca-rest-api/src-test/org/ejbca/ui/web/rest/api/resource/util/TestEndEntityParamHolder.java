/*************************************************************************
 *                                                                       *
 *  EJBCA Community: The OpenSource Certificate Authority                *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
package org.ejbca.ui.web.rest.api.resource.util;

import org.cesecore.authentication.tokens.AuthenticationToken;
import org.cesecore.certificates.ca.X509CA;
import org.cesecore.certificates.certificateprofile.CertificateProfileSessionRemote;
import org.ejbca.core.ejb.ra.EndEntityManagementSessionRemote;
import org.ejbca.core.ejb.ra.raadmin.EndEntityProfileSessionRemote;
import org.ejbca.core.model.SecConst;

import com.keyfactor.util.crypto.algorithm.AlgorithmConstants;

public class TestEndEntityParamHolder {

	private final String testUsername;
	private final String testCertProfileName;
	private final String testEeProfileName;
	private final AuthenticationToken internalAdminToken;
	private final X509CA x509TestCa;
	private final EndEntityProfileSessionRemote endEntityProfileSessionRemote;
	private final CertificateProfileSessionRemote certificateProfileSession;
	private final EndEntityManagementSessionRemote endEntityManagementSession;
	private final int tokenType;
	private final String keyAlgo;
	private final String keySpec;
	private final boolean keyRecoverable;

	private TestEndEntityParamHolder(Builder builder) {
		this.testUsername = builder.testUsername;
		this.testCertProfileName = builder.testCertProfileName;
		this.testEeProfileName = builder.testEeProfileName;
		this.internalAdminToken = builder.internalAdminToken;
		this.x509TestCa = builder.x509TestCa;
		this.endEntityProfileSessionRemote = builder.endEntityProfileSessionRemote;
		this.certificateProfileSession = builder.certificateProfileSession;
		this.endEntityManagementSession = builder.endEntityManagementSession;
		this.tokenType = builder.tokenType;
		this.keyAlgo = builder.keyAlgo;
		this.keySpec = builder.keySpec;
		this.keyRecoverable = builder.keyRecoverable;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getTestUsername() {
		return testUsername;
	}

	public String getTestCertProfileName() {
		return testCertProfileName;
	}

	public String getTestEeProfileName() {
		return testEeProfileName;
	}

	public AuthenticationToken getInternalAdminToken() {
		return internalAdminToken;
	}

	public X509CA getX509TestCa() {
		return x509TestCa;
	}

	public EndEntityProfileSessionRemote getEndEntityProfileSessionRemote() {
		return endEntityProfileSessionRemote;
	}

	public CertificateProfileSessionRemote getCertificateProfileSession() {
		return certificateProfileSession;
	}

	public EndEntityManagementSessionRemote getEndEntityManagementSession() {
		return endEntityManagementSession;
	}

	public int getTokenType() {
        return tokenType;
    }

    public String getKeyAlgo() {
        return keyAlgo;
    }

    public String getKeySpec() {
        return keySpec;
    }

    public boolean isKeyRecoverable() {
        return keyRecoverable;
    }

    public static class Builder {

		private String testUsername;
		private String testCertProfileName;
		private String testEeProfileName;
		private AuthenticationToken internalAdminToken;
		private X509CA x509TestCa;
		private EndEntityProfileSessionRemote endEntityProfileSessionRemote;
		private CertificateProfileSessionRemote certificateProfileSession;
		private EndEntityManagementSessionRemote endEntityManagementSession;
		private int tokenType=-1;
		private String keyAlgo;
	    private String keySpec;
	    private boolean keyRecoverable;

		public Builder withTestUsername(String testUsername) {
			this.testUsername = testUsername;
			return this;
		}

		public Builder withTestCertProfileName(String testCertProfileName) {
			this.testCertProfileName = testCertProfileName;
			return this;
		}

		public Builder withTestEeProfileName(String testEeProfileName) {
			this.testEeProfileName = testEeProfileName;
			return this;
		}

		public Builder withInternalAdminToken(AuthenticationToken internalAdminToken) {
			this.internalAdminToken = internalAdminToken;
			return this;
		}

		public Builder withX509TestCa(X509CA x509TestCa) {
			this.x509TestCa = x509TestCa;
			return this;
		}

		public Builder withEndEntityProfileSessionRemote(EndEntityProfileSessionRemote endEntityProfileSessionRemote) {
			this.endEntityProfileSessionRemote = endEntityProfileSessionRemote;
			return this;
		}

		public Builder withCertificateProfileSession(CertificateProfileSessionRemote certificateProfileSession) {
			this.certificateProfileSession = certificateProfileSession;
			return this;
		}

		public Builder withEndEntityManagementSession(EndEntityManagementSessionRemote endEntityManagementSession) {
			this.endEntityManagementSession = endEntityManagementSession;
			return this;
		}
		
		public Builder withTokenType(int tokenType) {
		    this.tokenType = tokenType;
            return this;
		}
		
		public Builder withKeyAlgo(String keyAlgo) {
            this.keyAlgo = keyAlgo;
            return this;
        }
		
		public Builder withKeySpec(String keySpec) {
            this.keySpec = keySpec;
            return this;
        }
		
		public Builder withKeyRecoverable(boolean keyRecoverable) {
            this.keyRecoverable = keyRecoverable;
            return this;
        }

		public TestEndEntityParamHolder build() {
		    if (this.tokenType==-1) {
		        this.tokenType = SecConst.TOKEN_SOFT_P12;
		    }
		    if (this.keyAlgo==null) {
                this.keyAlgo = AlgorithmConstants.KEYALGORITHM_RSA;
            }
		    if (this.keySpec==null) {
                this.keySpec = "2048";
            }
			return new TestEndEntityParamHolder(this);
		}

	}

}
