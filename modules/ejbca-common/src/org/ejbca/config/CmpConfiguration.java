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

package org.ejbca.config;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cesecore.configuration.ConfigurationBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * This is a  class containing CMP configuration parameters.
 */
public class CmpConfiguration extends ConfigurationBase implements Serializable {

    private static final long serialVersionUID = -2787354158199916828L;

    private static final Logger log = Logger.getLogger(CmpConfiguration.class);

    public static final String RA_MODE = "ra";
    public static final String CLIENT_MODE = "client";

    // Constants: Authentication modules
    public static final String AUTHMODULE_REG_TOKEN_PWD = "RegTokenPwd";
    public static final String AUTHMODULE_DN_PART_PWD = "DnPartPwd";
    public static final String AUTHMODULE_HMAC = "HMAC";
    public static final String AUTHMODULE_ENDENTITY_CERTIFICATE = "EndEntityCertificate";

    // Constants: Configuration keys
    public static final String CONFIG_DEFAULTCA = "defaultca";
    public static final String CONFIG_ALLOWRAVERIFYPOPO = "allowraverifypopo";
    public static final String CONFIG_OPERATIONMODE = "operationmode";
    public static final String CONFIG_AUTHENTICATIONMODULE = "authenticationmodule";
    public static final String CONFIG_AUTHENTICATIONPARAMETERS = "authenticationparameters";
    public static final String CONFIG_EXTRACTUSERNAMECOMPONENT = "extractusernamecomponent";
    public static final String CONFIG_RA_ALLOWCUSTOMCERTSERNO = "ra.allowcustomcertserno";
    public static final String CONFIG_RA_NAMEGENERATIONSCHEME = "ra.namegenerationscheme";
    public static final String CONFIG_RA_NAMEGENERATIONPARAMS = "ra.namegenerationparameters";
    public static final String CONFIG_RA_NAMEGENERATIONPREFIX = "ra.namegenerationprefix";
    public static final String CONFIG_RA_NAMEGENERATIONPOSTFIX = "ra.namegenerationpostfix";
    public static final String CONFIG_RA_PASSWORDGENPARAMS = "ra.passwordgenparams";
    /**
     * @deprecated since 6.5.1, but remains to allow 100% uptime during upgrade. Use CONFIG_RA_ENDENTITYPROFILEID instead
     */
    @Deprecated
    public static final String CONFIG_RA_ENDENTITYPROFILE = "ra.endentityprofile";
    public static final String CONFIG_RA_ENDENTITYPROFILEID = "ra.endentityprofileid";
    public static final String CONFIG_RA_CERTIFICATEPROFILE = "ra.certificateprofile";
    public static final String CONFIG_RESPONSEPROTECTION = "responseprotection";
    public static final String CONFIG_RACANAME = "ra.caname";
    public static final String CONFIG_VENDORCERTIFICATEMODE = "vendorcertificatemode";
    /**
     * @deprecated since 7.11.0, but remains to allows 100% uptime during upgrades. Use CONFIG_VENDORCAIDS instead
     */
    @Deprecated
    public static final String CONFIG_VENDORCA = "vendorca";
    public static final String CONFIG_VENDORCAIDS = "vendorcaids";
    public static final String CONFIG_RESPONSE_CAPUBS_CA = "response.capubsca";
    public static final String CONFIG_RESPONSE_CAPUBS_ISSUING_CA = "response.capubsissuingca";
    public static final String CONFIG_RESPONSE_EXTRACERTS_CA = "response.extracertsca";
    public static final String CONFIG_RA_OMITVERIFICATIONSINEEC = "ra.endentitycertificate.omitverifications";
    public static final String CONFIG_RACERT_PATH = "racertificatepath";
    public static final String CONFIG_ALLOWAUTOMATICKEYUPDATE = "allowautomatickeyupdate";
    public static final String CONFIG_ALLOWUPDATEWITHSAMEKEY = "allowupdatewithsamekey";
    public static final String CONFIG_ALLOWSERVERGENERATEDKEYS = "allowservergenkeys";
    public static final String CONFIG_EXTENDEDVALIDATION = "extendedvalidation";
    /**
     * @deprecated since 7.4.0, value is instead set per CA. Only remains for upgrades.
     */
    @Deprecated
    public static final String CONFIG_CERTREQHANDLER_CLASS = "certreqhandler.class";
    /**
     * @deprecated since 6.12.0. No longer used, and can no longer be set. The datasource is now hard-coded to be UnidDS
     */
    @Deprecated
    public static final String CONFIG_UNIDDATASOURCE = "uniddatasource";

    public static final String PROFILE_USE_KEYID = "KeyId";
    public static final String PROFILE_DEFAULT = "ProfileDefault";

    // This List is used in the command line handling of updating a config value to ensure a correct value.
    public static final List<String> CMP_BOOLEAN_KEYS = Arrays.asList(CONFIG_VENDORCERTIFICATEMODE, CONFIG_ALLOWRAVERIFYPOPO, CONFIG_RA_ALLOWCUSTOMCERTSERNO,
            CONFIG_ALLOWAUTOMATICKEYUPDATE, CONFIG_ALLOWUPDATEWITHSAMEKEY, CONFIG_ALLOWSERVERGENERATEDKEYS,
            CONFIG_EXTENDEDVALIDATION);
    public static final String DOT = ".";

