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
package org.ejbca.ui.web.rest.api.resource.validator;

import org.apache.log4j.Logger;
import org.cesecore.config.GlobalCesecoreConfiguration;
import org.cesecore.configuration.GlobalConfigurationSessionLocal;
import org.easymock.EasyMock;
import org.ejbca.core.model.util.EjbLocalHelper;
import org.ejbca.ui.web.rest.api.io.request.SearchCertificateCriteriaRestRequest;
import org.ejbca.ui.web.rest.api.io.request.SearchCertificatesRestRequest;
import org.ejbca.ui.web.rest.api.resource.builder.SearchCertificatesRestRequestTestBuilder;
import org.ejbca.ui.web.rest.api.validator.ValidSearchCertificateCriteriaRestRequestList;
import org.ejbca.ui.web.rest.api.validator.ValidSearchCertificateMaxNumberOfResults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * A unit test class for annotation @ValidSearchCertificateCriteriaRestRequestList and its validator.
 * <br/>
 * <b>Note: </b> Due to test compilation issue ECA-7148, we use an original input class SearchCertificatesRestRequest instead of simplified annotated class.
 *
 * @version $Id: ValidSearchCertificateCriteriaRestRequestListUnitTest.java 29504 2018-07-17 17:55:12Z andrey_s_helmes $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ EjbLocalHelper.class, ValidSearchCertificateCriteriaRestRequestList.Validator.class, ValidSearchCertificateMaxNumberOfResults.Validator.class })
public class ValidSearchCertificateCriteriaRestRequestListUnitTest {

    private static Logger log = Logger.getLogger(ValidSearchCertificateCriteriaRestRequestListUnitTest.class);
    private Validator validator;

    @Before
    public void setUp() throws Exception {

        final GlobalConfigurationSessionLocal globalConfigurationSession = PowerMock.createMock(GlobalConfigurationSessionLocal.class);
        final GlobalCesecoreConfiguration globalCesecoreConfigurationMock = PowerMock.createMock(GlobalCesecoreConfiguration.class);
        final EjbLocalHelper ejbLocalHelperMock = PowerMock.createMockAndExpectNew(EjbLocalHelper.class);

        EasyMock.expect(globalConfigurationSession.getCachedConfiguration(EasyMock.anyObject(String.class)))
                .andReturn(globalCesecoreConfigurationMock).anyTimes();

        EasyMock.expect(ejbLocalHelperMock.getGlobalConfigurationSession())
                .andReturn(globalConfigurationSession).anyTimes();

        EasyMock.expect(globalCesecoreConfigurationMock
                        .getMaximumQueryCount())
                .andReturn(11).anyTimes();

        EasyMock.replay();
        PowerMock.replayAll(EjbLocalHelper.class);

        validator = Validation.buildDefaultValidatorFactory().getValidator();

    }

    @Test
    public void validationShouldFailOnNullValue() {
        // given
        final String expectedMessage = "Invalid criteria value, cannot be null.";
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(null)
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Invalid object.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOnEmptyValue() {
        // given
        final String expectedMessage = "Invalid criteria value, cannot be empty.";
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(new ArrayList<SearchCertificateCriteriaRestRequest>())
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Invalid object.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldPassOnNormalValue() {
        // given
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults().build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Valid object.", 0, constraintViolations.size());
    }

    @Test
    public void validationShouldFailOn2QueryCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, multiple 'QUERY' properties are not allowed.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("QUERY").value("TEST").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("QUERY").value("TEST").operation("EQUAL").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one query allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2StringValueCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, combining 'QUERY', 'SERIAL_NUMBER' 'USERNAME', SUBJECT_DN, SUBJECT_ALT_NAME, EXTERNAL_ACCOUNT_BINDING_ID properties is not allowed.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("USERNAME").value("TEST").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("SUBJECT_DN").value("TEST").operation("EQUAL").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one query allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn13StatusCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, 'STATUS' property repetition.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest2 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest3 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest4 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest5 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest6 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest7 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest8 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest9 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest10 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest11 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest12 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest13 = SearchCertificateCriteriaRestRequest.builder().property("STATUS").value("CERT_ACTIVE").operation("EQUAL").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(
                        Arrays.asList(
                                querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1, querySearchCertificateCriteriaRestRequest2,
                                querySearchCertificateCriteriaRestRequest3, querySearchCertificateCriteriaRestRequest4, querySearchCertificateCriteriaRestRequest5,
                                querySearchCertificateCriteriaRestRequest6, querySearchCertificateCriteriaRestRequest7, querySearchCertificateCriteriaRestRequest8,
                                querySearchCertificateCriteriaRestRequest9, querySearchCertificateCriteriaRestRequest10, querySearchCertificateCriteriaRestRequest11,
                                querySearchCertificateCriteriaRestRequest12, querySearchCertificateCriteriaRestRequest13
                        )
                )
                .build();

        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        log.info(constraintViolations);
        assertEquals("Up to 12 are allowed.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2ISSUED_DATEBeforeCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'ISSUED_DATE' with 'BEFORE' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("ISSUED_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("ISSUED_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'BEFORE' 'ISSUED_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2ISSUED_DATEAfterCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'ISSUED_DATE' with 'AFTER' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("ISSUED_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("ISSUED_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();

        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'AFTER' 'ISSUED_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2EXPIRE_DATEBeforeCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'EXPIRE_DATE' with 'BEFORE' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("EXPIRE_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("EXPIRE_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'BEFORE' 'EXPIRE_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2EXPIRE_DATEAfterCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'EXPIRE_DATE' with 'AFTER' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("EXPIRE_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("EXPIRE_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'AFTER' 'EXPIRE_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2REVOCATION_DATEBeforeCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'REVOCATION_DATE' with 'BEFORE' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("REVOCATION_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("REVOCATION_DATE").operation("BEFORE").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'BEFORE' 'REVOCATION_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void validationShouldFailOn2REVOCATION_DATEAfterCriteria() {
        // given
        final String expectedMessage = "Invalid criteria value, overlapping properties 'REVOCATION_DATE' with 'AFTER' operation.";
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest0 = SearchCertificateCriteriaRestRequest.builder().property("REVOCATION_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificateCriteriaRestRequest querySearchCertificateCriteriaRestRequest1 = SearchCertificateCriteriaRestRequest.builder().property("REVOCATION_DATE").operation("AFTER").value("2018-06-15T14:07:09Z").build();
        final SearchCertificatesRestRequest testClass = SearchCertificatesRestRequestTestBuilder.withDefaults()
                .criteria(Arrays.asList(querySearchCertificateCriteriaRestRequest0, querySearchCertificateCriteriaRestRequest1))
                .build();
        // when
        final Set<ConstraintViolation<SearchCertificatesRestRequest>> constraintViolations = validator.validate(testClass);
        // then
        assertEquals("Only one 'AFTER' 'REVOCATION_DATE' is allowed at a time.", 1, constraintViolations.size());
        assertEquals("Validation message should match.", expectedMessage, constraintViolations.iterator().next().getMessage());
    }
}
