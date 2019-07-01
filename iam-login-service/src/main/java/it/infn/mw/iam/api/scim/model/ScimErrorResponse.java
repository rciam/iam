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
package it.infn.mw.iam.api.scim.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ScimErrorResponse {

  public static final String ERROR_URN = "urn:ietf:params:scim:api:messages:2.0:Error";
  private final String[] schemas = {ERROR_URN};
  private final String status;
  private final String detail;

  public ScimErrorResponse(@JsonProperty("status") int statusCode,
      @JsonProperty("detail") String message) {
    status = Integer.toString(statusCode);
    detail = message;
  }

  public String[] getSchemas() {

    return Arrays.copyOf(schemas, schemas.length);
  }

  public String getStatus() {

    return status;
  }

  public String getDetail() {

    return detail;
  }
}