    private final String ALIAS_LIST = "aliaslist";
    public static final String CMP_CONFIGURATION_ID = "1";

    // Default Values
    public static final float LATEST_VERSION = 10f;
    public static final String EJBCA_VERSION = InternalConfiguration.getAppVersion();

    // Default values
    public static final Set<String> DEFAULT_ALIAS_LIST = new LinkedHashSet<>();
    public static final String DEFAULT_DEFAULTCA = "";
    public static final String DEFAULT_OPERATION_MODE = CLIENT_MODE;
    public static final String DEFAULT_EXTRACT_USERNAME_COMPONENT = "DN";
    public static final String DEFAULT_VENDOR_MODE = "false";
    public static final String DEFAULT_VENDOR_CA_IDS = "";
    public static final String DEFAULT_RESPONSE_CAPUBS_CA = "";
    public static final String DEFAULT_RESPONSE_CAPUBS_ISSUING_CA = "true";
    public static final String DEFAULT_RESPONSE_EXTRACERTS_CA = "";
    public static final String DEFAULT_KUR_ALLOW_AUTOMATIC_KEYUPDATE = "false";
    public static final String DEFAULT_ALLOW_SERVERGENERATED_KEYS = "false";
    public static final String DEFAULT_KUR_ALLOW_SAME_KEY = "true";
    public static final String DEFAULT_RESPONSE_PROTECTION = "signature";
    public static final String DEFAULT_ALLOW_RA_VERIFY_POPO = "false";
    public static final String DEFAULT_RA_USERNAME_GENERATION_SCHEME = "DN";
    public static final String DEFAULT_RA_USERNAME_GENERATION_PARAMS = "CN";
    public static final String DEFAULT_RA_USERNAME_GENERATION_PREFIX = "";
    public static final String DEFAULT_RA_USERNAME_GENERATION_POSTFIX = "";
    public static final String DEFAULT_RA_PASSWORD_GENERARION_PARAMS = "random";
    public static final String DEFAULT_RA_ALLOW_CUSTOM_SERNO = "false";
    public static final String DEFAULT_RA_EEPROFILE = "1";
    public static final String DEFAULT_RA_CERTPROFILE = "ENDUSER";
    public static final String DEFAULT_RA_CANAME = "ManagementCA";
    public static final String DEFAULT_CLIENT_AUTHENTICATION_MODULE = CmpConfiguration.AUTHMODULE_REG_TOKEN_PWD + ";" + CmpConfiguration.AUTHMODULE_HMAC;
    public static final String DEFAULT_CLIENT_AUTHENTICATION_PARAMS = "-;-";
    public static final String DEFAULT_RA_OMITVERIFICATIONSINEEC = "false";
    public static final String DEFAULT_RACERT_PATH = "";
    public static final String DEFAULT_CERTREQHANDLER = ""; //"org.ejbca.core.protocol.unid.UnidFnrHandler";
    public static final String DEFAULT_EXTENDEDVALIDATION = "false";


    /**
     * Creates a new instance of CmpConfiguration
     */
    public CmpConfiguration() {
        super();
    }

    public CmpConfiguration(Serializable dataobj) {
        @SuppressWarnings("unchecked")
        LinkedHashMap<Object, Object> d = (LinkedHashMap<Object, Object>) dataobj;
        data = d;
    }

    /**
     * Copy constructor for {@link CmpConfiguration}
     */
    public CmpConfiguration(CmpConfiguration cmpConfiguration) {
        super();
        setAliasList(new LinkedHashSet<>());
        for (String alias : cmpConfiguration.getAliasList()) {
            addAlias(alias);
            for (String key : getAllAliasKeys(alias)) {
                String value = cmpConfiguration.getValue(key, alias);
                setValue(key, value, alias);
            }
        }
    }


