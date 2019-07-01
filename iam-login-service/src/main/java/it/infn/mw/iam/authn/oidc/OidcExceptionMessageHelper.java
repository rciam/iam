/**
 * Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2016-2019
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.infn.mw.iam.authn.oidc;

import org.springframework.security.core.AuthenticationException;

import it.infn.mw.iam.authn.AuthenticationExceptionMessageHelper;

public class OidcExceptionMessageHelper implements AuthenticationExceptionMessageHelper {

  public String buildErrorMessage(AuthenticationException e) {

    if (e instanceof OidcClientError) {
      OidcClientError error = (OidcClientError) e;
      if ("access_denied".equals(error.getError())) {
        return "User denied access to requested identity information";
      }
      return error.getError();
    }

    return e.getMessage();
  }

}
