/*
 * Janssen Project software is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2020, Janssen Project
 */

package org.gluu.oxauth.ciba;

import io.jans.as.client.ciba.push.PushErrorClient;
import io.jans.as.client.ciba.push.PushErrorRequest;
import io.jans.as.client.ciba.push.PushErrorResponse;
import io.jans.as.model.ciba.PushErrorResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * @author Javier Rojas Blum
 * @version May 9, 2020
 */
@Stateless
@Named
public class CIBAPushErrorService {

    private final static Logger log = LoggerFactory.getLogger(CIBAPushErrorService.class);

    public void pushError(String authReqId, String clientNotificationEndpoint, String clientNotificationToken,
                          PushErrorResponseType error, String errorDescription) {
        PushErrorRequest pushErrorRequest = new PushErrorRequest();

        pushErrorRequest.setClientNotificationToken(clientNotificationToken);
        pushErrorRequest.setAuthReqId(authReqId);
        pushErrorRequest.setErrorType(error);
        pushErrorRequest.setErrorDescription(errorDescription);

        PushErrorClient pushErrorClient = new PushErrorClient(clientNotificationEndpoint);
        pushErrorClient.setRequest(pushErrorRequest);
        PushErrorResponse pushErrorResponse = pushErrorClient.exec();

        log.debug("CIBA: push error result status " + pushErrorResponse.getStatus());
    }
}