    /**
     * Initializes a new cmp configuration with default values.
     */
    public void initialize(String alias) {
        if (StringUtils.isNotEmpty(alias)) {
            alias = alias + DOT;
            data.put(alias + CONFIG_DEFAULTCA, DEFAULT_DEFAULTCA);
            data.put(alias + CONFIG_RESPONSEPROTECTION, DEFAULT_RESPONSE_PROTECTION);
            data.put(alias + CONFIG_OPERATIONMODE, DEFAULT_OPERATION_MODE);
            data.put(alias + CONFIG_AUTHENTICATIONMODULE, DEFAULT_CLIENT_AUTHENTICATION_MODULE);
            data.put(alias + CONFIG_AUTHENTICATIONPARAMETERS, DEFAULT_CLIENT_AUTHENTICATION_PARAMS);
            data.put(alias + CONFIG_EXTRACTUSERNAMECOMPONENT, DEFAULT_EXTRACT_USERNAME_COMPONENT);
            data.put(alias + CONFIG_VENDORCERTIFICATEMODE, DEFAULT_VENDOR_MODE);
            data.put(alias + CONFIG_VENDORCAIDS, DEFAULT_VENDOR_CA_IDS);
            data.put(alias + CONFIG_RESPONSE_CAPUBS_CA, DEFAULT_RESPONSE_CAPUBS_CA);
            data.put(alias + CONFIG_RESPONSE_CAPUBS_ISSUING_CA, DEFAULT_RESPONSE_CAPUBS_ISSUING_CA);
            data.put(alias + CONFIG_RESPONSE_EXTRACERTS_CA, DEFAULT_RESPONSE_EXTRACERTS_CA);
            data.put(alias + CONFIG_ALLOWRAVERIFYPOPO, DEFAULT_ALLOW_RA_VERIFY_POPO);
            data.put(alias + CONFIG_RA_NAMEGENERATIONSCHEME, DEFAULT_RA_USERNAME_GENERATION_SCHEME);
            data.put(alias + CONFIG_RA_NAMEGENERATIONPARAMS, DEFAULT_RA_USERNAME_GENERATION_PARAMS);
            data.put(alias + CONFIG_RA_NAMEGENERATIONPREFIX, DEFAULT_RA_USERNAME_GENERATION_PREFIX);
            data.put(alias + CONFIG_RA_NAMEGENERATIONPOSTFIX, DEFAULT_RA_USERNAME_GENERATION_POSTFIX);
            data.put(alias + CONFIG_RA_PASSWORDGENPARAMS, DEFAULT_RA_PASSWORD_GENERARION_PARAMS);
            data.put(alias + CONFIG_RA_ALLOWCUSTOMCERTSERNO, DEFAULT_RA_ALLOW_CUSTOM_SERNO);
            data.put(alias + CONFIG_RA_ENDENTITYPROFILE, "EMPTY");
            data.put(alias + CONFIG_RA_ENDENTITYPROFILEID, DEFAULT_RA_EEPROFILE);
            data.put(alias + CONFIG_RA_CERTIFICATEPROFILE, DEFAULT_RA_CERTPROFILE);
            data.put(alias + CONFIG_RACANAME, DEFAULT_RA_CANAME);
            data.put(alias + CONFIG_RACERT_PATH, DEFAULT_RACERT_PATH);
            data.put(alias + CONFIG_RA_OMITVERIFICATIONSINEEC, DEFAULT_RA_OMITVERIFICATIONSINEEC);
            data.put(alias + CONFIG_ALLOWAUTOMATICKEYUPDATE, DEFAULT_KUR_ALLOW_AUTOMATIC_KEYUPDATE);
            data.put(alias + CONFIG_ALLOWSERVERGENERATEDKEYS, DEFAULT_ALLOW_SERVERGENERATED_KEYS);
            data.put(alias + CONFIG_ALLOWUPDATEWITHSAMEKEY, DEFAULT_KUR_ALLOW_SAME_KEY);
            data.put(alias + CONFIG_CERTREQHANDLER_CLASS, DEFAULT_CERTREQHANDLER);
            data.put(alias + CONFIG_EXTENDEDVALIDATION, DEFAULT_EXTENDEDVALIDATION);
        }
    }

    // return all the key with an alias
    public static Set<String> getAllAliasKeys(String alias) {
        alias = alias + DOT;
        Set<String> keys = new LinkedHashSet<>();
        keys.add(alias + CONFIG_DEFAULTCA);
        keys.add(alias + CONFIG_RESPONSEPROTECTION);
        keys.add(alias + CONFIG_OPERATIONMODE);
        keys.add(alias + CONFIG_AUTHENTICATIONMODULE);
        keys.add(alias + CONFIG_AUTHENTICATIONPARAMETERS);
        keys.add(alias + CONFIG_EXTRACTUSERNAMECOMPONENT);
        keys.add(alias + CONFIG_VENDORCERTIFICATEMODE);
        keys.add(alias + CONFIG_VENDORCAIDS);
        keys.add(alias + CONFIG_RESPONSE_CAPUBS_CA);
        keys.add(alias + CONFIG_RESPONSE_EXTRACERTS_CA);
        keys.add(alias + CONFIG_ALLOWRAVERIFYPOPO);
        keys.add(alias + CONFIG_RESPONSE_CAPUBS_ISSUING_CA);
        keys.add(alias + CONFIG_RA_NAMEGENERATIONSCHEME);
        keys.add(alias + CONFIG_RA_NAMEGENERATIONPARAMS);
        keys.add(alias + CONFIG_RA_NAMEGENERATIONPREFIX);
        keys.add(alias + CONFIG_RA_NAMEGENERATIONPOSTFIX);
        keys.add(alias + CONFIG_RA_PASSWORDGENPARAMS);
        keys.add(alias + CONFIG_RA_ALLOWCUSTOMCERTSERNO);
        keys.add(alias + CONFIG_RA_ENDENTITYPROFILE);
        keys.add(alias + CONFIG_RA_ENDENTITYPROFILEID);
        keys.add(alias + CONFIG_RA_CERTIFICATEPROFILE);
        keys.add(alias + CONFIG_RACANAME);
        keys.add(alias + CONFIG_RACERT_PATH);
        keys.add(alias + CONFIG_RA_OMITVERIFICATIONSINEEC);
        keys.add(alias + CONFIG_ALLOWAUTOMATICKEYUPDATE);
        keys.add(alias + CONFIG_ALLOWUPDATEWITHSAMEKEY);
        keys.add(alias + CONFIG_CERTREQHANDLER_CLASS);
        keys.add(alias + CONFIG_ALLOWSERVERGENERATEDKEYS);
        keys.add(alias + CONFIG_EXTENDEDVALIDATION);
        return keys;
    }


    /**
     * Method used by the Admin GUI.
     */
    public String getCMPDefaultCA(String alias) {
        String key = alias + DOT + CONFIG_DEFAULTCA;
        return getValue(key, alias);
    }

    public void setCMPDefaultCA(String alias, String defCA) {
        String key = alias + DOT + CONFIG_DEFAULTCA;
        setValue(key, defCA, alias);
    }


    public String getResponseProtection(String alias) {
        String key = alias + DOT + CONFIG_RESPONSEPROTECTION;
        String result = getValue(key, alias);
        if (result == null) {
            setResponseProtection(alias, DEFAULT_RESPONSE_PROTECTION);
            return DEFAULT_RESPONSE_PROTECTION;
        } else {
            return result;
        }

    }

    public void setResponseProtection(String alias, String protection) {
        String key = alias + DOT + CONFIG_RESPONSEPROTECTION;
        setValue(key, protection, alias);
    }


    // Any value that is not "ra" or "RA" will be client mode, no matter what it is
    public boolean getRAMode(String alias) {
        String key = alias + DOT + CONFIG_OPERATIONMODE;
        String value = getValue(key, alias);
        return isRAMode(value);
    }

    public void setRAMode(String alias, boolean ramode) {
        String key = alias + DOT + CONFIG_OPERATIONMODE;
        setValue(key, getOperationalMode(ramode), alias);
    }

    public void setRAMode(String alias, String mode) {
        setRAMode(alias, StringUtils.equalsIgnoreCase(mode, RA_MODE));
    }


    public String getAuthenticationModule(String alias) {
        String key = alias + DOT + CONFIG_AUTHENTICATIONMODULE;
        return getValue(key, alias);
    }

    public void setAuthenticationModule(String alias, String authModule) {
        String key = alias + DOT + CONFIG_AUTHENTICATIONMODULE;
        setValue(key, authModule, alias);
    }

    public String getAuthenticationParameters(String alias) {
        String key = alias + DOT + CONFIG_AUTHENTICATIONPARAMETERS;
        return getDecryptedValue(getValue(key, alias));
    }

    public void setAuthenticationParameters(String alias, String authParams) {
        String key = alias + DOT + CONFIG_AUTHENTICATIONPARAMETERS;
        setValue(key, getEncryptedValue(authParams), alias);
    }

    public String getAuthenticationParameter(String authModule, String alias) {
        if (StringUtils.isNotEmpty(alias)) {
            return getAuthenticationParameter(authModule, getAuthenticationModule(alias), getAuthenticationParameters(alias));
        } else {
            if (log.isDebugEnabled()) {
                log.debug("No CMP alias was specified. Returning an empty String");
            }
            return "";
        }
    }

    public boolean isInAuthModule(String alias, String authmodule) {
        return isModulesContainsModule(getAuthenticationModule(alias), authmodule);
    }

    public String getExtractUsernameComponent(String alias) {
        String key = alias + DOT + CONFIG_EXTRACTUSERNAMECOMPONENT;
        return getValue(key, alias);
    }

    public void setExtractUsernameComponent(String alias, String extractComponent) {
        String key = alias + DOT + CONFIG_EXTRACTUSERNAMECOMPONENT;
        setValue(key, extractComponent, alias);
    }


    /**
     * @param alias the CMP configuration alias
     * @return the boolean status of whether Vendor Certificate Mode is activated
     */
    public boolean getVendorMode(String alias) {
        String key = alias + DOT + CONFIG_VENDORCERTIFICATEMODE;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    /**
     * Sets the Vendor Certificate Mode to true or false
     *
     * @param alias      the CMP configuration alias
     * @param vendormode boolean value of Vendor Certificate Mode
     */
    public void setVendorMode(String alias, boolean vendormode) {
        String key = alias + DOT + CONFIG_VENDORCERTIFICATEMODE;
        setValue(key, Boolean.toString(vendormode), alias);
    }

    /**
     * Gets the semicolon separated list of CA IDs for accepted vendor certificates
     *
     * @param alias
     * @return the semicolon separated list of CA IDs
     */
    public String getVendorCaIds(String alias) {
        String key = alias + DOT + CONFIG_VENDORCAIDS;
        return getValue(key, alias);
    }

    /**
     * Sets the semicolon separated list of CA IDs, to add or remove vendor CAs.
     * There are no checks performed, if the CAs for the IDs exist.
     *
     * @param alias    the CMP configuration alias
     * @param vendorCA the semicolon separated list of CA IDs
     */
    public void setVendorCaIds(String alias, String vendorCA) {
        String key = alias + DOT + CONFIG_VENDORCAIDS;
        setValue(key, vendorCA, alias);
    }

    /**
     * Gets the semicolon separated list of CA IDs, to add the CA certificates to CMP response 'caPubs' field.
     *
     * @param alias the CMP configuration alias.
     * @return the semicolon separated list of CA IDs.
     */
    public String getResponseCaPubsCA(String alias) {
        String key = alias + DOT + CONFIG_RESPONSE_CAPUBS_CA;
        return getValue(key, alias);
    }

    /**
     * Sets the semicolon separated list of CA IDs, to add the CA certificates to CMP response 'caPubs' field.
     * <p>
     * There are no checks performed, if the CAs for that IDs exist.
     *
     * @param alias      the CMP configuration alias.
     * @param caIdString the semicolon separated list of CA IDs.
     */
    public void setResponseCaPubsCA(String alias, String caIdString) {
        String key = alias + DOT + CONFIG_RESPONSE_CAPUBS_CA;
        setValue(key, caIdString, alias);
    }

    /**
     * Adds the issuing CA certificate at index 0 of the caPubs field by default.
     *
     * @param alias the CMP configuration alias.
     * @return true if the issuing CA certificate is added.
     */
    public boolean getResponseCaPubsIssuingCA(String alias) {
        String key = alias + DOT + CONFIG_RESPONSE_CAPUBS_ISSUING_CA;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    /**
     * Adds the issuing CA certificate at index 0 of the caPubs field by default.
     *
     * @param alias the CMP configuration alias.
     * @param add   true if the issuing CA certificate has to be added.
     */
    public void setResponseCaPubsIssuingCA(String alias, boolean add) {
        String key = alias + DOT + CONFIG_RESPONSE_CAPUBS_ISSUING_CA;
        setValue(key, Boolean.toString(add), alias);
    }

    /**
     * Sets the semicolon separated list of CA IDs, to add the CA certificates to CMP PKI message response 'extraCerts' field.
     *
     * @param alias the CMP configuration alias.
     * @return the semicolon separated list of CA IDs.
     */
    public String getResponseExtraCertsCA(String alias) {
        String key = alias + DOT + CONFIG_RESPONSE_EXTRACERTS_CA;
        return getValue(key, alias);
    }

    /**
     * Sets the semicolon separated list of CA IDs, to add the CA certificates to CMP PKI message response 'extraCerts' field.
     * <p>
     * There are no checks performed, if the CAs for that IDs exist.
     *
     * @param alias      the CMP configuration alias.
     * @param caIdString the semicolon separated list of CA IDs.
     */
    public void setResponseExtraCertsCA(String alias, String caIdString) {
        String key = alias + DOT + CONFIG_RESPONSE_EXTRACERTS_CA;
        setValue(key, caIdString, alias);
    }

    public boolean getAllowRAVerifyPOPO(String alias) {
        String key = alias + DOT + CONFIG_ALLOWRAVERIFYPOPO;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setAllowRAVerifyPOPO(String alias, boolean raVerifyPopo) {
        String key = alias + DOT + CONFIG_ALLOWRAVERIFYPOPO;
        setValue(key, Boolean.toString(raVerifyPopo), alias);
    }


    public String getRANameGenScheme(String alias) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONSCHEME;
        return getValue(key, alias);
    }

    public void setRANameGenScheme(String alias, String scheme) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONSCHEME;
        setValue(key, scheme, alias);
    }


    public String getRANameGenParams(String alias) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPARAMS;
        return getValue(key, alias);
    }

    public void setRANameGenParams(String alias, String params) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPARAMS;
        setValue(key, params, alias);
    }


    public String getRANameGenPrefix(String alias) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPREFIX;
        return getValue(key, alias);
    }

    public void setRANameGenPrefix(String alias, String prefix) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPREFIX;
        setValue(key, prefix, alias);
    }


    public String getRANameGenPostfix(String alias) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPOSTFIX;
        return getValue(key, alias);
    }

    public void setRANameGenPostfix(String alias, String postfix) {
        String key = alias + DOT + CONFIG_RA_NAMEGENERATIONPOSTFIX;
        setValue(key, postfix, alias);
    }


    public String getRAPwdGenParams(String alias) {
        String key = alias + DOT + CONFIG_RA_PASSWORDGENPARAMS;
        return getValue(key, alias);
    }

    public void setRAPwdGenParams(String alias, String params) {
        String key = alias + DOT + CONFIG_RA_PASSWORDGENPARAMS;
        setValue(key, params, alias);
    }

    public boolean getAllowRACustomSerno(String alias) {
        String key = alias + DOT + CONFIG_RA_ALLOWCUSTOMCERTSERNO;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setAllowRACustomSerno(String alias, boolean allowCustomSerno) {
        String key = alias + DOT + CONFIG_RA_ALLOWCUSTOMCERTSERNO;
        setValue(key, Boolean.toString(allowCustomSerno), alias);
    }


    public String getRAEEProfile(String alias) {
        String key = alias + DOT + CONFIG_RA_ENDENTITYPROFILEID;
        return getValue(key, alias);
    }

    /**
     * @param alias the CMP alias
     * @param eep   the end entity profile ID, or the value KeyId
     * @throws NumberFormatException if the end entity profile ID is not an integer or KeyId
     */
    public void setRAEEProfile(String alias, String eep) throws NumberFormatException {

        // Check the the value actually is an int. Throws NumberFormatException
        if (!StringUtils.equals(CmpConfiguration.PROFILE_USE_KEYID, eep)) {
            Integer.parseInt(eep);
        }

        String key = alias + DOT + CONFIG_RA_ENDENTITYPROFILEID;
        if (!data.containsKey(key)) {
            //Lazy initialization for upgrade
            data.put(key, DEFAULT_RA_EEPROFILE);
        }
        setValue(key, eep, alias);
    }


    public String getRACertProfile(String alias) {
        String key = alias + DOT + CONFIG_RA_CERTIFICATEPROFILE;
        return getValue(key, alias);
    }

    public void setRACertProfile(String alias, String certp) {
        String key = alias + DOT + CONFIG_RA_CERTIFICATEPROFILE;
        setValue(key, certp, alias);
    }


    public String getRACAName(String alias) {
        String key = alias + DOT + CONFIG_RACANAME;
        return getValue(key, alias);
    }

    public void setRACAName(String alias, String caname) {
        String key = alias + DOT + CONFIG_RACANAME;
        setValue(key, caname, alias);
    }

    public String getRACertPath(String alias) {
        String key = alias + DOT + CONFIG_RACERT_PATH;
        return getValue(key, alias);
    }

    public void setRACertPath(String alias, String certpath) {
        String key = alias + DOT + CONFIG_RACERT_PATH;
        setValue(key, certpath, alias);
    }

    public boolean getOmitVerificationsInEEC(String alias) {
        String key = alias + DOT + CONFIG_RA_OMITVERIFICATIONSINEEC;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setOmitVerificationsInEEC(String alias, boolean omit) {
        String key = alias + DOT + CONFIG_RA_OMITVERIFICATIONSINEEC;
        setValue(key, Boolean.toString(omit), alias);
    }

    public boolean getKurAllowAutomaticUpdate(String alias) {
        String key = alias + DOT + CONFIG_ALLOWAUTOMATICKEYUPDATE;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setKurAllowAutomaticUpdate(String alias, boolean allowAutomaticUpdate) {
        String key = alias + DOT + CONFIG_ALLOWAUTOMATICKEYUPDATE;
        setValue(key, Boolean.toString(allowAutomaticUpdate), alias);
    }

    public boolean getAllowServerGeneratedKeys(String alias) {
        String key = alias + DOT + CONFIG_ALLOWSERVERGENERATEDKEYS;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setAllowServerGeneratedKeys(String alias, boolean allowSrvGenKeys) {
        String key = alias + DOT + CONFIG_ALLOWSERVERGENERATEDKEYS;
        setValue(key, Boolean.toString(allowSrvGenKeys), alias);
    }


    public boolean getKurAllowSameKey(String alias) {
        String key = alias + DOT + CONFIG_ALLOWUPDATEWITHSAMEKEY;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setKurAllowSameKey(String alias, boolean allowSameKey) {
        String key = alias + DOT + CONFIG_ALLOWUPDATEWITHSAMEKEY;
        setValue(key, Boolean.toString(allowSameKey), alias);
    }

    public boolean getUseExtendedValidation(String alias) {
        String key = alias + DOT + CONFIG_EXTENDEDVALIDATION;
        String value = getValue(key, alias);
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    public void setUseExtendedValidation(String alias, boolean use) {
        String key = alias + DOT + CONFIG_EXTENDEDVALIDATION;
        setValue(key, Boolean.toString(use), alias);
    }

    /**
     * @deprecated as of 7.4.0 this has setting is set per CA and is universal for all incoming PKCS#10 requests. Only remains for upgrades.
     */
    @Deprecated
    public String getCertReqHandlerClass(String alias) {
        String key = alias + DOT + CONFIG_CERTREQHANDLER_CLASS;
        return getValue(key, alias);
    }

    /**
     * @deprecated as of 7.4.0 this has setting is set per CA and is universal for all incoming PKCS#10 requests. Only remains for upgrades.
     */
    @Deprecated
    public void setCertReqHandlerClass(String alias, String certReqClass) {
        String key = alias + DOT + CONFIG_CERTREQHANDLER_CLASS;
        setValue(key, certReqClass, alias);
    }


    public String getValue(String key, String alias) {
        if (aliasExists(alias)) {
            if (data.containsKey(key)) {
                return (String) data.get(key);
            } else {
                log.info("Could not find key '" + key + "' in the CMP configuration data");
            }
        } else {
            log.error("CMP alias '" + alias + "' does not exist");
        }
        return null;
    }

    public void setValue(String key, String value, String alias) {
        if (aliasExists(alias)) {
            if (data.containsKey(key)) {
                data.put(key, value);
                if (log.isDebugEnabled()) {
                    log.debug("Edited '" + key + "=" + value + "' in the CMP configuration data");
                }
            } else {
                data.put(key, value);
                if (log.isDebugEnabled()) {
                    log.debug("Added '" + key + "=" + value + "' to the CMP configuration data");
                }
            }
        } else {
            log.error("CMP alias '" + alias + "' does not exist");
        }
    }

    /**
     * set list of aliases. Use LinkedHashSet to maintain order, which is important for consistent databaseprotection
     *
     * @param aliaslist LinkedHashSet of aliases,
     */
    public void setAliasList(final Set<String> aliaslist) {
        data.put(ALIAS_LIST, aliaslist);
    }

    public Set<String> getAliasList() {
        @SuppressWarnings("unchecked")
        Set<String> ret = (Set<String>) data.get(ALIAS_LIST);

        return (ret == null ? DEFAULT_ALIAS_LIST : ret);
    }

    public List<String> getSortedAliasList() {
        List<String> result = new ArrayList<>(getAliasList());
        result.sort(String::compareToIgnoreCase);
        return result;
    }

    public boolean aliasExists(String alias) {
        if (StringUtils.isNotEmpty(alias)) {
            Set<String> aliases = getAliasList();
            return aliases.contains(alias);
        }
        return false;
    }

    public void addAlias(String alias) {
        if (log.isDebugEnabled()) {
            log.debug("Adding CMP alias: " + alias);
        }

        if (StringUtils.isEmpty(alias)) {
            if (log.isDebugEnabled()) {
                log.debug("No alias is added because no alias was provided.");
            }
            return;
        }

        Set<String> aliases = getAliasList();
        if (aliases.contains(alias)) {
            if (log.isDebugEnabled()) {
                log.debug("CMP alias '" + alias + "' already exists.");
            }
            return;
        }

        initialize(alias);
        aliases.add(alias);
        data.put(ALIAS_LIST, aliases);
    }

    public void removeAlias(String alias) {
        if (log.isDebugEnabled()) {
            log.debug("Removing CMP alias: " + alias);
        }

        if (StringUtils.isEmpty(alias)) {
            if (log.isDebugEnabled()) {
                log.debug("No alias is removed because no alias was provided.");
            }
            return;
        }

        Set<String> aliases = getAliasList();
        if (!aliases.contains(alias)) {
            if (log.isDebugEnabled()) {
                log.debug("CMP alias '" + alias + "' does not exist");
            }
            return;
        }

        for (String key : getAllAliasKeys(alias)) {
            data.remove(key);
        }
        // remove old keys from previous versions of EJBCA
        data.remove(CONFIG_UNIDDATASOURCE);
        aliases.remove(alias);
        data.put(ALIAS_LIST, aliases);
    }

    public void renameAlias(String oldAlias, String newAlias) {
        if (log.isDebugEnabled()) {
            log.debug("Renaming CMP alias '" + oldAlias + "' to '" + newAlias + "'");
        }

        if (StringUtils.isEmpty(oldAlias) || StringUtils.isEmpty(newAlias)) {
            log.info("No alias is renamed because one or both aliases were not provided.");
            return;
        }

        Set<String> aliases = getAliasList();
        if (!aliases.contains(oldAlias)) {
            log.info("Cannot rename. CMP alias '" + oldAlias + "' does not exists.");
            return;
        }

        if (aliases.contains(newAlias)) {
            log.info("Cannot rename. CMP alias '" + newAlias + "' already exists.");
            return;
        }

        Set<String> oldKeys = getAllAliasKeys(oldAlias);
        for (String oldkey : oldKeys) {
            String newkey = oldkey;
            newkey = StringUtils.replace(newkey, oldAlias, newAlias);
            Object value = data.get(oldkey);
            data.put(newkey, value);
        }
        removeAlias(oldAlias);
        aliases.remove(oldAlias);
        aliases.add(newAlias);
        data.put(ALIAS_LIST, aliases);
    }

    public void cloneAlias(String originAlias, String cloneAlias) {
        if (log.isDebugEnabled()) {
            log.debug("Cloning CMP alias '" + originAlias + "' to '" + cloneAlias + "'");
        }

        if (StringUtils.isEmpty(originAlias) || StringUtils.isEmpty(cloneAlias)) {
            log.info("No alias is cloned because one or both aliased were not provided");
            return;
        }

        Set<String> aliases = getAliasList();
        if (!aliases.contains(originAlias)) {
            log.info("Cannot clone. CMP alias '" + originAlias + "' does not exist.");
            return;
        }

        if (aliases.contains(cloneAlias)) {
            log.info("Cannot clone. CMP alias '" + cloneAlias + "' already exists.");
            return;
        }

        for (String originalKey : getAllAliasKeys(originAlias)) {
            String cloneKey = originalKey;
            cloneKey = StringUtils.replace(cloneKey, originAlias, cloneAlias);
            Object value = data.get(originalKey);
            data.put(cloneKey, value);
        }
        aliases.add(cloneAlias);
        data.put(ALIAS_LIST, aliases);
    }

    /**
     * @return the configuration as a regular Properties object
     */
    public Properties getAsProperties() {
        final Properties properties = new Properties();
        Set<String> aliases = getAliasList();
        for (String alias : aliases) {
            Properties aliasp = getAsProperties(alias);
            properties.putAll(aliasp);
        }
        return properties;
    }

    public Properties getAsProperties(String alias) {
        if (aliasExists(alias)) {
            final Properties properties = new Properties();
            for (String key : getAllAliasKeys(alias)) {
                final Object value = data.get(key);
                properties.setProperty(key, value == null ? "" : value.toString());
            }
            return properties;
        }
        return null;
    }

    /**
     * Implementation of UpgradableDataHashMap function getLatestVersion
     */
    @Override
    public float getLatestVersion() {
        return LATEST_VERSION;
    }

    /**
     * Implementation of UpgradableDataHashMap function upgrade.
     */
    @Override
    public void upgrade() {
        float version = getVersion();
        if (Float.compare(LATEST_VERSION, version) != 0) {
            // New version of the class, upgrade
            log.info("Upgrading CMP Configuration with version " + version);
            Set<String> aliases = getAliasList();
            // v10
            if (isOlderThan(version, 10f)) {
                applyV10(aliases);
            }
            // v9
            if (isOlderThan(version, 9f)) {
                applyV9(aliases);
            }
            // v8
            if (isOlderThan(version, 8f)) {
                applyV8(aliases);
            }
            data.put(VERSION, LATEST_VERSION);
        }
    }

    private boolean isOlderThan(float version, float v) {
        return Float.compare(version, v) < 0;
    }

    private void applyV8(Set<String> aliases) {
        for (String alias : aliases) {
            data.put(alias + DOT + CONFIG_ALLOWSERVERGENERATEDKEYS, DEFAULT_ALLOW_SERVERGENERATED_KEYS);
            data.putIfAbsent(alias + DOT + CONFIG_RESPONSE_CAPUBS_CA, DEFAULT_RESPONSE_CAPUBS_CA);
            data.putIfAbsent(alias + DOT + CONFIG_RESPONSE_EXTRACERTS_CA, DEFAULT_RESPONSE_EXTRACERTS_CA);
        }
    }

    private void applyV9(Set<String> aliases) {
        for (String alias : aliases) {
            data.putIfAbsent(alias + DOT + CONFIG_RESPONSE_CAPUBS_ISSUING_CA, DEFAULT_RESPONSE_CAPUBS_ISSUING_CA);
        }
    }

    private void applyV10(Set<String> aliases) {
        for (String alias : aliases) {
            data.putIfAbsent(alias + DOT + CONFIG_EXTENDEDVALIDATION, DEFAULT_EXTENDEDVALIDATION);
            data.putIfAbsent(alias + DOT + CONFIG_VENDORCAIDS, DEFAULT_VENDOR_CA_IDS);
        }
    }

    @Override
    public String getConfigurationId() {
        return CMP_CONFIGURATION_ID;
    }

    @Override
    public void filterDiffMapForLogging(Map<Object, Object> diff) {
        Set<String> aliases = getAliasList();
        for (String alias : aliases) {
            filterDiffMapForLogging(diff, alias + DOT + CONFIG_AUTHENTICATIONPARAMETERS);
        }
    }

    public static String getAuthenticationParameter(final String authModule, final String confModule, final String confParams) {
        if (StringUtils.isNotEmpty(authModule) && StringUtils.isNotEmpty(confModule) && StringUtils.isNotEmpty(confParams)) {

            String[] modules = confModule.split(";");
            String[] params = confParams.split(";");

            if (modules.length > params.length) {
                log.info("There are not as many authentication parameters as authentication modules. "
                        + modules.length + " modules but " + params.length + " parameters. Returning an empty String");
                return "";
            }

            for (int i = 0; i < modules.length; i++) {
                if (StringUtils.equals(modules[i].trim(), authModule)) {
                    return params[i];
                }
            }
        }
        return "";
    }

    public static Collection<String> getCmpResponseProtectionList(boolean ramode) {
        ArrayList<String> pl = new ArrayList<>();
        pl.add("signature");
        if (ramode) {
            pl.add("pbe");
        }
        return pl;
    }

    public static boolean isModulesContainsModule(String authmodules, String authmodule) {
        if (StringUtils.isNotEmpty(authmodules) && StringUtils.isNotEmpty(authmodule)) {
            return new HashSet<>(Arrays.asList(authmodules.split(";"))).contains(authmodule);
        }
        return false;
    }

    public static boolean isRAMode(final String mode) {
        return StringUtils.equalsIgnoreCase(mode, RA_MODE);
    }

    public static String getOperationalMode(final boolean mode) {
        return mode ? RA_MODE : CLIENT_MODE;
    }

}